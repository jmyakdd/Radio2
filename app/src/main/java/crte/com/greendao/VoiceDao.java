package crte.com.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import crte.com.radio.entry.Voice;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "VOICE".
*/
public class VoiceDao extends AbstractDao<Voice, Long> {

    public static final String TABLENAME = "VOICE";

    /**
     * Properties of entity Voice.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Num = new Property(1, Long.class, "num", false, "NUM");
        public final static Property FileName = new Property(2, String.class, "fileName", false, "FILE_NAME");
        public final static Property FilePath = new Property(3, String.class, "filePath", false, "FILE_PATH");
    }


    public VoiceDao(DaoConfig config) {
        super(config);
    }
    
    public VoiceDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"VOICE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NUM\" INTEGER," + // 1: num
                "\"FILE_NAME\" TEXT," + // 2: fileName
                "\"FILE_PATH\" TEXT);"); // 3: filePath
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"VOICE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Voice entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long num = entity.getNum();
        if (num != null) {
            stmt.bindLong(2, num);
        }
 
        String fileName = entity.getFileName();
        if (fileName != null) {
            stmt.bindString(3, fileName);
        }
 
        String filePath = entity.getFilePath();
        if (filePath != null) {
            stmt.bindString(4, filePath);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Voice entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long num = entity.getNum();
        if (num != null) {
            stmt.bindLong(2, num);
        }
 
        String fileName = entity.getFileName();
        if (fileName != null) {
            stmt.bindString(3, fileName);
        }
 
        String filePath = entity.getFilePath();
        if (filePath != null) {
            stmt.bindString(4, filePath);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Voice readEntity(Cursor cursor, int offset) {
        Voice entity = new Voice( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // num
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // fileName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // filePath
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Voice entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNum(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setFileName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFilePath(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Voice entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Voice entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Voice entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}