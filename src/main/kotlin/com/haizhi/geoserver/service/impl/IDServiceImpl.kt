package com.haizhi.geoserver.service.impl

import com.haizhi.geoserver.service.IDService
import org.springframework.stereotype.Service

@Service
class IDServiceImpl : IDService {

    override fun generate(prefix: String): String {
        val id = (Math.random() * 100000).toInt()
        // BE AWARE, this is just a temporary implement.
        return prefix + "_" + id
    }

}
