package org.zhenchao.multiton;

/**
 * 缓存
 * 有上限多例
 *
 * @author zhenchao.wang 2016-10-11 22:33
 * @version 1.0.0
 */
public class Cache {

    /** 本地缓存 */
    private static final Cache LOCAL_CACHE = new Cache();

    /** 全局缓存 */
    private static final Cache GLOBAL_CACHE = new Cache();

    private Cache() {
    }

    public static Cache getInstance(CacheType cacheType) {
        return CacheType.GLOBAL.equals(cacheType) ? GLOBAL_CACHE : LOCAL_CACHE;
    }

    /**
     * 缓存类型
     */
    public enum CacheType {
        LOCAL(1),
        GLOBAL(2);
        private int value;

        CacheType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }
}
