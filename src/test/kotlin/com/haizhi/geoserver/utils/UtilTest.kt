package com.haizhi.geoserver.utils

import com.haizhi.geoserver.utils.GeoUtils.Companion.geoToJson
import com.haizhi.geoserver.utils.GeoUtils.Companion.jsonToWkt
import org.junit.Test

/**
@author: teddy liu
@date: 2019-08-23 16:52
 */
class UtilTest {
    @Test
    fun testJsonToWKT() {
        val wkt = "POLYGON((-90 -40, -90 80, 60 80, 60 -40, -90 -40))"
        val map = HashMap<String, Any>()
        map["name"] = "北清路"
        map["author"] = "李雷"
        var json = geoToJson(wkt, map)
        println(json)
        println(jsonToWkt(json))
    }
}