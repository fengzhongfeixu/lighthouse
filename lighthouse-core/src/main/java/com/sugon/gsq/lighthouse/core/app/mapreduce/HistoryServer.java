package com.sugon.gsq.lighthouse.core.app.mapreduce;

import com.sugon.gsq.lighthouse.core.annotations.LHService;
import com.sugon.gsq.lighthouse.core.app.Service;

/*
 * ClassName: HistoryServer
 * Author: Administrator
 * Date: 2020/9/8 16:20
 */
@LHService(order = 2)
public class HistoryServer extends Service {
    @Override
    public boolean start() {
        return false;
    }

    @Override
    public boolean stop() {
        return false;
    }

    @Override
    public String getName() {
        return "HistoryServer";
    }

    @Override
    public String getStatus() {
        return null;
    }
}
