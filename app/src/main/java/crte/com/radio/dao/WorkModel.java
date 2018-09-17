package crte.com.radio.dao;

import java.util.List;

import crte.com.greendao.WorkDao;
import crte.com.radio.App;
import crte.com.radio.entry.Work;

public class WorkModel {
    private static WorkDao dao = App.instance.getDaoSession().getWorkDao();

    public static long insert(Work work) {
        return dao.insertOrReplace(work);
    }

    public static void update(Work work) {
        dao.update(work);
    }

    public static void deleteById(long id) {
        dao.deleteByKey(id);
    }

    public static List<Work> select(){
        return dao.queryBuilder().list();
    }
}
