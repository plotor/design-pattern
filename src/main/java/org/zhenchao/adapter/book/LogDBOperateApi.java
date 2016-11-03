package org.zhenchao.adapter.book;

import java.util.List;

/**
 * 日志数据库操作API
 *
 * @author zhenchao.wang 2016-11-03 22:19
 * @version 1.0.0
 */
public interface LogDBOperateApi {

    /**
     * 新增日志
     *
     * @param logModel
     */
    void add(LogModel logModel);

    /**
     * 删除日志
     *
     * @param logId
     */
    void delete(String logId);

    /**
     * 更新日志
     *
     * @param logModel
     */
    void update(LogModel logModel);

    /**
     * 查询日志
     *
     * @return
     */
    List<LogModel> getAll();

}
