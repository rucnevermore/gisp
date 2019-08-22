package com.haizhi.geoserver.dao

import com.haizhi.geoserver.entities.Icons
import org.springframework.data.jpa.repository.JpaRepository

interface IconRepository : JpaRepository<Icons, Long>