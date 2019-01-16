package org.zhenchao.singleton;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * @author zhenchao.wang 2019-01-16 13:34
 * @version 1.0.0
 */
public class EnumSingletonTest implements Serializable {

    private static final long serialVersionUID = 991713955127096061L;

    private static final EnumSingletonTest INSTANCE = new EnumSingletonTest();

    @Test
    public void test() throws Exception {
        // 枚举类型反序列化得到的还是原对象
        EnumSingleton newInstance = this.deserialize(this.serialize(EnumSingleton.INSTANCE));
        Assert.assertEquals(EnumSingleton.INSTANCE, newInstance);

        // 普通类型反序列化会创建新的对象
        EnumSingletonTest newInstance2 = this.deserialize(this.serialize(INSTANCE));
        Assert.assertEquals(INSTANCE, newInstance2);

    }

    /**
     * 防止反序列化创建新的对象
     *
     * @return
     * @throws ObjectStreamException
     */
    private Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }

    /**
     * 序列化
     *
     * @param obj
     * @return
     * @throws IOException
     */
    private byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        try {
            oos.writeObject(obj);
            oos.flush();
            return baos.toByteArray();
        } finally {
            oos.close();
        }
    }

    /**
     * 反序列化
     *
     * @param bytes
     * @param <T>
     * @return
     * @throws Exception
     */
    private <T> T deserialize(byte[] bytes) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        try {
            return (T) ois.readObject();
        } finally {
            ois.close();
        }
    }
}