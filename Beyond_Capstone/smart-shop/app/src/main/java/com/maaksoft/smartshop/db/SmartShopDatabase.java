package com.maaksoft.smartshop.db;

import android.content.Context;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.maaksoft.smartshop.dao.ContactDao;
import com.maaksoft.smartshop.dao.SettingDao;
import com.maaksoft.smartshop.dao.ShopListContactsDao;
import com.maaksoft.smartshop.dao.StoreDao;
import com.maaksoft.smartshop.dao.ShopListDao;

import com.maaksoft.smartshop.model.Contact;
import com.maaksoft.smartshop.model.Setting;
import com.maaksoft.smartshop.model.ShopListContact;
import com.maaksoft.smartshop.model.Store;
import com.maaksoft.smartshop.model.ShopList;

@Database(entities = { ShopList.class, Store.class, Setting.class, Contact.class, ShopListContact.class }, version = 6, exportSchema = false)
public abstract class SmartShopDatabase extends RoomDatabase {

    private static SmartShopDatabase smartShopDatabase;

    public abstract ShopListDao shopListDao();

    public abstract StoreDao storeDao();

    public abstract SettingDao settingDao();

    public abstract ContactDao contactDao();

    public abstract ShopListContactsDao shopListContactsDao();

    public static SmartShopDatabase getDatabase(Context context) {

        if (smartShopDatabase == null) {
            smartShopDatabase = Room.databaseBuilder(context, SmartShopDatabase.class, "smart_shop")
                                //Room.inMemoryDatabaseBuilder(context.getApplicationContext(), SmartShopDatabase.class)
                                // To simplify the exercise, allow queries on the main thread.
                                // Don't do this on a real app!
                                .allowMainThreadQueries()
                                // recreate the database if necessary
                                .fallbackToDestructiveMigration()
                                .build();
        }
        return smartShopDatabase;

    }

    public static void destroyInstance() {
        smartShopDatabase = null;
    }

}