package com.tuanzhang.ad.mysql.listener;

import com.github.shyiko.mysql.binlog.event.EventType;
import com.tuanzhang.ad.mysql.constant.Constant;
import com.tuanzhang.ad.mysql.constant.OpType;
import com.tuanzhang.ad.mysql.dto.BinLogRowData;
import com.tuanzhang.ad.mysql.dto.MySqlRowData;
import com.tuanzhang.ad.mysql.dto.TableTemplate;
import com.tuanzhang.ad.sender.ISender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IncrementListener implements IListener {

    @Resource
    private ISender sender;

    private final  AggregationListener aggregationListener;

    @Autowired
    public IncrementListener(AggregationListener aggregationListener) {
        this.aggregationListener = aggregationListener;
    }


    @Override
    @PostConstruct   //在系统启动时应当立即实现注册
    public void register() {
        Constant.table2Db.forEach((k, v) ->
                aggregationListener.register(v,k, this));
    }

    @Override
    public void onEvent(BinLogRowData data) {
        TableTemplate tableTemplate = data.getTable();
        EventType eventType = data.getEventType();

        //包装成最后需要投递的数据
        MySqlRowData rowData = new MySqlRowData();
        rowData.setTableName(tableTemplate.getTableName());
        rowData.setLevel(data.getTable().getLevel());
        OpType opType = OpType.to(eventType);
        rowData.setOpType(opType);

        //取出模板中该操作对应字段列表
        List<String> fieldList = tableTemplate.getOpTypeFieldSetMap().get(opType);
        if (CollectionUtils.isEmpty(fieldList)) {
            return;
        }

        for (Map<String, String > afterMap :data.getAfter()) {
            Map<String, String> _afterMap = new HashMap<>();
            for (Map.Entry<String, String> entry : afterMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                _afterMap.put(key, value);
            }

            rowData.getFiledValueMap().add(_afterMap);
        }

        sender.sender(rowData);
    }
}
