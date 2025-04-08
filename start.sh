#!/bin/bash

# 应用名称
APP_NAME="dyf-Langchain4j"
# JAR包路径
JAR_PATH="target/dyf-Langchain4j-0.0.1-SNAPSHOT.jar"
# 默认日志目录
DEFAULT_LOG_DIR="./logs"
# PID文件
PID_FILE="application.pid"

# 检查是否提供了日志目录参数，若未提供则使用默认目录
if [ -n "$2" ]; then
    LOG_DIR=$2
else
    LOG_DIR=$DEFAULT_LOG_DIR
fi

# 创建日志目录（如果不存在）
mkdir -p $LOG_DIR

# 获取应用PID
get_pid() {
    if [ -f "$PID_FILE" ]; then
        PID=$(cat "$PID_FILE")
        # 验证PID是否仍然有效
        if ps -p $PID > /dev/null 2>&1; then
            echo $PID
            return
        fi
    fi
    # 如果PID文件不存在或PID无效，尝试通过进程查找
    PID=$(ps -ef | grep java | grep "$JAR_PATH" | grep -v grep | awk '{print $2}')
    if [ -z "$PID" ]; then
        # 尝试另一种方式查找，使用应用名称
        PID=$(ps -ef | grep java | grep "$APP_NAME" | grep -v grep | awk '{print $2}')
    fi
    echo $PID
}

# 启动应用
start() {
    PID=$(get_pid)
    if [ -n "$PID" ]; then
        echo "$APP_NAME 已经在运行，PID: $PID"
    else
        echo "正在启动 $APP_NAME..."
        nohup java -jar $JAR_PATH --spring.config.location=file:./src/main/resources/application.properties \
            --logging.file.path=$LOG_DIR > $LOG_DIR/startup.log 2>&1 &

        # 保存PID到文件
        echo $! > $PID_FILE

        echo "$APP_NAME 启动成功，PID: $!"
    fi
}

# 停止应用
stop() {
    PID=$(get_pid)
    if [ -n "$PID" ]; then
        echo "正在停止 $APP_NAME，PID: $PID"
        kill $PID

        # 等待进程结束
        for i in {1..10}
        do
            if ps -p $PID > /dev/null 2>&1; then
                echo "等待应用停止中..."
                sleep 1
            else
                break
            fi
        done

        # 确认进程是否已经结束
        if ps -p $PID > /dev/null 2>&1; then
            echo "应用未能正常停止，尝试强制终止..."
            kill -9 $PID
        fi

        rm -f $PID_FILE
        echo "$APP_NAME 已停止"
    else
        echo "$APP_NAME 未运行"
    fi
}

# 重启应用
restart() {
    stop
    sleep 2
    start
}

# 查看应用状态
status() {
    PID=$(get_pid)
    if [ -n "$PID" ]; then
        echo "$APP_NAME 正在运行，PID: $PID"
        echo "进程详情:"
        ps -f -p $PID
    else
        # 尝试查找可能相关的Java进程
        JAVA_PROCS=$(ps -ef | grep java | grep -v grep)
        if [ -n "$JAVA_PROCS" ]; then
            echo "$APP_NAME 未找到确切的PID，但以下Java进程正在运行："
            echo "$JAVA_PROCS"
        else
            echo "$APP_NAME 未运行"
        fi
    fi
}

# 根据第一个参数执行相应操作
case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        restart
        ;;
    status)
        status
        ;;
    *)
        echo "用法: $0 {start|stop|restart|status} [日志目录路径]"
        echo "例如: $0 start /path/to/logs"
        exit 1
        ;;
esac

exit 0
