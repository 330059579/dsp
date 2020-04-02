package com.tuanzhang.ad.runner;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.tuanzhang.ad.BinLogClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//實現CommandLineRunner接口，程序開始時執行
@Component
public class BinLogRunner implements CommandLineRunner {

    private final BinLogClient client;

    @Autowired
    public BinLogRunner(BinLogClient client) {
        this.client = client;
    }

    @Override
    public void run(String... args) throws Exception {
        client.connect();
    }
}
