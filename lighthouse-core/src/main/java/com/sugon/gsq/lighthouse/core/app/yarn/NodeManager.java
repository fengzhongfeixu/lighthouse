package com.sugon.gsq.lighthouse.core.app.yarn;

import com.sugon.gsq.lighthouse.core.annotations.LHProcess;
import com.sugon.gsq.lighthouse.core.app.Process;

/*
 * ClassName: NodeManager
 * Author: Administrator
 * Date: 2020/9/8 17:53
 */
@LHProcess
public class NodeManager extends Process {
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
        return null;
    }

    @Override
    public String getStatus() {
        return null;
    }
}
