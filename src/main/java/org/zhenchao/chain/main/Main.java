package org.zhenchao.chain.main;

import org.zhenchao.chain.hetero.AbstractMethodResultHandler;
import org.zhenchao.chain.hetero.ValueAHandler;
import org.zhenchao.chain.hetero.ValueBHandler;
import org.zhenchao.chain.hetero.ValueCHandler;
import org.zhenchao.chain.model.Result;
import org.zhenchao.chain.pure.AssetsHandler;
import org.zhenchao.chain.pure.DepartmentManagerHandler;
import org.zhenchao.chain.pure.GeneralManagerHandler;
import org.zhenchao.chain.pure.ProjectManagerHandler;

/**
 * 驱动类
 *
 * @author zhenchao.wang 2016-09-11 17:01
 * @version 1.0.0
 */
public class Main {

    public static void main(String[] args) {

        AssetsHandler assetsHandler = new ProjectManagerHandler(new DepartmentManagerHandler(new GeneralManagerHandler()));
        assetsHandler.handle(123456L, 500);
        assetsHandler.handle(123456L, 1000);
        assetsHandler.handle(123456L, 1500);

        AbstractMethodResultHandler resultHandler = new ValueAHandler(new ValueBHandler(new ValueCHandler()));
        Result result = new Result();
        resultHandler.handle(result);
        System.out.println(result);

    }
}
