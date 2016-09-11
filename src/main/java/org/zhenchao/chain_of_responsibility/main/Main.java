package org.zhenchao.chain_of_responsibility.main;

import org.zhenchao.chain_of_responsibility.hetero.AbstractMethodResultHandler;
import org.zhenchao.chain_of_responsibility.hetero.ValueAHandler;
import org.zhenchao.chain_of_responsibility.hetero.ValueBHandler;
import org.zhenchao.chain_of_responsibility.hetero.ValueCHandler;
import org.zhenchao.chain_of_responsibility.model.Result;
import org.zhenchao.chain_of_responsibility.pure.AbstractAssetsHandler;
import org.zhenchao.chain_of_responsibility.pure.DepartmentManagerHandler;
import org.zhenchao.chain_of_responsibility.pure.GeneralManagerHandler;
import org.zhenchao.chain_of_responsibility.pure.ProjectManagerHandler;

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
