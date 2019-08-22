package com.haizhi.geoserver.dao

import com.haizhi.geoserver.entities.Layers
import org.springframework.data.jpa.repository.JpaRepository


interface LayerRepository : JpaRepository<Layers, Long> {

    fun findByLayerId(layerId: String): Layers

}
