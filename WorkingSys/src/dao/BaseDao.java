package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class BaseDao {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    public static Connection con = null;
    public static ResultSet rs = null;
    public static PreparedStatement pstmt = null;

    //静态代码块:当这个类被使用的时候就会执行一次
    /*获取配置信息的方法*/
    static{
        InputStream in = BaseDao.class.getClassLoader().getResourceAsStream("database.properties");
        Properties props = new Properties();
        try {
            props.load(in);
            driver = props.getProperty("driver");
            url = props.getProperty("url");
            user = props.getProperty("user");
            password = props.getProperty("password");
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    /*获取链接对象的方法*/
    public static void getCon(){
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ResultSet query(String sql,Object[] prams){
        try {
            getCon();
            pstmt = con.prepareStatement(sql);
            if (prams!=null){
                for (int i = 0; i <prams.length; i++) {
                    pstmt.setObject(i+1,prams[i]);
                }
            }
            rs=pstmt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }

    public static ResultSet queryByList(String sql, List params){
        try {
            getCon();
            pstmt = con.prepareStatement(sql);
            if (params!=null){
                for (int i = 0; i <params.size(); i++) {
                    pstmt.setObject(i+1,params.get(i));
                }
            }
            rs=pstmt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }


    public static int update(String sql,Object[] prams){
        int result =0;
        try {
            getCon();
            pstmt=con.prepareStatement(sql);
            if (prams!=null){
                for (int i = 0; i <prams.length; i++) {
                    pstmt.setObject(i+1,prams[i]);
                }
            }
            result=pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeAll(con,pstmt,null);
        }
        return result;
    }

    public static void closeAll(Connection con,PreparedStatement pstmt,ResultSet rs){
        if (pstmt!=null){
            try {
                pstmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (con!=null){
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
}

