package com.sugon.gsq.lighthouse.db.plugin;

import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.db.DBAppenderBase;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

/*
 * ClassName: SugonDBAppenderPlugin
 * Author: gsq
 * Date: 2019/7/30 20:10
 */
public class SugonDBAppenderPlugin extends DBAppenderBase<ILoggingEvent> {
    private String insertSQL;
    private static final Method GET_GENERATED_KEYS_METHOD;
    private static final int TIME_INDEX = 1;
    private static final int MESSAGE_INDEX = 2;
    private static final int LEVEL_STRING_INDEX = 3;
    private static final int LOGGER_NAME_INDEX = 4;
    private static final int THREAD_NAME_INDEX = 5;
    private static final int CALLER_FILENAME_INDEX = 6;
    private static final int CALLER_CLASS_INDEX = 7;
    private static final int CALLER_METHOD_INDEX = 8;
    private static final int CALLER_LINE_INDEX = 9;

    private static final StackTraceElement EMPTY_CALLER_DATA = CallerData.naInstance();

    static {
        Method getGeneratedKeysMethod;
        try {
            getGeneratedKeysMethod = PreparedStatement.class.getMethod("getGeneratedKeys", (Class[]) null);
        } catch (Exception ex) {
            getGeneratedKeysMethod = null;
        }
        GET_GENERATED_KEYS_METHOD = getGeneratedKeysMethod;
    }

    @Override
    public void start() {
        insertSQL = buildInsertSQL();
        super.start();
    }

    private static String buildInsertSQL() {
        return "INSERT INTO om_sys_log " +
                "(time, message, level_string, logger_name, thread_name," +
                "caller_filename, caller_class, caller_method, caller_line)"+
                "VALUES (?, ?, ? ,?, ?, ?, ?, ?, ?)";
    }

    private void bindLoggingEventWithInsertStatement(PreparedStatement stmt, ILoggingEvent event) throws SQLException {
        String uuid = insertLogMessage(event.getFormattedMessage());
        stmt.setTimestamp(TIME_INDEX, new Timestamp(event.getTimeStamp()));
        stmt.setString(MESSAGE_INDEX, uuid);
        stmt.setString(LEVEL_STRING_INDEX, event.getLevel().toString());
        stmt.setString(LOGGER_NAME_INDEX, event.getLoggerName());
        stmt.setString(THREAD_NAME_INDEX, event.getThreadName());
    }

    private void bindCallerDataWithPreparedStatement(PreparedStatement stmt, StackTraceElement[] callerDataArray) throws SQLException {
        StackTraceElement caller = extractFirstCaller(callerDataArray);
        stmt.setString(CALLER_FILENAME_INDEX, caller.getFileName());
        stmt.setString(CALLER_CLASS_INDEX, caller.getClassName());
        stmt.setString(CALLER_METHOD_INDEX, caller.getMethodName());
        stmt.setString(CALLER_LINE_INDEX, Integer.toString(caller.getLineNumber()));
    }

    @Override
    protected void subAppend(ILoggingEvent event, Connection connection, PreparedStatement insertStatement) throws Throwable {
        bindLoggingEventWithInsertStatement(insertStatement, event);
        bindCallerDataWithPreparedStatement(insertStatement, event.getCallerData());
        int updateCount = insertStatement.executeUpdate();
        if (updateCount != 1) {
            addWarn("Failed to insert loggingEvent");
        }
    }

    private StackTraceElement extractFirstCaller(StackTraceElement[] callerDataArray) {
        StackTraceElement caller = EMPTY_CALLER_DATA;
        if (hasAtLeastOneNonNullElement(callerDataArray))
            caller = callerDataArray[0];
        return caller;
    }

    private boolean hasAtLeastOneNonNullElement(StackTraceElement[] callerDataArray) {
        return callerDataArray != null && callerDataArray.length > 0 && callerDataArray[0] != null;
    }

    private String insertLogMessage(String message){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    @Override
    protected Method getGeneratedKeysMethod() {
        return GET_GENERATED_KEYS_METHOD;
    }

    @Override
    protected String getInsertSQL() {
        return insertSQL;
    }

    protected void secondarySubAppend(ILoggingEvent event, Connection connection, long eventId) throws Throwable {
    }

}
