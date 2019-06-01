package com.ncu.example;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JDBCUtils {
    //创建出BasicDataSource类对象
    private static BasicDataSource datasource = new BasicDataSource();



    //BasicDataSource对象的自定义配置 (静态代码块只会执行一次)
    static{
        //数据库连接信息配置,必须的
        datasource.setDriverClassName("com.mysql.jdbc.Driver"); // 必须是全名(反射)
        datasource.setUrl("jdbc:mysql://localhost:3306/sampledb");
        datasource.setUsername("root");
        datasource.setPassword("123456");

        //对象连接池中的连接数量配置,可选的
        datasource.setInitialSize(10);//初始化的连接数
        datasource.setMaxActive(8); //最大连接数量
        datasource.setMaxIdle(5);  //最大空闲数
        datasource.setMinIdle(1);  //最小空闲数
    }



    //定义静态方法,返回DataSource实现类的对象
    public static DataSource getDataSource(){
        return datasource;
    }

    public static JdbcTemplate getJdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDataSource());
        return jdbcTemplate;
    }
}
