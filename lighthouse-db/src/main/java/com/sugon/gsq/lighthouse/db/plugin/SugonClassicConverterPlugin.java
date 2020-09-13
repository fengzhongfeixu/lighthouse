package com.sugon.gsq.lighthouse.db.plugin;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * ClassName: MyClassicConverter
 * Author: gsq
 * Date: 2019/7/30 14:27
 */
public class SugonClassicConverterPlugin extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
