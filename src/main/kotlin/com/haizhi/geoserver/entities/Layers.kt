package com.haizhi.geoserver.entities

import lombok.Data
import org.hibernate.annotations.DynamicInsert
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "layers")
@Data
@DynamicInsert
class Layers : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @Column(name = "project_id")
    var projectId: Long = 0

    @Column(name = "layer_id")
    var layerId: String = ""

    @Column(name = "layer_name")
    var layerName: String = ""

    @Column(name = "gis_tab_id")
    var gisTabId: Long = 0

    @Column(name = "feature_type")
    var featureType: Long = 0

    @Column(name = "icon_type")
    var iconType: Long = 0

    @Column(name = "icon_id")
    var iconId: Long = 0

    @Column(name = "icon_name")
    var iconName: String = ""

    @Column(name = "opacity")
    var opacity: Long = 0

    @Column(name = "color")
    var color: String = ""

    @ElementCollection
    var tag: List<String>? = null

    @Column(name = "create_time")
    var createTime: String = ""

    @Column(name = "update_time")
    var updateTime: String = ""

    companion object {

        private const val serialVersionUID = 1L
    }

}
