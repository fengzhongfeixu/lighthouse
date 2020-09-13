package com.sugon.gsq.lighthouse.db.plugin;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;
import com.sugon.gsq.lighthouse.db.util.JsonValidator;

/*
 * ClassName: SugonFilterPlugin
 * Author: gsq
 * Date: 2019/7/31 11:26
 */
public class SugonFilterPlugin extends AbstractMatcherFilter<ILoggingEvent> {

    private static final String packageName = "com.sugon.gsq.om";

    @Override
    public FilterReply decide(ILoggingEvent event) {
        FilterReply filterReply;
        // 记录日志的class必须在com.sugon包下且日志级别为INFO且信息格式为json
        if (event.getLoggerName().toLowerCase().contains(packageName)
                && event.getLevel() == Level.INFO
                && JsonValidator.validate(event.getFormattedMessage().trim())) {
            filterReply =  FilterReply.ACCEPT;
        } else {
            filterReply = FilterReply.DENY;
        }
        return filterReply;
    }
}
