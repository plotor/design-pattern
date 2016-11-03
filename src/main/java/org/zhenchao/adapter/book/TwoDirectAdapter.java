package org.zhenchao.adapter.book;

import java.util.List;

/**
 * 双向适配器
 *
 * @author zhenchao.wang 2016-11-03 22:43
 * @version 1.0.0
 */
public class TwoDirectAdapter implements LogDBOperateApi, LogFileOperateApi{

    private LogDBOperateApi logDBOperate;

    private LogFileOperate logFileOperate;

    public TwoDirectAdapter(LogDBOperateApi logDBOperate, LogFileOperate logFileOperate) {
        this.logDBOperate = logDBOperate;
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

    @Override
    public List<LogModel> read() {
        return null;
    }

    @Override
    public void write(List<LogModel> logModels) {

    }

}
