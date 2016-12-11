package org.zhenchao.proxy.jproxy;

import java.sql.SQLException;

/**
 * 数据库操作抽象
 *
 * @author zhenchao.wang 2016-12-11 10:06
 * @version 1.0.0
 */
public interface DatabaseDao {

    void insert() throws SQLException;

    void delete() throws SQLException;

    void update() throws SQLException;

    void select() throws SQLException;

}
