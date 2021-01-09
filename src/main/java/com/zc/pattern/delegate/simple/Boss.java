package com.zc.pattern.delegate.simple;

/**
 * @ClassName Boss
 * @Author 周聪
 * @Date 2021/1/9 15:48
 * @Version 1.0
 * @Description 老板发布命令
 */
public class Boss {

    /**
     * 发任务
     * @param command
     * @param leader
     */
    public void command(String command,Leader leader){
        leader.doing(command);
    }
}
