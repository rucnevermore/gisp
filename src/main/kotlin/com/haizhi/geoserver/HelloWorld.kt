package com.haizhi.geoserver

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration

/**
@author: teddy liu
@date: 2019-08-19 17:26
 */

@SpringBootApplication(exclude = arrayOf(DataSourceAutoConfiguration::class))
class HelloWorld

fun main(args: Array<String>) {
    SpringApplication.run(HelloWorld::class.java, *args)
}


