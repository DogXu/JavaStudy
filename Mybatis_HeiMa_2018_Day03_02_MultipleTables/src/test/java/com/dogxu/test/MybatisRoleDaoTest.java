package com.dogxu.test;

import com.dogxu.dao.RoleDao;
import com.dogxu.dao.UserDao;
import com.dogxu.entity.RoleEntity;
import com.dogxu.entity.UserEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author DogXu
 * @date 2020/8/4 14:21
 * @description:
 */
public class MybatisRoleDaoTest {

    private InputStream in;
    private SqlSession sqlSession;
    private RoleDao roleDao;

    @Before//在测试方法之前执行
    public void initResorces() {
        try {
            //1.读取配置文件：
            in = Resources.getResourceAsStream("MybatisSqlMapConfig.xml");

            //2.创建SqlSessionFactory工厂：
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

            //3.使用工厂生产SqlSession对象：
            sqlSession = sqlSessionFactory.openSession();
            //设置自动提交：
            //sqlSession = sqlSessionFactory.openSession(true);

            //4.使用SqlSession创建Dao接口的代理对象：
            roleDao = sqlSession.getMapper(RoleDao.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After//在测试方法之后执行
    public void closeResources() {
        try {
            //7.释放资源：
            sqlSession.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestSelectAllRoleWithUsers() {
        //5.使用代理对象执行方法：
        List<RoleEntity> roleEntities = roleDao.selectAllRoleWithUsers();
        roleEntities.forEach(System.err::println);

        //6.提交事务：
        sqlSession.commit();
    }
}
