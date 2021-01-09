package com.zc.pattern.delegate.simple;

/**
 * @ClassName EmployeeA
 * @Author 周聪
 * @Date 2021/1/9 16:07
 * @Version 1.0
 * @Description
 */
public class EmployeeA implements IEmployee {
    @Override
    public void doing(String command) {
        System.out.println("我是员工A,我开始干活了，我擅长加密，执行");
    }
}
