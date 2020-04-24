package pers.lujiayi.im;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootTest
class ImApplicationTests {

    private static final String JDBC_URL = "jdbc:h2:mem:DBName;DB_CLOSE_DELAY=-1";
    //用户名
    private static final String USER = "sa";
    //连接数据库时使用的密码，默认为空
    private static final String PASSWORD = "";
    //连接H2数据库时使用的驱动类，org.h2.Driver这个类是由H2数据库自己提供的，在H2数据库的jar包中可以找到
    private static final String DRIVER_CLASS = "org.h2.Driver";

    @Test
    void contextLoads() {


        try {
            // 加载H2数据库驱动
            Class.forName(DRIVER_CLASS);
            // 根据连接URL，用户名，密码获取数据库连接
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

}
