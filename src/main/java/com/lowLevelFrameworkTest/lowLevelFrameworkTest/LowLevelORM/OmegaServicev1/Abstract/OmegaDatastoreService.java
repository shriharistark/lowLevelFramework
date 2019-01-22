package com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.OmegaServicev1.Abstract;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

import java.util.List;

public interface OmegaDatastoreService {

    Object get(Key key, String kind);
    Object get(String ID, String kind);

    Object put(Object pojo, String id);
    Object put(Object pojo);        //auto-generate numeric Id
    Object upsert(Object pojo, Key key);
    List<Key> put(List<?> pojo);

    boolean delete(Key key);
    boolean delete(List<Key> keys);

    Transaction beginTransaction();
    Transaction finishTransaction();
}
