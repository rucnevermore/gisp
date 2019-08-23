package com.haizhi.geoserver.service.impl

import com.haizhi.geoserver.client.GeoserverClient
import com.haizhi.geoserver.constant.BodyTemplate.Companion.HBASE_DATASTORE_CREATE_BODY
import com.haizhi.geoserver.constant.BodyTemplate.Companion.LAYER_CREATE_BODY_XML
import com.haizhi.geoserver.constant.BodyTemplate.Companion.LAYER_NAME
import com.haizhi.geoserver.constant.BodyTemplate.Companion.STORE_NAME
import com.haizhi.geoserver.constant.BodyTemplate.Companion.ZOOKEEPER
import com.haizhi.geoserver.constant.HttpConstant.Companion.JSON_CONTENT_TYPE
import com.haizhi.geoserver.constant.HttpConstant.Companion.XML_CONTENT_TYPE
import com.haizhi.geoserver.service.GeometryService
import com.haizhi.geoserver.utils.GeoUtils
import com.haizhi.geoserver.utils.GeoUtils.Companion.jsonToWkt
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher
import it.geosolutions.geoserver.rest.HTTPUtils
import it.geosolutions.geoserver.rest.decoder.RESTDataStoreList
import it.geosolutions.geoserver.rest.decoder.RESTFeatureType
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service



/**
@author: teddy liu
@date: 2019-08-19 19:16
 */

@Service
@Slf4j
class GeometryServiceImpl : GeometryService {

    internal var logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var geoserverClient: GeoserverClient

    override fun getDatastore(workspace: String): RESTDataStoreList {
        return geoserverClient.reader.getDatastores(workspace)
    }

    override fun createWorkspace(workspace: String): Boolean {
        if(geoserverClient.reader.existsWorkspace(workspace))
            return false
        return geoserverClient.publisher.createWorkspace(workspace)
    }

    override fun createDatastore(workspace: String, storeName: String): Boolean {
        val requestUrl = StringBuilder(geoserverClient.gsUrl).append("/rest/workspaces/")
            .append(workspace).append("/").append(GeoServerRESTPublisher.StoreType.DATASTORES)

        var sentResult: String? = null
        var body: String? = HBASE_DATASTORE_CREATE_BODY.replace(STORE_NAME, storeName,false)
            .replace(ZOOKEEPER, geoserverClient.zookeeper, false)
        logger.debug("Create Data Store Body: $body")
        sentResult = HTTPUtils.post(requestUrl.toString(), body,
            JSON_CONTENT_TYPE, geoserverClient.userName, geoserverClient.password)
        if(sentResult == null) {
            if (logger.isErrorEnabled)
                logger.error("Error in creating store: $storeName")
            return false
        }
        if (logger.isInfoEnabled)
            logger.info("Store successfully created: $storeName")
        return true
        TODO("判断是否存在数据存储，并进行相应的返回")
    }

    /**
     *
     */
    override fun createLayer(workspace: String, storeName: String, layerName: String): Boolean {
        val ftypeXml = LAYER_CREATE_BODY_XML.replace(LAYER_NAME, layerName, false)
        val requestUrl = StringBuilder(geoserverClient.gsUrl).append("/rest/workspaces/")
            .append(workspace).append("/datastores/").append(storeName).append("/featuretypes")
        if (layerName.isEmpty()) {
            if (logger.isErrorEnabled)
                logger.error("GSFeatureTypeEncoder has no valid name associated, try using GSFeatureTypeEncoder.setName(String)")
            return false
        }
        logger.info("Create FeatureType Body: $ftypeXml")
        return HTTPUtils.post(requestUrl.toString(), ftypeXml, XML_CONTENT_TYPE, geoserverClient.userName, geoserverClient.password) != null
    }

    /**
     *
     */
    override fun getAttribute(workspace: String, storeName: String, featureType: String): List<String> {
        val attrList = ArrayList<String>()
        val requestUrl = StringBuilder(geoserverClient.gsUrl).append("/rest/workspaces/")
            .append(workspace).append("/datastores/").append(storeName)
            .append("/featuretypes/").append(featureType).append(".xml")
        val response = HTTPUtils.get(requestUrl.toString(), "admin", "geoserver")
        RESTFeatureType.build(response).attributes.forEach{
            attrList.add(it.name)
        }
        return attrList
    }

    /**
     * http://123.126.105.34:8082/geoserver/haizhi/ows?service=WFS&version=2.0.0
     * &request=GetFeature&typeName=haizhi%3Agdelt-quickstart&outputFormat=application%2Fjson&count=2
     */
    override fun query(workspace: String, storeName: String, featureType: String, count: Int, predicate: String, geometry: String): String {
        val requestUrl = StringBuilder(geoserverClient.gsUrl).append("/")
            .append(workspace).append("/ows?service=WFS&version=2.0.0&request=GetFeature&typeName=")
            .append(workspace)
            .append(":")
            .append(featureType)
            .append("&outputFormat=application%2Fjson")
        if (predicate != "") {
            if(geometry != "")
                requestUrl.append("&cql_filter=$predicate(geom," + jsonToWkt("{\"geometry\":$geometry}") + ")")
        }
        if (count != -1)
            requestUrl.append("&count=$count")
        return HTTPUtils.get(requestUrl.toString().replace(" ", "%20"), "admin", "geoserver")
    }
}