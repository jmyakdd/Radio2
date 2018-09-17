package crte.com.radio.dao;

import java.util.List;

import crte.com.greendao.ContactDbDao;
import crte.com.radio.App;
import crte.com.radio.entry.ContactDb;

public class ContactDbModel {
    private static ContactDbDao dao = App.instance.getDaoSession().getContactDbDao();

    public static long insert(String name, Integer age) {
        ContactDb testUser = new ContactDb(name, age);
        return dao.insert(testUser);
    }

    public static long insert(ContactDb user) {
        return dao.insertOrReplace(user);
    }

    public static ContactDb selectUserById(long id) {
        List<ContactDb> list = dao.queryBuilder().where(ContactDbDao.Properties.Id.eq(id)).list();
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public static List<ContactDb> selectUserList() {
        return dao.queryBuilder().list();
    }

    public static List<ContactDb> selectUserList(String name) {
        return dao.queryBuilder().where(ContactDbDao.Properties.Name.eq(name))
                .list();
    }

    public static void deleteById(long id) {
        dao.deleteByKey(id);
    }

    public static void deleteByObject(ContactDb user) {
        dao.delete(user);
    }

    public static void update(ContactDb user) {
        dao.update(user);
    }
}
