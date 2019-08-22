package com.haizhi.geoserver.entities

import java.io.Serializable

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

import org.hibernate.annotations.DynamicInsert

import lombok.Data

@Entity
@Table(name = "icons")
@Data
@DynamicInsert
class Icons : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @Column(name = "icon_id")
    var iconId: Long = 0

    @Column(name = "icon_name")
    var iconName: String? = null

    @Column(name = "icon_location")
    var iconLocation: String? = null

    @Column(name = "create_time")
    var createTime: String? = null

    @Column(name = "update_time")
    var updateTime: String? = null

    companion object {

        private const val serialVersionUID = 1L
    }
}
