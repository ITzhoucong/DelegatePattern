package com.zc.pattern.delegate.simple.test;

import com.zc.pattern.delegate.simple.Boss;
import com.zc.pattern.delegate.simple.Leader;

/**
 * @ClassName DelegateTest
 * @Author 周聪
 * @Date 2021/1/9 16:18
 * @Version 1.0
 * @Description
 */
public class DelegateTest {

    public static void main(String[] args) {
        new Boss().command("架构",new Leader());
    }
}
