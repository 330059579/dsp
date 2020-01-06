package com.tuanzhang.ad.mysql.listener;

import com.tuanzhang.ad.mysql.dto.BinLogRowData;

public interface IListener {

    void register();

    void onEvent(BinLogRowData data);
}
