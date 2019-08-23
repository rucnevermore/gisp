package com.haizhi.geoserver.service

import it.geosolutions.geoserver.rest.decoder.RESTDataStoreList
import org.springframework.stereotype.Service

/**
@author: teddy liu
@date: 2019-08-19 19:04
 */

@Service
interface GeometryService {

    fun createWorkspace(workspace: String): Boolean

    fun createDatastore(workspace: String, storeName: String): Boolean

    fun createLayer(workspace: String, storeName: String, layerName: String): Boolean

    fun getDatastore(workspace: String): RESTDataStoreList

    fun getAttribute(workspace: String, storeName: String, featureType: String): List<String>

    fun query(workspace: String, storeName: String, featureType: String, count: Int, predicate: String, geometry: String): String
}