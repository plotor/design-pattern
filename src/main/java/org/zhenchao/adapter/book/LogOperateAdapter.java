package org.zhenchao.adapter.book;

import java.util.List;

/**
 * 日志操作适配器
 *
 * @author zhenchao.wang 2016-11-03 22:30
 * @version 1.0.0
 */
public class LogOperateAdapter implements LogDBOperateApi {

    private LogFileOperateApi logFileOperate;

    public LogOperateAdapter(LogFileOperateApi logFileOperate) {
        this.logFileOperate = logFileOperate;
    }

    @Override
    public void add(LogModel logModel) {
    }

    @Override
    public void delete(String logId) {

    }

    @Override
    public void update(LogModel logModel) {

    }

    @Override
    public List<LogModel> getAll() {
        return null;
    }
}
