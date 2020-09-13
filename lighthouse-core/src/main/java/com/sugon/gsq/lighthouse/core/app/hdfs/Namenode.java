package com.sugon.gsq.lighthouse.core.app.hdfs;

import com.sugon.gsq.lighthouse.core.annotations.LHProcess;
import com.sugon.gsq.lighthouse.core.app.Process;

/*
 * ClassName: Namenode
 * Author: gsq
 * Date: 2020/9/8 16:08
 */
@LHProcess
public class Namenode extends Process {
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
        return "Namenode";
    }

    @Override
    public String getStatus() {
        return null;
    }

}
