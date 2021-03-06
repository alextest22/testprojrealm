package com.test.testprojrealm.data.dataBase;

import android.util.Log;

import io.reactivex.functions.Consumer;
import io.realm.Realm;

public class RealmHelper {

    RealmHelper() {
    }

    void executeTransaction(Consumer<Realm> transaction) {
        try {
            Realm realm = Realm.getDefaultInstance();
            if (realm != null) {
                try {
                    if (!realm.isInTransaction()) realm.beginTransaction();
                    if (realm.isInTransaction() && !realm.isClosed() && transaction != null) transaction.accept(realm);
                    if (realm.isInTransaction()) realm.commitTransaction();
                } catch (Exception e) {
                    Log.e("RealmException", "", e);
                } finally {
                    if (!realm.isClosed()) realm.close();
                }
            }
        } catch (Exception e) {
            Log.e("RealmException", "", e);
        }
    }
}
