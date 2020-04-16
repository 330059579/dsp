package com.tuanzhang.ad.mysql;

import com.alibaba.fastjson.JSON;
import com.tuanzhang.ad.mysql.constant.OpType;
import com.tuanzhang.ad.mysql.dto.ParseTemplate;
import com.tuanzhang.ad.mysql.dto.TableTemplate;
import com.tuanzhang.ad.mysql.dto.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.CharSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class TemplateHolder {

   private ParseTemplate parseTemplate;

   @Autowired
   private JdbcTemplate jdbcTemplate;

   //这个sql可以查询表的索引和列明的对应关系
    private String SQL_SCHEMA = "select table_schema, table_name, " +
            "column_name, ordinal_position from information_schema.columns " +
            "where table_schema = ? and table_name = ?";

    @PostConstruct
    private void init(){
        loadJson("template.json");
    }


    public TableTemplate getTable(String tableName) {
        return parseTemplate.getTableTemplateMap().get(tableName);
    }

    private void loadJson(String path){
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        InputStream resourceAsStream = contextClassLoader.getResourceAsStream(path);
        try {
            Template template = JSON.parseObject(resourceAsStream, Charset.defaultCharset(), Template.class);
            this.parseTemplate = ParseTemplate.parse(template);
            loadMeta();
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private void loadMeta(){

        for (Map.Entry<String, TableTemplate> entry : parseTemplate.getTableTemplateMap().entrySet()) {
            TableTemplate value = entry.getValue();
            List<String> insertFields = value.getOpTypeFieldSetMap().get(OpType.ADD);
            List<String> updateFields = value.getOpTypeFieldSetMap().get(OpType.UPDATE);
            List<String> deleteFields = value.getOpTypeFieldSetMap().get(OpType.DELETE);

            jdbcTemplate.query(SQL_SCHEMA, new Object[]{
                    parseTemplate.getDatabases(), value.getTableName()
            },(rs, i) ->{
                int pos = rs.getInt("ORDINAL_POSITION");
                String colName = rs.getString("COLUMN_NAME");

                if ((null != updateFields && updateFields.contains(colName))
                        || (null != insertFields && insertFields.contains(colName))
                        || (null != deleteFields && deleteFields.contains(colName))) {
                    value.getPostMap().put(pos - 1, colName);
                }

                return null;
            });
        }
    }


}
