package com.haizhi.geoserver.controller

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

/**
@author: teddy liu
@date: 2019-08-23 20:12
 */
@RestController
@RequestMapping("/table")
class MenuTreeController {
    internal var logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired(required = true)
    protected lateinit var request: HttpServletRequest


}