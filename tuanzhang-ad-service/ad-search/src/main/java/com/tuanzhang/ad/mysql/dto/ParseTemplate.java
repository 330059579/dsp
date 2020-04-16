package com.tuanzhang.ad.mysql.dto;

import com.tuanzhang.ad.mysql.constant.OpType;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Data
public class ParseTemplate {

    private String databases;
    private Map<String, TableTemplate> tableTemplateMap = new HashMap<>();
    
    public static ParseTemplate parse(Template template) {
        ParseTemplate parseTemplate = new ParseTemplate();
        parseTemplate.setDatabases(template.getDatabases());

        for(JsonTable table: template.getTableList()) {
            String name = table.getTableName();
            Integer level = table.getLevel();
            TableTemplate tableTemplate = new TableTemplate();
            tableTemplate.setTableName(name);
            tableTemplate.setLevel(level);
            parseTemplate.tableTemplateMap.put(name, tableTemplate);

            //便利操作类型对应的列
            Map<OpType, List<String>> opTypeFiledSetMap = tableTemplate.getOpTypeFieldSetMap();
            for (JsonTable.Column column : table.getInsert()) {
                getAndCreateIfNeed(OpType.ADD, opTypeFiledSetMap,ArrayList::new).add(column.getColumn());
            }

            for (JsonTable.Column column : table.getUpdate()) {
                getAndCreateIfNeed(OpType.UPDATE, opTypeFiledSetMap,ArrayList::new).add(column.getColumn());
            }

            for (JsonTable.Column column : table.getDelete()) {
                getAndCreateIfNeed(OpType.DELETE, opTypeFiledSetMap,ArrayList::new).add(column.getColumn());
            }
        }

        return parseTemplate;
    }

    private static <T, R> R getAndCreateIfNeed(T key, Map<T, R> map, Supplier<R> factory) {
        return map.computeIfAbsent(key, k -> factory.get());
    }
}
