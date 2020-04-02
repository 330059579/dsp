package com.tuanzhang.ad.mysql.constant;

import java.util.HashMap;
import java.util.Map;

public class Constant {

    private final static String DB_NAME = "ad";

    private static class AD_PLAN_TABLE_INFO {
        private static final String TABLE_NAME = "ad_plan";

        private static final String COLUMN_ID = "id";

        private static final String COLUMN_USER_ID = "user_id";

        private static final String COLUMN_START_DATE = "start_date";

        private static final String COLUMN_END_DATE = "end_date";
    }


    private static class AD_CREATIVE_TABLE_INFO {
        private static final String TABLE_NAME = "ad_creative";

        private static final String COLUMN_ID = "id";

        private static final String COLUMN_TYPE = "type";

        private static final String COLUMN_MATERIAL_TYPE = "material_type";


        private static final String COLUMN_HEIGHT = "height";

        private static final String COLUMN_WIDTH= "width";

        private static final String COLUMN_USER_ID = "user_id";

        private static final String COLUMN_AUDIT_STATUS = "audit_status";

        private static final String URL = "url";
    }


    private static class AD_UNIT_TABLE_INFO {
        private static final String TABLE_NAME = "ad_unit";

        private static final String COLUMN_ID = "id";

        private static final String COLUMN_UNIT_STATUS = "unit_status";

        private static final String COLUMN_POSITION_TYPE = "position_type";

        private static final String COLUMN_PLAN_ID = "plan_id";
    }


    private static class AD_CREATIVE_UNIT_TABLE_INFO {
        private static final String TABLE_NAME = "ad_creative_unit";

        private static final String COLUMN_UNIT_ID = "unit_id";

        private static final String COLUMN_CREATIVE_ID = "creative_id";
    }


    private static class AD_UNIT_DISTRICT_TABLE_INFO {
        private static final String TABLE_NAME = "ad_unit_district";

        private static final String COLUMN_UNIT_ID = "unit_id";

        private static final String COLUMN_PROVINCE = "province";

        private static final String COLUMN_CITY = "city";
    }


    private static class AD_UNIT_IT_TABLE_INFO {
        private static final String TABLE_NAME = "ad_unit_district";


        private static final String COLUMN_UNIT_ID = "unit_id";

        private static final String COLUMN_IT_TAG = "it_tag";
    }

    private static class AD_UNIT_KEYWORD_TABLE_INFO {
        private static final String TABLE_NAME = "ad_unit_keyword";

        private static final String COLUMN_UNIT_ID = "unit_id";

        private static final String COLUMN_KEYWORD = "keyword";
    }


    public static Map<String, String> table2Db;

    static {
        table2Db = new HashMap<>();
        table2Db.put(AD_PLAN_TABLE_INFO.TABLE_NAME, DB_NAME);
        table2Db.put(AD_CREATIVE_TABLE_INFO.TABLE_NAME, DB_NAME);
        table2Db.put(AD_UNIT_TABLE_INFO.TABLE_NAME, DB_NAME);
        table2Db.put(AD_CREATIVE_UNIT_TABLE_INFO.TABLE_NAME, DB_NAME);
        table2Db.put(AD_UNIT_DISTRICT_TABLE_INFO.TABLE_NAME, DB_NAME);
        table2Db.put(AD_UNIT_IT_TABLE_INFO.TABLE_NAME, DB_NAME);
        table2Db.put(AD_UNIT_KEYWORD_TABLE_INFO.TABLE_NAME, DB_NAME);

    }
}
