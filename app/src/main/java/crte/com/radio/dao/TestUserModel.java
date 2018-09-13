package crte.com.radio.dao;

import java.util.List;

import crte.com.greendao.TestUserDao;
import crte.com.radio.App;
import crte.com.radio.entry.TestUser;

public class TestUserModel {
    private static TestUserDao dao = App.instance.getDaoSession().getTestUserDao();

    public static long insert(String name, Integer age) {
        TestUser testUser = new TestUser(name, age);
        return dao.insert(testUser);
    }

    public static long insert(TestUser user) {
        return dao.insertOrReplace(user);
    }

    public static TestUser selectUserById(long id) {
        List<TestUser> list = dao.queryBuilder().where(TestUserDao.Properties.Id.eq(id)).list();
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public static List<TestUser> selectUserList() {
        return dao.queryBuilder().list();
    }

    public static List<TestUser> selectUserList(String name) {
        return dao.queryBuilder().where(TestUserDao.Properties.Name.eq(name))
                .list();
    }

    public static void deleteById(long id) {
        dao.deleteByKey(id);
    }

    public static void deleteByObject(TestUser user) {
        dao.delete(user);
    }

    public static void update(TestUser user) {
        dao.update(user);
    }
}
