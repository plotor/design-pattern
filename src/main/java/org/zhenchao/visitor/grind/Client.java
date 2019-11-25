package org.zhenchao.visitor.grind;

/**
 * @author zhenchao.wang 2019-11-25 14:09
 * @version 1.0.0
 */
public class Client {

    public static void main(String[] args) {
        ObjectStructure os = new ObjectStructure();

        os.add(new EnterpriseCustomer("阿里巴巴"));
        os.add(new EnterpriseCustomer("小米科技"));

        os.add(new PersonalCustomer("张三"));
        os.add(new PersonalCustomer("李四"));

        // 客户提出服务请求
        Visitor srVisitor = new ServiceRequestVisitor();
        os.handleRequest(srVisitor);

        // 对客户进行偏好分析
        Visitor paVisitor = new PreferenceAnalyzeVisitor();
        os.handleRequest(paVisitor);
    }

}
