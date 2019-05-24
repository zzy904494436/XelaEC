package com.qindadai.latte.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by mymac on 2019-05-24.
 * func:
 */
public class DatabaseManager {

    private DaoSession session;
    private UserProfileDao dao;

    private DatabaseManager() {
    }

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }

    public void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database db = helper.getWritableDb();
        session = new DaoMaster(db).newSession();
        dao = session.getUserProfileDao();
    }

    public final UserProfileDao getDao(){
        return dao;
    }


}
