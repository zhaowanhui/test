package com.zwh.test;

import com.zwh.bean.MyFactory;
import com.zwh.dao.UserDAO;
import com.zwh.entity.User;
import org.junit.Test;

public class Test1 {
    @Test
    public void test1(){
        UserDAO userDAO = MyFactory.getInstance("userDAO");
        userDAO.test();
        User user = MyFactory.getInstance("user");
        System.out.println(user);
    }
}
