package com.tuanzhang.ad;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.tuanzhang.ad.mysql.BinLogConfig;
import com.tuanzhang.ad.mysql.listener.AggregationListener;
import org.springframework.stereotype.Component;

@Component
public class BinLogClient {

    private BinaryLogClient client;

    private final BinLogConfig config;
    private final AggregationListener listener;


    public BinLogClient(BinLogConfig config, AggregationListener listener) {
        this.config = config;
        this.listener = listener;
    }

    public void connect(){
        new Thread(() ->{
            System.out.println("11");
        }).start();
    }
}
