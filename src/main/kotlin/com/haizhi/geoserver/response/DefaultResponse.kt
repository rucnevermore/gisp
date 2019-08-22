package com.haizhi.geoserver.response

import java.io.Serializable
import java.util.UUID


import lombok.Data

@Data
class DefaultResponse {
    var errstr = ""

    var result: Serializable? = null

    var status = 0

    var trcid = UUID.randomUUID().toString()

    constructor(errstr: String, status: Int){
        this.errstr = errstr
        this.status = status
    }

    constructor()

    companion object {
        val OK = DefaultResponse("调用成功", 0)
        val KO = DefaultResponse("调用失败", 1)
    }
}
