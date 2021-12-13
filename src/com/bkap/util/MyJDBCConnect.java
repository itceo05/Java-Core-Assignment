package com.bkap.util;

import com.bkap.constant.JCConstant;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyJDBCConnect {

    public static Connection getConnect() {
        try {
            Class.forName(JCConstant.DRIVER);
            Connection sqlConn = DriverManager.getConnection(JCConstant.URL, JCConstant.USER, JCConstant.PASS);
            return sqlConn;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
