package com.dogxu.test;

import com.dogxu.dao.UserDao;
import com.dogxu.entity.UserEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author DogXu
 * @date 2020/8/3 16:35
 * @description:mybatis入门案例
 */
public class MybatisXMLTest {

    public static void main(String[] args) {
        try {
            //1.读取配置文件：
            InputStream in = Resources.getResourceAsStream("MybatisSqlMapConfig.xml");

            //2.创建SqlSessionFactory工厂：
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

            //3.使用工厂生产SqlSession对象：
            SqlSession sqlSession = sqlSessionFactory.openSession();

            //4.使用SqlSession创建Dao接口的代理对象：
            UserDao userDao = sqlSession.getMapper(UserDao.class);

            //5.使用代理对象执行方法：
            List<UserEntity> userEntities = userDao.selectAll();
            System.err.println(userEntities);

            //6.释放资源：
            sqlSession.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
