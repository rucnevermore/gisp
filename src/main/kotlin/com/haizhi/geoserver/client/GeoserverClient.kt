package com.haizhi.geoserver.client

import it.geosolutions.geoserver.rest.GeoServerRESTManager
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher
import it.geosolutions.geoserver.rest.GeoServerRESTReader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.net.URL

/**
@author: teddy liu
@date: 2019-08-19 17:39
 */

@Component
class GeoserverClient{

    @Value("\${spring.geoserver.url}")
    lateinit var gsUrl: String

    @Value("\${spring.geoserver.username}")
    lateinit var userName: String

    @Value("\${spring.geoserver.password}")
    lateinit var password: String

    @Value("\${spring.geomesa.zookeeper}")
    lateinit var zookeeper: String

    lateinit var reader: GeoServerRESTReader
    lateinit var publisher: GeoServerRESTPublisher
    lateinit var manager: GeoServerRESTManager

    @Autowired
    fun init(){
        manager = GeoServerRESTManager(URL(gsUrl), userName, password)
        reader = manager.reader
        publisher = manager.publisher
    }
}