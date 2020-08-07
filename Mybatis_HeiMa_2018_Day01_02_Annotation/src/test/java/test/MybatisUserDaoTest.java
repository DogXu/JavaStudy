package test;

import dogxu.dao.UserDao;
import dogxu.entity.UserEntity;
import dogxu.entity.UserEntity2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author DogXu
 * @date 2020/8/4 14:21
 * @description:
 */
public class MybatisUserDaoTest {

    private InputStream in;
    private SqlSession sqlSession;
    private SqlSessionFactory sqlSessionFactory;
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
    public void TestInsertUser() throws IOException {
        //5.使用代理对象执行方法：
        UserEntity user = new UserEntity();
        user.setUsername("TestInsertUser");
        user.setSex("男");
        user.setAddress("北京朝阳区");
        user.setBirthday(new Date());
        userDao.saveUser(user);

        //6.提交事务：
        sqlSession.commit();
    }

    @Test
    public void TestUpdateUser() throws IOException {
        //5.使用代理对象执行方法：
        UserEntity user = new UserEntity();
        user.setId(54);
        user.setUsername("TestUpdateUser");
        user.setSex("女");
        user.setAddress("北京朝阳区");
        user.setBirthday(new Date());
        userDao.updateUser(user);

        //6.提交事务：
        sqlSession.commit();
    }

    @Test
    public void TestDeleteUser() throws IOException {
        //5.使用代理对象执行方法：
        userDao.deleteUserById(57);

        //6.提交事务：
        sqlSession.commit();
    }

    @Test
    public void TestSelectAll() throws IOException {
        //5.使用代理对象执行方法：
        List<UserEntity> userEntities = userDao.selectAll();
        userEntities.forEach(System.err::println);
    }

    @Test
    public void TestSelectAllToUser2() throws IOException {
        //5.使用代理对象执行方法：
        List<UserEntity2> userEntities = userDao.selectAllToUser2();
        userEntities.forEach(System.err::println);
    }

    @Test
    public void TestSelectByIdToUser2() throws IOException {
        //5.使用代理对象执行方法：
        UserEntity2 user2 = userDao.selectByIdToUser2(41);
        System.err.println(user2);
    }

    @Test
    public void TestSelectAllUserWithAccounts() throws IOException {
        //5.使用代理对象执行方法：
        List<UserEntity> userEntities = userDao.selectAllUserWithAccounts();
        userEntities.forEach(System.err::println);
    }

    @Test
    public void TestSelectByIdWithSecondLevelCache() {
        //TODO Mybatis二级缓存详解：
        //指的是Mybatis中SqlSessionFactory对象的缓存。由同一个SqlSessionFactory对象创建的SqlSession共享其缓存。
        //注解二级缓存的使用步骤：
//        第一步：让Mybatis框架支持二级缓存（在SqlMapConfig.xml中配置，默认为true）
//        第二步：让当前的映射文件支持二级缓存（在UserDao接口中配置：@CacheNamespace(blocking = true)//开启二级缓存支持）

        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserDao userDao1 = sqlSession1.getMapper(UserDao.class);
        UserEntity userEntity1 = userDao1.selectById(41);
        System.err.println("sqlSession1 --- "+ userEntity1);
        sqlSession1.close();//一级缓存消失

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserDao userDao2 = sqlSession2.getMapper(UserDao.class);
        UserEntity userEntity2 = userDao2.selectById(41);
        System.err.println("sqlSession2 --- "+ userEntity2);
        sqlSession2.close();

        //上述运行结果说明一级缓存失效后第二次查询使用了二级缓存。

        System.err.println(userEntity1 == userEntity2);

        //这个结果False说明了二级缓存存的是数据而不是对象，在缓存中查出数据后会再封装成对象。
    }
}
