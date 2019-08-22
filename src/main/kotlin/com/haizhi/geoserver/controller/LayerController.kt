package com.haizhi.geoserver.controller

import com.haizhi.geoserver.dao.IconRepository
import com.haizhi.geoserver.dao.LayerRepository
import com.haizhi.geoserver.entities.Layers
import com.haizhi.geoserver.response.DefaultResponse
import com.haizhi.geoserver.constant.NamingConstants
import com.haizhi.geoserver.service.IDService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/project")
class LayerController {

    internal var logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private val layerRepository: LayerRepository? = null

    @Autowired
    private val iconRepository: IconRepository? = null

    @Autowired
    private val idService: IDService? = null

    @GetMapping("/listLayerConfig")
    fun listLayerConfig(@RequestParam(value = "layerID", required = false) layerId: String?): DefaultResponse {
        val response = DefaultResponse()
        if (layerId != null) {
            val layer = layerRepository!!.findByLayerId(layerId)
            response.result = layer
        } else {
            val layerList = layerRepository!!.findAll()
            response.result = layerList
        }
        return response
    }

    @PostMapping("/addLayerConfig")
    fun addLayerConfig(@RequestBody data: Layers): DefaultResponse {
        data.layerId = idService!!.generate(NamingConstants.PRE_LAYER)
        layerRepository!!.save(data)
        layerRepository.flush()
        return DefaultResponse.OK
    }

    @PostMapping("/editLayerConfig")
    fun editLayerConfig(@RequestBody data: Layers): DefaultResponse {
        var layerId = data.layerId
        val layer = layerRepository?.findByLayerId(layerId)
        if (layer != null) {
            layer.projectId = data.projectId
            layer.layerName = data.layerName
            layer.color = data.color
            layer.gisTabId = data.gisTabId
            layer.tag = data.tag
            layer.iconId = data.iconId
            layer.iconName = data.iconName
            layer.iconType = data.iconType
            layer.opacity = data.opacity
            layerRepository!!.saveAndFlush(layer)
            return DefaultResponse.OK
        }else{
            return DefaultResponse.KO
        }
    }

    @GetMapping("/listIcon")
    fun listIcon(): DefaultResponse {
        val response = DefaultResponse()
        val iconList = iconRepository!!.findAll()
        response.result = iconList
        return response
    }
}
