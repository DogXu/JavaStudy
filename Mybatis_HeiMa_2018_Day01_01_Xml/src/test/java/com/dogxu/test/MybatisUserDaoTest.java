package com.dogxu.test;

import com.dogxu.dao.UserDao;
import com.dogxu.entity.UserEntity;
import com.dogxu.entity.UserEntity2;
import com.dogxu.entity.UserQueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.After;

/**
 * @author DogXu
 * @date 2020/8/4 14:21
 * @description:
 */
public class MybatisUserDaoTest {

    private InputStream in;
    private SqlSession sqlSession;
    private UserDao userDao;

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
        userDao.saveUserWithoutReturnId(user);

        System.err.println("TestInsertUser --- saveUserWithReturnId - Before - user="+ user);
        int id = userDao.saveUserWithReturnId(user);
        System.err.println("TestInsertUser --- saveUserWithReturnId - id="+ id);
        System.err.println("TestInsertUser --- saveUserWithReturnId - After - user="+ user);

        //6.提交事务：
        sqlSession.commit();
    }

    @Test
    public void TestUpdateUser() throws IOException {
        //5.使用代理对象执行方法：
        UserEntity user = new UserEntity();
        user.setId(52);
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
        UserEntity user = new UserEntity();
        user.setId(53);
        user.setUsername("TestDeleteUser");
        user.setSex("女");
        user.setAddress("北京朝阳区");
        user.setBirthday(new Date());
        userDao.deleteUser(user);

        //6.提交事务：
        sqlSession.commit();
    }

    @Test
    public void TestSelectByUsername() throws IOException {
        //5.使用代理对象执行方法：
        List<UserEntity> userEntities = userDao.selectByUsernameWithPercent("%王%");
        userEntities.forEach(System.err::println);

        System.err.println("****************************");

        List<UserEntity> users = userDao.selectByUsernameWithoutPercent("王");
        users.forEach(System.err::println);

        System.err.println("****************************");

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("老王");
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setId(46);
        userQueryVo.setUser(userEntity);
        List<UserEntity> usersByVo = userDao.selectByVo(userQueryVo);
        usersByVo.forEach(System.err::println);
    }

    @Test
    public void TestSelectAllToUserMap() {
        List<UserEntity2> userEntities = userDao.selectAllToUserMap();
        userEntities.forEach(System.err::println);
    }

    @Test
    public void TestSelectByCondition() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("%王%");
        userEntity.setAddress("北京");
        userEntity.setId(46);
        List<UserEntity> userEntities = userDao.selectByConditionWithIf(userEntity);
        userEntities.forEach(System.err::println);

        System.err.println("*********");

        List<UserEntity> userEntitiesWhere = userDao.selectByConditionWithWhere(userEntity);
        userEntitiesWhere.forEach(System.err::println);
    }

    @Test
    public void TestSelectByVo() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("老王");
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setId(46);
        userQueryVo.setUser(userEntity);
        List<UserEntity> usersByVo = userDao.selectByVo(userQueryVo);
        usersByVo.forEach(System.err::println);

        System.err.println("***************************");

        List<Integer> ids = new ArrayList<>();
        ids.add(41);
        ids.add(45);
        userQueryVo.setIds(ids);
        List<UserEntity> userEntities = userDao.selectByForeach(userQueryVo);
        userEntities.forEach(System.err::println);
    }

}
