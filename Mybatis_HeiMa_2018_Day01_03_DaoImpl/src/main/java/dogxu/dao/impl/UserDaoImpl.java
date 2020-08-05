package dogxu.dao.impl;

import dogxu.dao.UserDao;
import dogxu.entity.UserEntity;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author DogXu
 * @date 2020/8/3 17:28
 * @description:
 */
public class UserDaoImpl implements UserDao {

    private SqlSession sqlSession;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSession = sqlSessionFactory.openSession();
    }

    @Override
    public List<UserEntity> selectAll() {
        List<UserEntity> users = sqlSession.selectList("com.dogxu.dao.UserDao.selectAll");

        sqlSession.close();

        return users;
    }
}
