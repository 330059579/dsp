package com.tuanzhang.ad;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.tuanzhang.ad.mysql.BinLogConfig;
import com.tuanzhang.ad.mysql.listener.AggregationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
          client =  new BinaryLogClient(config.getHost(), config.getPort(), config.getUsername(), config.getPassword());
          if (!StringUtils.isEmpty(config.getBinlogName()) && !config.getPosition().equals(-1L)){
              client.setBinlogFilename(client.getBinlogFilename());
              client.setBinlogPosition(config.getPosition());
          }

          client.registerEventListener(listener);

          try {
              System.out.println("connecting to mysql start");
              client.connect();
              System.out.println("connecting to mysql end");

          }catch (Exception e) {
              e.printStackTrace();
          }
        }).start();
    }


    public void close(){
       try {
           client.disconnect();
       }catch (Exception e) {
           e.printStackTrace();
       }
    }
}
