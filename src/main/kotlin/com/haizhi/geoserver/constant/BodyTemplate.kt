package com.haizhi.geoserver.constant

/**
@author: teddy liu
@date: 2019-08-20 18:36
 */

class BodyTemplate {
    companion object {
        const val HBASE_DATASTORE_CREATE_BODY = "{\n" +
                "  \"dataStore\": {\n" +
                "    \"name\": \"STORE_NAME\",\n" +
                "    \"connectionParameters\": {\n" +
                "      \"entry\": [\n" +
                "                {\n" +
                "                    \"@key\": \"geomesa.query.threads\",\n" +
                "                    \"\$\": \"8\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"@key\": \"geomesa.query.caching\",\n" +
                "                    \"\$\": \"false\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"@key\": \"geomesa.query.loose-bounding-box\",\n" +
                "                    \"\$\": \"true\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"@key\": \"hbase.zookeepers\",\n" +
                "                    \"\$\": \"ZOOKEEPER\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"@key\": \"hbase.remote.filtering\",\n" +
                "                    \"\$\": \"true\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"@key\": \"hbase.catalog\",\n" +
                "                    \"\$\": \"STORE_NAME\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"@key\": \"geomesa.stats.enable\",\n" +
                "                    \"\$\": \"true\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"@key\": \"geomesa.security.auths.force-empty\",\n" +
                "                    \"\$\": \"false\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"@key\": \"hbase.security.enabled\",\n" +
                "                    \"\$\": \"false\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"@key\": \"geomesa.query.audit\",\n" +
                "                    \"\$\": \"true\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"@key\": \"namespace\",\n" +
                "                    \"\$\": \"haizhi\"\n" +
                "                }\n" +
                "            ]\n" +
                "    }\n" +
                "  }\n" +
                "}"
        const val WORKSPACE = "WORKSPACE"
        const val STORE_NAME = "STORE_NAME"
        const val ZOOKEEPER = "ZOOKEEPER"

        const val LAYER_CREATE_BODY_XML = "<featureType>\n" +
                "    <name>LAYER_NAME</name>\n" +
                "    <nativeCRS>EPSG:4326</nativeCRS>\n" +
                "    <enabled>true</enabled>\n" +
                "    <attributes>\n" +
                "    </attributes>\n" +
                "</featureType>"
        const val LAYER_NAME = "LAYER_NAME"
    }
}