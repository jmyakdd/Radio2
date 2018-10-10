package crte.com.radio.dao;

import java.util.List;

import crte.com.greendao.ZoneDao;
import crte.com.radio.App;
import crte.com.radio.entry.Zone;

public class ZoneModel {
    private static ZoneDao zoneDao = App.instance.getDaoSession().getZoneDao();

    public static long insert(Zone zone) {
        return zoneDao.insertOrReplace(zone);
    }

    public static void update(Zone zone) {
        zoneDao.update(zone);
    }

    public static void deleteById(long id) {
        zoneDao.deleteByKey(id);
    }

    public static List<Zone> select() {
        return zoneDao.queryBuilder().list();
    }
}
