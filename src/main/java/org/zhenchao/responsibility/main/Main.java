package org.zhenchao.responsibility.main;

import org.zhenchao.responsibility.hetero.AbstractMethodResultHandler;
import org.zhenchao.responsibility.hetero.ValueAHandler;
import org.zhenchao.responsibility.hetero.ValueBHandler;
import org.zhenchao.responsibility.hetero.ValueCHandler;
import org.zhenchao.responsibility.model.Result;
import org.zhenchao.responsibility.pure.AbstractAssetsHandler;
import org.zhenchao.responsibility.pure.DepartmentManagerHandler;
import org.zhenchao.responsibility.pure.GeneralManagerHandler;
import org.zhenchao.responsibility.pure.ProjectManagerHandler;

/**
 * 驱动类
 *
 * @author zhenchao.wang 2016-09-11 17:01
 * @version 1.0.0
 */
public class Main {

    public static void main(String[] args) {

        AbstractAssetsHandler assetsHandler = new ProjectManagerHandler(new DepartmentManagerHandler(new GeneralManagerHandler()));
        assetsHandler.handle(123456L, 500);
        assetsHandler.handle(123456L, 1000);
        assetsHandler.handle(123456L, 1500);

        AbstractMethodResultHandler resultHandler = new ValueAHandler(new ValueBHandler(new ValueCHandler()));
        Result result = new Result();
        resultHandler.handle(result);
        System.out.println(result);

    }
}
