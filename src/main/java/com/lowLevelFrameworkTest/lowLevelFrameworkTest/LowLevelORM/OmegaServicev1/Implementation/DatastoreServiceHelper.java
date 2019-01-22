package com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.OmegaServicev1.Implementation;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.Omega;
import com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.OmegaAnnotations.OmegaEntity;
import com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.OmegaException.EntityNotRegisteredException;
import com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.OmegaServicev1.Abstract.OmegaDatastoreServiceHelper;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class DatastoreServiceHelper implements OmegaDatastoreServiceHelper {

    @Override
    public Entity pojoToDatastoreEntity(Object pojo) throws EntityNotRegisteredException {

        if(pojo.getClass().isAnnotationPresent(OmegaEntity.class)){
            throw new EntityNotRegisteredException(pojo.getClass().getName()+" is not registered.");
        }

        Entity entity = new Entity(pojo.getClass().getName());
        List<Field> pojoFields = Omega.getFieldsForThe(pojo.getClass());    //this cannot be modified

        Field idField = pojoFields.parallelStream().filter(f -> f.getName().equalsIgnoreCase("ID")).findAny().orElse(null);

        Key key = null;
        try {
            key = idField != null ? KeyFactory.createKey(pojo.getClass().getName(), idField.get(pojo).toString()) : null;
        }
        catch(IllegalAccessException e){
            e.printStackTrace();
        }

        if(key != null){

        }

        return null;
    }

    @Override
    public Entity pojoToDatastoreEntity(Object pojo, String id) throws EntityNotRegisteredException {
        return null;
    }


    @Override
    public Object datastoreEntityToPojo(Entity entity) {
        return null;
    }

    @Override
    public Object castToFieldType(Object object, Field f) {
        return null;
    }
}
