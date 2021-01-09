package com.zc.pattern.delegate.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Leader
 * @Author 周聪
 * @Date 2021/1/9 16:04
 * @Version 1.0
 * @Description 项目经理
 */
public class Leader {
//    预先知道每个员工的特长、特征，分发任务
    private Map<String,IEmployee> register = new HashMap<String,IEmployee>();

    public Leader(){
        register.put("加密",new EmployeeA());
        register.put("架构",new EmployeeB());
    }

    public void doing(String command){
//        交给指定的员工去做
        register.get(command).doing(command);
    }
}
