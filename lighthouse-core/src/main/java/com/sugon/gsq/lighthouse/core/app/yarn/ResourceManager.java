package com.sugon.gsq.lighthouse.core.app.yarn;

import com.sugon.gsq.lighthouse.core.annotations.LHProcess;
import com.sugon.gsq.lighthouse.core.app.Process;
import com.sugon.gsq.lighthouse.core.app.Service;

/*
 * ClassName: ResourceManager
 * Author: gsq
 * Date: 2020/9/8 17:50
 */
@LHProcess
public class ResourceManager extends Process {
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
