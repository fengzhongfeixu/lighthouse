#!/bin/bash

BASE_HOME="$( dirname "$( cd "$( dirname "$0"  )" && pwd ) " )"

PROJECT_NAME="lighthouse-"$1

MAIN_CLASS=""

export ROLE=$1

if [ "$1" == "master" ] ;then
    MAIN_CLASS="com.sugon.gsq.lighthouse.LHMaster"
elif [ "$1" == "agent" ] ;then
    MAIN_CLASS="com.sugon.gsq.lighthouse.LHAgent"
else
    echo "Number of parameters or configuration errors !!!"
    exit 1
fi

export JAVA_HOME_BIN=/usr/java/jdk1.8.0_192-amd64/bin

JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -Xmx1500m"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -XX:+UseG1GC -verbose:gc"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -XX:+PrintGCDetails"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -XX:+PrintGCTimeStamps"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -XX:+PrintGCDateStamps"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -Xloggc:${BASE_HOME}/logs/${PROJECT_NAME}-gc.log"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -XX:+HeapDumpOnOutOfMemoryError"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -XX:HeapDumpPath=${BASE_HOME}/logs/heapdump.hprof"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -XX:+UseGCLogFileRotation"
JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -XX:GCLogFileSize=128M -XX:NumberOfGCLogFiles=4"
#JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -Dcom.sun.management.jmxremote=false"
#JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -Dcom.sun.management.jmxremote.port=30005"
#JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -Dcom.sun.management.jmxremote.ssl=false"
#JAVA_CMD_OPTS="${JAVA_CMD_OPTS} -Dcom.sun.management.jmxremote.authenticate=false"

cd ${BASE_HOME}

if [ $# -ne 1 ] || [ "$1" != "agent" -a "$1" != "master" ] ;then
    echo "Number of parameters or configuration errors !!!"
    exit 1
fi

pname='LH'`echo ${1:0:1} | tr 'a-z' 'A-Z'`${1:1}
pid=`jps | grep ${pname} | awk '{print $1}'`
if [ -f ${BASE_HOME}/logs/${PROJECT_NAME}.pid ];then
    echo "Error! ${PROJECT_NAME} is running and pid is ${pid}, please stop it first."
    exit 1
else
    #set classpath
    for j in ${BASE_HOME}/lib/*.jar;do
        CLASSPATH=${j}:"${CLASSPATH}"
    done
    CLASSPATH="${BASE_HOME}/conf:${CLASSPATH}"

    #nohup java -jar
    nohup ${JAVA_HOME_BIN}/java ${JAVA_CMD_OPTS} -classpath .:${CLASSPATH} ${MAIN_CLASS} $1 -Dglobal.config.path=${BASE_HOME}/conf/ --spring.config.location=${BASE_HOME}/conf/application.yml --logging.config ${BASE_HOME}/conf/logback.xml &>>${BASE_HOME}/logs/${PROJECT_NAME}.log &

    sleep 2
    pname='LH'`echo ${1:0:1} | tr 'a-z' 'A-Z'`${1:1}
    pid=`jps | grep ${pname} | awk '{print $1}'`
    if [ ${pid} ];then
        echo "${PROJECT_NAME} started successfully."
        echo ${pid} > ${BASE_HOME}/logs/${PROJECT_NAME}.pid
    else
        echo "Error! ${PROJECT_NAME} failed to start... please check the logs."
        exit 1
    fi
fi
exit 0
