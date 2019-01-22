package com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM;

import com.google.cloud.datastore.*;
import com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.OmegaAnnotations.OmegaEntity;

import java.lang.reflect.Field;

public class OmegaServiceV2 {

    private final Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    private final Omega omega = new Omega();

    public <T> boolean persist(T object){

        return false;
    }

    public <T> boolean put(T object){

        try {
//            FullEntity.Builder<IncompleteKey> entity = Entity.newBuilder().set("ok",true);
            FullEntity entity = omega.buildEntityFromPojov2(object);
            datastore.put(entity);

            if (object.getClass().isAnnotationPresent((OmegaEntity.class))) {

                for(Field attr : omega.getFieldsFor(object.getClass())) {
                    attr.setAccessible(true);

                }

            } else {
                System.out.println(object.getClass().getCanonicalName() + " is not annotated with omega entity");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public Object get(String ID, Class kind){

        Key keyFactory      = datastore.newKeyFactory().newKey(ID);
        Entity resultant    = datastore.get(keyFactory);

        try {
            Object output       = kind.newInstance();

            for(Field attribute : omega.getFieldsFor(kind)){
                if(omega.doCast(resultant,attribute) != null){
                    attribute.set(output,omega.doCast(resultant,attribute));
                }
            }

            return output;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
