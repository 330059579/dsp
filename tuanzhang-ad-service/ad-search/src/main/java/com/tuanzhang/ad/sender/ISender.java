package com.tuanzhang.ad.sender;

import com.tuanzhang.ad.mysql.dto.MySqlRowData;

public interface ISender {

    void sender(MySqlRowData rowData);
}
