package com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM;

import com.google.appengine.api.datastore.*;
import com.lowLevelFrameworkTest.lowLevelFrameworkTest.Utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OmegaService extends Omega {

    @Autowired
    Omega omega;

    private static DatastoreService getDataStorePersistence(){
        return DatastoreServiceFactory.getDatastoreService();
    }
    public <T> Key persist(T object){

        Entity entity = null;
        Key persistedKey = null;

        try {
            if(omega.getFieldsFor(object.getClass()).size() > 0){

                List<Field> fieldList = getFieldsFor(object.getClass());
                Map<String, Object> fieldMaps = new HashMap<>();

                for(Field f : fieldList){
                    f.setAccessible(true);
                    fieldMaps.put(f.getName(), f.get(object));
                }

                String mandatoryProps[] = {"ID"};
                if(Util.hasRequiredProperties(fieldMaps,mandatoryProps)){

                    entity = new Entity(object.getClass().getName(),fieldMaps.get("ID").toString());

                    for(Map.Entry<String, Object> fie : fieldMaps.entrySet()){
                        if(!fie.getKey().equals("ID")){
                            entity.setIndexedProperty(fie.getKey(),fie.getValue());
                        }
                    }
                    persistedKey = getDataStorePersistence().put(entity);

                }else{
                    throw new Exception("Cannot create entity without ID");
                }

                com.google.cloud.datastore.Key key = com.google.cloud.datastore.Key.newBuilder("lowLevelFramework",object.getClass().getName(),fieldMaps.get("ID").toString()).build();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return persistedKey;
    }

    public <T> Object get(String id, Class classEntity){

        try {
            Entity entity = getDataStorePersistence().get(KeyFactory.createKey(classEntity.getName(),id));
            Object object = classEntity.newInstance();

            for(Field f : omega.getFieldsFor(classEntity)){
                if(f.getName().equals("ID")){
                    f.set(object,entity.getKey().toString());
                }else {
                    if(f.getType().getTypeName().equals("int")){
                        f.set(object, Math.toIntExact((long)entity.getProperty(f.getName())));
                    }else {
                        f.set(object, entity.getProperty(f.getName()));
                    }
                }
            }

            return object;
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
