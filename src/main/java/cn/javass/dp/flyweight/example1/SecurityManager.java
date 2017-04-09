package cn.javass.dp.flyweight.example1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 安全管理，实现成单例
 */
public class SecurityManager {

    private static SecurityManager securityManager = new SecurityManager();

    /**
     * 在运行期间，用来存放登录人员对应的权限，
     * 在Web应用中，这些数据通常会存放到session中
     */
    private Map<String, Collection<AuthorizationModel>> map = new HashMap<>();

    private SecurityManager() {
    }

    public static SecurityManager getInstance() {
        return securityManager;
    }

    /**
     * 模拟登录的功能
     *
     * @param user 登录的用户
     */
    public void login(String user) {
        //登录的时候就需要把该用户所拥有的权限，从数据库中取出来，放到缓存中去
        Collection<AuthorizationModel> col = this.queryByUser(user);
        map.put(user, col);
    }

    /**
     * 判断某用户对某个安全实体是否拥有某权限
     *
     * @param user 被检测权限的用户
     * @param securityEntity 安全实体
     * @param permit 权限
     * @return true表示拥有相应权限，false表示没有相应权限
     */
    public boolean hasPermit(String user, String securityEntity, String permit) {
        Collection<AuthorizationModel> col = map.get(user);
        if (col == null || col.size() == 0) {
            System.out.println(user + "没有登录或是没有被分配任何权限");
            return false;
        }
        for (AuthorizationModel model : col) {
            //输出当前实例，看看是否同一个实例对象
            System.out.println("AuthorizationModel hashcode : " + model.hashCode());
            if (model.getSecurityEntity().equals(securityEntity) && model.getPermit().equals(permit)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 从数据库中获取某人所拥有的权限
     *
     * @param user 需要获取所拥有的权限的人员
     * @return 某人所拥有的权限
     */
    private Collection<AuthorizationModel> queryByUser(String user) {
        Collection<AuthorizationModel> models = new ArrayList<AuthorizationModel>();

        for (String data : TestDB.table) {
            String ss[] = data.split(",");
            if (ss[0].equals(user)) {
                AuthorizationModel model = new AuthorizationModel();
                model.setUser(ss[0]);
                model.setSecurityEntity(ss[1]);
                model.setPermit(ss[2]);
                models.add(model);
            }
        }
        return models;
    }

}
