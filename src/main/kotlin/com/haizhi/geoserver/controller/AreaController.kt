package com.haizhi.geoserver.controller

import com.haizhi.geoserver.response.DefaultResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

/**
@author: teddy liu
@date: 2019-08-23 20:14
 */
@RestController
@RequestMapping("/area")
class AreaController {

    internal var logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired(required = true)
    protected lateinit var request: HttpServletRequest

    @PostMapping(value = ["/addArea"], produces = ["application/json;charset=UTF-8"])
    fun addArea(): DefaultResponse{
        TODO()
    }

    @GetMapping(value = ["/listArea"], produces = ["application/json;charset=UTF-8"])
    fun listArea(): DefaultResponse{
        TODO()
    }
}