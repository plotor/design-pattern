package org.zhenchao.proxy.jproxy;

import java.sql.SQLException;

/**
 * 数据库操作实现类
 *
 * @author zhenchao.wang 2016-12-11 10:11
 * @version 1.0.0
 */
public class DatabaseDaoImpl implements DatabaseDao {

    @Override
    public void insert() throws SQLException {
        System.out.println("insert into table");
    }

    @Override
    public void delete() throws SQLException {
        System.out.println("delete from table");
    }

    @Override
    public void update() throws SQLException {
        // 模拟异常
        System.out.println("update table set");
        throw new SQLException("update data exception");
    }

    @Override
    public void select() throws SQLException {
        System.out.println("select * from table");
    }

}
