package org.zhenchao.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 登记式
 *
 * @author zhenchao.wang 2016-10-09 22:56
 * @version 1.0.0
 */
public class RegisterSingleton {

    private static Map<String, Object> registry = new HashMap<>();

    static {
        RegisterSingleton instance = new RegisterSingleton();
        registry.put(instance.getClass().getName(), instance);
    }

    protected RegisterSingleton() {
    }

    public static RegisterSingleton getInstance(String name) {
        if (null == name) {
            name = "org.zhenchao.singleton.RegisterSingleton";
        }
        if (null == registry.get(name)) {
            try {
                registry.put(name, Class.forName(name).newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (RegisterSingleton) registry.get(name);
    }
}
