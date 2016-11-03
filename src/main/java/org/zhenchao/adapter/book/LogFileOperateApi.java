package org.zhenchao.adapter.book;

import java.util.List;

/**
 * 日志文件操作API
 *
 * @author zhenchao.wang 2016-11-03 22:12
 * @version 1.0.0
 */
public interface LogFileOperateApi {

    /**
     * 读取日志文件
     *
     * @return
     */
    List<LogModel> read();

    /**
     * 写日志文件
     *
     * @param logModels
     */
    void write(List<LogModel> logModels);
}
