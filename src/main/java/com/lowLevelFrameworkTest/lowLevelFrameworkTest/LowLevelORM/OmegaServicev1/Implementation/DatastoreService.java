package com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.OmegaServicev1.Implementation;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.OmegaServicev1.Abstract.OmegaDatastoreService;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class DatastoreService implements OmegaDatastoreService {

    @Override
    public Object get(Key key, String kind) {
        return null;
    }

    @Override
    public Object get(String ID, String kind) {
        return null;
    }

    @Override
    public Object put(Object pojo, String id) {

//        Key newKey = KeyFactory.createKey(pojo.getClass().getName(), id);
        Entity entity = new Entity(pojo.getClass().getName(),id);

        return null;
    }

    @Override
    public Object put(Object pojo) {
        return null;
    }

    @Override
    public Object upsert(Object pojo, Key key) {

        get(key,pojo.getClass().getName());
        return null;
    }

    @Override
    public List<Key> put(List<?> pojo) {
        return null;
    }

    @Override
    public boolean delete(Key key) {
        return false;
    }

    @Override
    public boolean delete(List<Key> keys) {
        return false;
    }

    @Override
    public Transaction beginTransaction() {
        return null;
    }

    @Override
    public Transaction finishTransaction() {
        return null;
    }
}
