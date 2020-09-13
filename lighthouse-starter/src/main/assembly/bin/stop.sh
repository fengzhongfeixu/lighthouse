#!/bin/bash

BASE_HOME="$( dirname "$( cd "$( dirname "$0"  )" && pwd ) " )"

if [ $# -ne 1 ] || [ "$1" != "agent" -a "$1" != "master" ] ;then
    echo "Number of parameters or configuration errors !!!"
    exit 1
fi

PROJECT_NAME="lighthouse-"$1

pname='LH'`echo ${1:0:1} | tr 'a-z' 'A-Z'`${1:1}
pid=`jps | grep ${pname} | awk '{print $1}'`
if [ ${pid} ];then
    kill -9 ${pid}
    sleep 1
    if [[ $? -eq 0 ]];then
        echo "${PROJECT_NAME} stopped successfully."
        rm -f ${BASE_HOME}/logs/${PROJECT_NAME}.pid &>/dev/null
    else
        echo "Error! ${PROJECT_NAME} failed to stop..."
    fi
else
    echo "${PROJECT_NAME} is not running, no need to stop."
fi
exit 0
