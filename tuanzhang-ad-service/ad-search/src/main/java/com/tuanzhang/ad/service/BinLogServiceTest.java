package com.tuanzhang.ad.service;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;

import java.io.IOException;

public class BinLogServiceTest {


    //    Write---------------
//    WriteRowsEventData{tableId=85, includedColumns={0, 1, 2}, rows=[
//    [10, 10, 宝马]
//]}
//    Update--------------
//    UpdateRowsEventData{tableId=85, includedColumnsBeforeUpdate={0, 1, 2},
// includedColumns={0, 1, 2}, rows=[
//        {before=[10, 10, 宝马], after=[10, 11, 宝马]}
//]}
//    Delete--------------
//    DeleteRowsEventData{tableId=85, includedColumns={0, 1, 2}, rows=[
//    [11, 10, 奔驰]
//]}


//    Write---------------
//    WriteRowsEventData{tableId=70, includedColumns={0, 1, 2, 3, 4, 5, 6, 7}, rows=[
//    [12, 10, plan, 1, Tue Jan 01 08:00:00 CST 2019, Tue Jan 01 08:00:00 CST 2019, Tue Jan 01 08:00:00 CST 2019, Tue Jan 01 08:00:00 CST 2019]
//]}

    // Tue Jan 01 08:00:00 CST 2019

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

        try {

            client.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
