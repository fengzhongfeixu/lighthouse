package com.sugon.gsq.lighthouse.core.app.hdfs;

import com.sugon.gsq.lighthouse.core.annotations.LHService;
import com.sugon.gsq.lighthouse.core.app.Service;

/*
 * ClassName: HDFS
 * Author: Administrator
 * Date: 2020/9/8 16:18
 */
@LHService(order = 3)
public class HDFS extends Service {
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
        return "HDFS";
    }

    @Override
    public String getStatus() {
        return null;
    }

}
