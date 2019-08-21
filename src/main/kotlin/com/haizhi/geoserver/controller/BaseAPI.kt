package com.haizhi.geoserver.controller

import com.fasterxml.jackson.databind.ObjectMapper

/**
@author: teddy liu
@date: 2019-08-19 19:39
 */

interface BaseApi {

    var status :Int
    var message :String

    fun setResult(status: Int, data: Any?, msg: String): Map<String, Any?> {
        return mapOf(
            "status" to status,
            "result" to data,
            "message" to msg
        )
    }
    fun setResult(data: Any?) : Map<String, Any?> {
        return mapOf(
            "status" to status,
            "result" to data,
            "message" to message
        )
    }

    fun setError(status: Int, msg: String): Map<String, Any?> {
        return mapOf(
            "status" to status,
            "result" to "",
            "message" to msg
        )

    }

    fun jsonListLoad(data: String): List<*>{
        return ObjectMapper().readValue(data, List::class.java)
    }

}