package com.sugon.gsq.lighthouse.core.app;

import org.springframework.stereotype.Component;

/*
 * ClassName: LHService
 * Author: gsq
 * Date: 2020/8/18 15:47
 */
public abstract class Service implements IAction {

    @Override
    public boolean restart(){
        return stop() && start();
    }

}
