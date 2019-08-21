package com.haizhi.geoserver.service

import org.junit.Test
import it.geosolutions.geoserver.rest.HTTPUtils
import it.geosolutions.geoserver.rest.decoder.RESTFeatureType

class GeoserverRestTest {
    @Test
    fun testGetAttribute(){
        val response = HTTPUtils.get("http://123.126.105.34:8082/geoserver/rest/workspaces/haizhi/datastores/geomesa/featuretypes/gdelt-quickstart.xml", "admin", "geoserver")
        RESTFeatureType.build(response).attributes.forEach{
            println(it.name + ": " + it.binding)
        }
    }
}