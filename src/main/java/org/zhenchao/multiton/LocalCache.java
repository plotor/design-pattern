package org.zhenchao.multiton;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhenchao.wang 2016-10-15 23:01
 * @version 1.0.0
 */
public class LocalCache {

    private static Map<Long, LocalCache> instances = new HashMap<>();

    private LocalCache() {
    }

    public static LocalCache getInstance(long userId) {
        LocalCache cache = instances.get(userId);
        if (null == cache) {
            synchronized (LocalCache.class) {
                if (null == cache) {
                    cache = new LocalCache();
                    instances.put(userId, cache);
                }
            }
        }
        return cache;
    }

}
