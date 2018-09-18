package crte.com.radio.dao;

import java.util.List;

import crte.com.greendao.MessageDao;
import crte.com.radio.App;
import crte.com.radio.entry.Message;

public class MessageModel {
    private static MessageDao dao = App.instance.getDaoSession().getMessageDao();

    public static long insert(Message data) {
        return dao.insertOrReplace(data);
    }

    public static void update(Message data) {
        dao.update(data);
    }

    public static void deleteById(long id) {
        dao.deleteByKey(id);
    }

    public static List<Message> select(){
        return dao.queryBuilder().list();
    }
}
