package com.dogxu.test;

import com.dogxu.dao.UserDao;
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

/**
 * @author DogXu
 * @date 2020/8/4 14:21
 * @description:
 */
public class MybatisUserDaoTest {

    private InputStream in;
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;
    private UserDao userDao;

    @Before//在测试方法之前执行
    public void initResorces() {
        try {
            //1.读取配置文件：
            in = Resources.getResourceAsStream("MybatisSqlMapConfig.xml");

            //2.创建SqlSessionFactory工厂：
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

            //3.使用工厂生产SqlSession对象：
            sqlSession = sqlSessionFactory.openSession();
            //设置自动提交：
            //sqlSession = sqlSessionFactory.openSession(true);

            //4.使用SqlSession创建Dao接口的代理对象：
            userDao = sqlSession.getMapper(UserDao.class);
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
    public void TestSelectByIdWithFirstLevelCache() throws IOException {
        //TODO Mybatis一级缓存详解：
        //指的是Mybatis中SqlSession对象的缓存。
        //在commit之前只有一次select查询，即第二次查询使用的是缓存；
        //在commit之后会再次进行select查询，即第三次会重新查询数据库，不使用缓存。
        //一级缓存是SqlSession范围的缓存，当调用SqlSession的修改数据、添加数据、删除数据、commit、close等方法时就会清空；
        //即当有修改数据的操作时就会清空缓存，这样是为了让缓存中存储的是最新的数据，避免脏读。
        //手动清空一级缓存：sqlSession.clearCache()

        //5.使用代理对象执行方法：
        UserEntity userEntity1 = userDao.selectByIdWithFirstLevelCache(41);
        System.err.println("First Select --- "+ userEntity1);

        UserEntity userEntity2 = userDao.selectByIdWithFirstLevelCache(41);
        System.err.println("Second Select --- "+ userEntity2);

        //6.提交事务：
        sqlSession.commit();
        //sqlSession.clearCache();//清空缓存

        UserEntity userEntity3 = userDao.selectByIdWithFirstLevelCache(41);
        System.err.println("Third Select --- "+ userEntity3);
    }

    @Test
    public void TestSelectByIdWithSecondLevelCache() {
        //TODO Mybatis二级缓存详解：
        //指的是Mybatis中SqlSessionFactory对象的缓存。由同一个SqlSessionFactory对象创建的SqlSession共享其缓存。
        //二级缓存的使用步骤：
//        第一步：让Mybatis框架支持二级缓存（在SqlMapConfig.xml中配置）
//        第二步：让当前的映射文件支持二级缓存（在IUserDao.xml中配置）
//        第三步：让当前的操作支持二级缓存（在select标签中配置）

        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserDao userDao1 = sqlSession1.getMapper(UserDao.class);
        UserEntity userEntity1 = userDao1.selectByIdWithSecondLevelCache(41);
        System.err.println("sqlSession1 --- "+ userEntity1);
        sqlSession1.close();//一级缓存消失

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserDao userDao2 = sqlSession2.getMapper(UserDao.class);
        UserEntity userEntity2 = userDao2.selectByIdWithSecondLevelCache(41);
        System.err.println("sqlSession2 --- "+ userEntity2);
        sqlSession2.close();

        //上述运行结果说明一级缓存失效后第二次查询使用了二级缓存。

        System.err.println(userEntity1 == userEntity2);

        //这个结果False说明了二级缓存存的是数据而不是对象，在缓存中查出数据后会再封装成对象。
    }
}
