package com.haizhi.geoserver.utils

import java.io.IOException
import java.io.StringReader
import org.geotools.geojson.geom.GeometryJSON
import org.json.simple.JSONObject
import java.io.StringWriter
import org.locationtech.jts.io.WKTReader


/**
@author: teddy liu
@date: 2019-08-23 14:13
 */

class GeoUtils {
    companion object{
        fun jsonToWkt(geoJson: String): String {
            var wkt: String = ""
            val gjson = GeometryJSON()
            val reader = StringReader(geoJson)
            try {
                val geometry = gjson.read(reader)
                wkt = geometry.toText()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return wkt
        }

        fun geoToJson(wkt: String, map: MutableMap<String, Any>): String {
            var json: String = ""
            try {
                val reader = WKTReader()
                val geometry = reader.read(wkt)
                val writer = StringWriter()
                val g = GeometryJSON()
                g.write(geometry, writer)
                map["geometry"] = writer
                json = JSONObject.toJSONString(map)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return json
        }
    }
}