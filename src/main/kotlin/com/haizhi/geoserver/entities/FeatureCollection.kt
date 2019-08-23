package com.haizhi.geoserver.entities

import lombok.Data
import org.opengis.feature.Feature
import java.io.Serializable

/**
@author: teddy liu
@date: 2019-08-23 15:50
 */

@Data
class FeatureCollection: Serializable {

    var type: String = "FeatureCollection"
    var numberReturned: Int = 0

    var features: List<Feature>? = null
}