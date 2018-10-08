#!/bin/bash
EC2_HOME=/home/ec2-user
PID_FILE=$EC2_HOME/bot.pid
LOG_FILE=$EC2_HOME/bot.log

KEY_FILE=$EC2_HOME/.keyfile
KEY_FILE_DEST=$EC2_HOME/The-Bot/src/main/resources/com/brentvw/discord

sudo chown -R ec2-user:ec2-user $EC2_HOME/The-Bot/
cd $EC2_HOME/The-Bot

if [ -e $PID_FILE ]
then
    kill -9 `cat $PID_FILE`
fi

cp $KEY_FILE $KEY_FILE_DEST
mvn clean install -DskipTests
mvn exec:java -DskipTests & > $LOG_FILE
echo $! > $PID_FILE 
