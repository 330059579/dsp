package com.tuanzhang.ad.service;

import com.alibaba.fastjson.JSON;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;

public class BinLogServiceTest {

    public static void main(String[] args) {
        BinaryLogClient client = new BinaryLogClient("94.191.72.230",3306,
                "root", "123456");
        //不设置从最新的binlog开始监听
       // client.setBinlogFilename("");
        client.registerEventListener(event -> {
            EventData data = event.getData();
            if (data instanceof UpdateRowsEventData) {
                System.out.println("update===" + data.toString());
            }

            if (data instanceof WriteRowsEventData) {
                System.out.println("write===" + data.toString());
            }

            if (data instanceof DeleteRowsEventData) {
                System.out.println("delete===" + data.toString());
            }
        });
    }
}
