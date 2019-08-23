package com.haizhi.geoserver.controller

import com.google.gson.GsonBuilder
import com.haizhi.geoserver.entities.FeatureData
import com.haizhi.geoserver.response.DefaultResponse
import com.haizhi.geoserver.service.GeometryService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import org.json.simple.JSONObject

/**
@author: teddy liu
@date: 2019-08-19 19:36
 */

@RestController
@RequestMapping("/gisdata")
class GeoserverAPI : BaseApi {

    override var status: Int =  0
    override var message: String = ""

    internal var logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired(required = true)
    protected lateinit var request: HttpServletRequest

    @Autowired
    private lateinit var geometryService: GeometryService

    @GetMapping(value = ["/getDatastore"], produces = ["application/json;charset=UTF-8"])
    fun getDatastore(
        @RequestParam(value = "workspace", defaultValue = "", required = true)workspace :String
    ): DefaultResponse {
        val response = DefaultResponse()
        var data: String = ""
        geometryService.getDatastore(workspace).forEach{
            data += it.name
        }
        response.result = data
        return response
    }

    /**
     * 在geoserver中创建一个工作区，workspace默认为user_id，理论上不会显式的调用
     */
    @PostMapping(value = ["/createWorkspace"], produces = ["application/json;charset=UTF-8"])
    fun createWorkspace(
        @RequestParam(value = "workspace", defaultValue = "", required = true)workspace: String
    ): DefaultResponse {
        var workspace = "haizhi"
//        var workspace = this.request.session.getAttribute("user_id").toString()
        var data = geometryService.createWorkspace(workspace)
        if(!data)
            return DefaultResponse.KO
        return  DefaultResponse.OK
    }

    /**
     * 在geoserver中创建一个数据存储，类型为hbase-geomesa，vector，workspace名默认为user_id，datastore名默认为gis_tab_id
     */
    @PostMapping(value = ["/createDatastore"], produces = ["application/json;charset=UTF-8"])
    fun createDatastore(
        @RequestParam(value = "storeName", defaultValue = "", required = true)storeName: String
    ): DefaultResponse {
        var workspace = "haizhi"
//        var workspace = this.request.session.getAttribute("user_id").toString()
        var data = geometryService.createDatastore(workspace, storeName)
        if(!data)
            return DefaultResponse.KO
        return  DefaultResponse.OK
    }

    /**
     * 在geoserver中创建一个图层，对应于数据中的一个featureType，workspace名默认为user_id，datastore名默认为gis_tab_id
     */
    @PostMapping(value = ["/createFeatureType"], produces = ["application/json;charset=UTF-8"])
    fun createLayer(
        @RequestParam(value = "layerName", defaultValue = "", required = true)layerName: String
    ): DefaultResponse {
        var workspace = "haizhi"
//        var workspace = this.request.session.getAttribute("user_id").toString()
        var storeName = "geomesa"
//        storeName = featureType
        var data = geometryService.createLayer(workspace, storeName, layerName)
        if(!data)
            return DefaultResponse.KO
        return  DefaultResponse.OK
    }

    /**
     * 获取一个gis表的attributes信息，即数据的字段信息，workspace名默认为user_id，datastore名默认为gis_tab_id，featureType名默认为gis_tab_id
     */
    @GetMapping(value = ["/listAttributes"], produces = ["application/json;charset=UTF-8"])
    fun getAttributes(
        @RequestParam(value = "gisTabID", defaultValue = "", required = true)featureType: String
    ): DefaultResponse {
        logger.info("请求参数：gisTabID = {}", featureType)
        val response = DefaultResponse()
        var workspace = "haizhi"
//        var workspace = this.request.session.getAttribute("user_id").toString()
        var storeName = "geomesa"
//        storeName = featureType
        response.result = geometryService.getAttribute(workspace, storeName, featureType) as ArrayList<*>
        return response
    }

    /**
     * 获取一个gis表的数据，workspace名默认为user_id，datastore名默认为gis_tab_id，featureType名默认为gis_tab_id
     */
    @PostMapping(value = ["/queryData"], produces = ["application/json;charset=UTF-8"] )
    fun dataPreview(
//        @RequestParam(value = "gisTabID", defaultValue = "", required = true)featureType: String,
//        @RequestParam(value = "count", defaultValue = "-1",  required = false)count: Int,
//        @RequestParam(value = "predicate", defaultValue = "", required = false)predicate: String,
//        @RequestParam(value = "geometry", defaultValue = "", required = false)geometry: String
        @RequestBody body: FeatureData
    ): DefaultResponse {

        logger.info("请求参数：requestBody = {}", body.toString())
        val response = DefaultResponse()
        var workspace = "haizhi"
//        var workspace = this.request.session.getAttribute("user_id").toString()
        var storeName = "geomesa"
//        storeName = featureType
        val gson = GsonBuilder().create()
        val geoJsonString = geometryService.query(workspace, storeName,
            body.gisTabID,
            body.count,
            body.predicate,
            body.geometry.toString())
        response.result = gson.fromJson(geoJsonString, Object::class.java)
        return response
    }
}
