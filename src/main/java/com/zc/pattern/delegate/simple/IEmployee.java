package com.zc.pattern.delegate.simple;

/**
 * @ClassName IEmployee
 * @Author 周聪
 * @Date 2021/1/9 16:05
 * @Version 1.0
 * @Description 员工接口
 */
public interface IEmployee {

    /**
     * 做事
     * @param command
     */
    void doing(String command);
}
