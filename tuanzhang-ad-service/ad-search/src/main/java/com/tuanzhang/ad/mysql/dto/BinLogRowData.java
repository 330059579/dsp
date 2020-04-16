package com.tuanzhang.ad.mysql.dto;

import com.github.shyiko.mysql.binlog.event.EventType;
import lombok.Data;

import java.util.List;
import java.util.Map;

//将监听到的binlog日志字符串解析为此类
@Data
public class BinLogRowData {

    private TableTemplate table;

    private EventType eventType;

    private List<Map<String, String>> after;

    private List<Map<String, String>> before;
}
