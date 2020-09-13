package com.sugon.gsq.lighthouse.core.app;

/*
 * ClassName: IAction
 * Author: gsq
 * Date: 2020/8/20 14:31
 */
public interface IAction extends IBase {

    boolean start();

    boolean stop();

    boolean restart();

}
