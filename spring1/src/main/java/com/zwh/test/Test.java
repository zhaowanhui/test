package com.zwh.test;

import com.zwh.bean.MyFactory;
import com.zwh.dao.UserDAO;

public class Test {
    public static void main(String[] args){
        UserDAO userDAO =(UserDAO) MyFactory.getInstance("UserDAO");
        userDAO.test();
    }
}
