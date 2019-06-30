package com.example.beleskazadatakja.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {


    private static String DATABASE_NAME="beleske1";
    private static int DATABASE_VERSION=1;

    Dao<Beleksa, Integer> mBeleskaDao = null;


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, Beleksa.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            TableUtils.dropTable(connectionSource, Beleksa.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Beleksa, Integer> getmBeleskaDao()throws SQLException{
        if (mBeleskaDao == null){
            mBeleskaDao= getDao(Beleksa.class);
        }
        return mBeleskaDao;
    }

    @Override
    public void close() {
        mBeleskaDao=null;
        super.close();
    }
}
