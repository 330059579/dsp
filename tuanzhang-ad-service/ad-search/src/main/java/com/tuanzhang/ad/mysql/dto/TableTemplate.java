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
      private Map<Integer, String> postMap = new HashMap<>();
}
