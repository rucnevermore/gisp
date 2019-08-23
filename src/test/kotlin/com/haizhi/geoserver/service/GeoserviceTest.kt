package com.haizhi.geoserver.service

import org.junit.Test
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.junit.runner.RunWith
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
@author: teddy liu
@date: 2019-08-21 11:39
 */

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class GeoserviceTest {

//    @Autowired
    private val mockMvc: MockMvc? = null

    @Autowired
    lateinit var geometryService: GeometryService

    @Throws(Exception::class)
    fun testGetAttribute() {
        this.mockMvc!!.perform(
            get("/gisdata/listAttributes")
            .param("featureType", "gdelt-quickstart"))
            .andExpect(status().`is`(200))
            .andDo(MockMvcResultHandlers.print()
            )
    }

    @Throws(Exception::class)
    fun testDataPreviewAPI() {
        this.mockMvc!!.perform(
            post("/gisdata/dataPreview")
            .param("featureType", "gdelt-quickstart"))
            .andExpect(status().`is`(200))
            .andDo(MockMvcResultHandlers.print()
            )
    }

    @Test
    @Throws(Exception::class)
    fun testDataPreviewService(){
        val result = geometryService.query("haizhi", "geomesa", "gdelt-quickstart", 10, "", "")
    }
}