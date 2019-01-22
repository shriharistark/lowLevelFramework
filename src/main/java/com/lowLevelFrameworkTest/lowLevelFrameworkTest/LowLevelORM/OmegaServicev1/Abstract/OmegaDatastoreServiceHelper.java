package com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.OmegaServicev1.Abstract;

import com.google.appengine.api.datastore.Entity;
import com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.OmegaException.EntityNotRegisteredException;

import java.lang.reflect.Field;

public interface OmegaDatastoreServiceHelper {

    Entity pojoToDatastoreEntity(Object pojo) throws EntityNotRegisteredException;  //for put
    Entity pojoToDatastoreEntity(Object pojo, String id) throws EntityNotRegisteredException;  //for put

    Object datastoreEntityToPojo(Entity entity);//for get
    Object castToFieldType(Object object, Field f);

}
