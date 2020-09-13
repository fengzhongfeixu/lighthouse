package com.sugon.gsq.lighthouse.core.app.yarn;


import com.sugon.gsq.lighthouse.core.annotations.LHService;
import com.sugon.gsq.lighthouse.core.app.Service;

/*
 * ClassName: Yarn
 * Author: Administrator
 * Date: 2020/9/8 17:55
 */
@LHService(order = 1)
public class Yarn extends Service {
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
        return "Yarn";
    }

    @Override
    public String getStatus() {
        return null;
    }
}
