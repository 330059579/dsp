package com.tuanzhang.ad.sender.kafka;

import com.alibaba.fastjson.JSON;
import com.tuanzhang.ad.mysql.dto.MySqlRowData;
import com.tuanzhang.ad.sender.ISender;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

public class KafkaSender implements ISender {

    //TODO
   /* @Value("${adconf.kafka.topic}")
    private String topic;
*/
    /*@Resource
    private KafkaTemplate<String, String> kafkaTemplate;*/

    @Override
    public void sender(MySqlRowData rowData) {
      /*  kafkaTemplate.send("test", JSON.toJSONString(rowData));*/
    }

    @KafkaListener(topics={"ad-search-mysql-data"},groupId = "ad-search")
    public void processMysqlRowData(ConsumerRecord<?,?> record){
       /* Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            MySqlRowData rowData = JSON.parseObject(message.toString(), MySqlRowData.class);
        }*/

    }
}
