#!/bin/bash
PID_FILE=$HOME/bot.pid
LOG_FILE=$HOME/bot.log

KEY_FILE=$HOME/.keyfile
KEY_FILE_DEST=src/main/resources/com/brentvw/discord/

if [ -e $PID_FILE ]
then
    kill -9 `cat $PID_FILE`
fi

cp $KEY_FILE $KEY_FILE_DEST
mvn clean install -DskipTests
mvn exec:java -DskipTests & > $LOG_FILE
echo $! > $PID_FILE 