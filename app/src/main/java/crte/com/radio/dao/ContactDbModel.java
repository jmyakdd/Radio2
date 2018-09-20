package crte.com.radio.dao;

import java.util.List;

import crte.com.greendao.ContactDao;
import crte.com.radio.App;
import crte.com.radio.entry.Contact;

public class ContactDbModel {
    private static ContactDao dao = App.instance.getDaoSession().getContactDao();

    public static long insert(String name, Integer age) {
        Contact testUser = new Contact(name, age);
        return dao.insert(testUser);
    }

    public static long insert(Contact user) {
        return dao.insertOrReplace(user);
    }

    public static Contact selectUserById(long id) {
        List<Contact> list = dao.queryBuilder().where(ContactDao.Properties.Id.eq(id)).list();
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public static List<Contact> selectUserList() {
        return dao.queryBuilder().list();
    }

    public static List<Contact> selectUserList(String name) {
        return dao.queryBuilder().where(ContactDao.Properties.Name.eq(name))
                .list();
    }

    public static void deleteById(long id) {
        dao.deleteByKey(id);
    }

    public static void deleteByObject(Contact user) {
        dao.delete(user);
    }

    public static void update(Contact user) {
        dao.update(user);
    }
}
