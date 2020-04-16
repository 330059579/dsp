package com.tuanzhang.ad.mysql.dto;

import com.tuanzhang.ad.mysql.constant.OpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableTemplate {

      private String tableName;
      private Integer level;
      private Map<OpType, List<String>> opTypeFieldSetMap = new HashMap<>();

      //由于binlong日志中，只记录列的索引，没有记录列的名字，这里需要维护一个列索引和列名字的映射
      private Map<Integer, String> postMap = new HashMap<>();
}
