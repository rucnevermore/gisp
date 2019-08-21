package com.haizhi.geoserver.controller

import com.haizhi.geoserver.service.GeometryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
@author: teddy liu
@date: 2019-08-19 19:36
 */

@RestController
@RequestMapping("/gisdata")
class GeoserverAPI : BaseApi {

    override var status: Int =  0
    override var message: String = ""

    @Autowired
    private lateinit var geometryService: GeometryService

    @GetMapping(value = ["/getDatastore"], produces = ["application/json;charset=UTF-8"])
    fun getDatastore(
        @RequestParam(value = "workspace", defaultValue = "", required = true)workspace :String
    ):Map<*, *> {
        var data: String = ""
        geometryService.getDatastore(workspace).forEach{
            data += it.name
        }
        return setResult(0, data, "")
    }

    @PostMapping(value = ["/createDatastore"], produces = ["application/json;charset=UTF-8"])
    fun createDatastore(
        @RequestParam(value = "workspace", defaultValue = "", required = true)workspace: String,
        @RequestParam(value = "storeName", defaultValue = "", required = true)storeName: String
    ):Map<*, *> {
        var data: String = ""
        data = geometryService.createDatastore(workspace, storeName).toString()
        return setResult(0, data, "")
    }

    @PostMapping(value = ["/createLayer"], produces = ["application/json;charset=UTF-8"])
    fun createLayer(
        @RequestParam(value = "workspace", defaultValue = "", required = true)workspace: String,
        @RequestParam(value = "storeName", defaultValue = "", required = true)storeName: String,
        @RequestParam(value = "layerName", defaultValue = "", required = true)layerName: String
    ):Map<*, *> {
        var data: String = ""
        data = geometryService.createLayer(workspace, storeName, layerName).toString()
        return setResult(0, data, "")
    }

    @GetMapping(value = ["/getAttributes"], produces = ["application/json;charset=UTF-8"])
    fun getAttributes(
        @RequestParam(value = "workspace", defaultValue = "", required = true)workspace: String,
        @RequestParam(value = "storeName", defaultValue = "", required = true)storeName: String,
        @RequestParam(value = "featureType", defaultValue = "", required = true)featureType: String
    ):Map<*, *> {
        var data = geometryService.getAttribute(workspace, storeName, featureType)
        return setResult(0, mapOf("list" to data), "")
    }
}