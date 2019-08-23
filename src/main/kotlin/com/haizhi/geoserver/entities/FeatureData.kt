package com.haizhi.geoserver.entities

import lombok.Data
import lombok.ToString
import org.json.simple.JSONObject

/**
@author: teddy liu
@date: 2019-08-23 16:36
 */
@Data
class FeatureData {
    var gisTabID: String = ""
    var count: Int = -1
    var predicate: String = ""
    var geometry: JSONObject? = null
}