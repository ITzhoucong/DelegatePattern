package com.zc.pattern.delegate.simple;

/**
 * @ClassName EmployeeB
 * @Author 周聪
 * @Date 2021/1/9 16:09
 * @Version 1.0
 * @Description
 */
public class EmployeeB implements IEmployee {
    @Override
    public void doing(String command) {
        System.out.println("我是员工B,我擅长架构，我开始干活了");
    }
}
