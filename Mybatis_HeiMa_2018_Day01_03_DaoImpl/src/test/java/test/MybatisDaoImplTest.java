package test;

import dogxu.dao.UserDao;
import dogxu.dao.impl.UserDaoImpl;
import dogxu.entity.UserEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author DogXu
 * @date 2020/8/3 16:35
 * @description:mybatis入门案例
 */
public class MybatisDaoImplTest {

    public static void main(String[] args) {
        try {
            //1.读取配置文件：
            InputStream in = Resources.getResourceAsStream("MybatisSqlMapConfig.xml");

            //2.创建SqlSessionFactory工厂：
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

            //3.使用SqlSessionFactory创建Dao对象：
            UserDaoImpl userDaoImpl = new UserDaoImpl(sqlSessionFactory);

            //4.使用代理对象执行方法：
            List<UserEntity> userEntities = userDaoImpl.selectAll();
            System.err.println(userEntities);

            //5.释放资源：
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
