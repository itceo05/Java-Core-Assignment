package com.bkap.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class DataProvider {
    static ResultSet resultSet = null;
    static Connection sqlConn = MyJDBCConnect.getConnect();
    static CallableStatement callableStatement = null;

    public static ResultSet excuteQuery(String sql, Object... params) {

        try {
            callableStatement = sqlConn.prepareCall(sql);

            if(params != null)
                for (int i = 0; i < params.length; i++) {
                    callableStatement.setObject(i + 1, params[i]);
                }

            resultSet = callableStatement.executeQuery();
            return resultSet;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static int excuteUpdate(String sql, Object... params) {

        try {
            callableStatement = sqlConn.prepareCall(sql);

            if(params != null)
                for (int i = 0; i < params.length; i++) {
                    callableStatement.setObject(i + 1, params[i]);
                }
            return callableStatement.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }
}
