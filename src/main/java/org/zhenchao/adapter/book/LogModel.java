package org.zhenchao.adapter.book;

/**
 * 日志数据对象
 *
 * @author zhenchao.wang 2016-11-03 22:09
 * @version 1.0.0
 */
public class LogModel {

    private String id;

    private String user;

    private String createTime;

    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
