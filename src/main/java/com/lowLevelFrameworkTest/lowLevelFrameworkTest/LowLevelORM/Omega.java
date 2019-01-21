package com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM;

import com.google.cloud.datastore.*;
import com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.OmegaAnnotations.OmegaEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

@Component
public class Omega {

    private static Map<String, List<Field>> mapper;
    private static final String projectID = "LowLevelFrameworkTest";

    public static void register(Class classEntity) throws Exception{
        if(mapper == null){
            mapper = new LinkedHashMap<>();
        }

        if(mapper.containsKey(classEntity.getCanonicalName())){
            System.out.println(classEntity.getName() + "is already registered!");
            return;
        }

        if(classEntity.isAnnotationPresent(OmegaEntity.class)){
            System.out.println("\n-----\n"+classEntity.getCanonicalName()+" has "+" omega entity");
            mapper.put(classEntity.getCanonicalName(), Arrays.asList(classEntity.getDeclaredFields()));

            System.out.println("\n------------\nPrinting Registered Classes and respective fields");
            for(Map.Entry<String, List<Field>> entry : mapper.entrySet()){
                System.out.println("Fields for "+entry.getKey()+": ");
                for(Field k : entry.getValue()){
                    System.out.print(k.getName()+" ");
                }
            }


        }else {
            throw new Exception("Make sure annotate your class with @OmegaEntity ...");
        }
    }

    public List<Field> getFieldsFor(Class classEntity) throws Exception{
        if(mapper == null){
            System.out.println("The mapper is not yet called or initiated!");
        }
        if(!mapper.containsKey(classEntity.getCanonicalName())){
            throw new Exception("Register your class first!");
        }else {
            return mapper.get(classEntity.getCanonicalName());
        }
    }

    protected<T> Object doCast(Entity datastoreEntity, Field field){

        Object result = null;

        try {

            switch (field.getType().getTypeName().toLowerCase()){

                case "boolean":
                    result = datastoreEntity.getBoolean(field.getName());
                    break;

                case "blob":
                    result = datastoreEntity.getBlob(field.getName());
                    break;

                case "double":
                    result = datastoreEntity.getDouble(field.getName());
                    break;

                case "latlng":
                    result = datastoreEntity.getLatLng(field.getName());
                    break;

                case "list":
                    result = datastoreEntity.getList(field.getName());
                    break;

                case "long":
                    result = datastoreEntity.getLong(field.getName());
                    break;

                case "string":
                    result = datastoreEntity.getString(field.getName());
                    break;

                case "timestamp":
                    result = datastoreEntity.getTimestamp(field.getName());
                    break;

                case "value":
                    result = datastoreEntity.getValue(field.getName());
                    break;

                case "key":
                    result = datastoreEntity.getKey().toUrlSafe();
                    break;

                default:
                    throw new IllegalArgumentException(field.getType().getTypeName()+ "is unrecognizable for Datastore");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    protected <T> Entity buildEntityFromPojo(T pojoEntity, Object object) throws Exception {

        List<Field> fields = getFieldsFor(pojoEntity.getClass());

        Field id = fields.parallelStream().filter(f -> "id".equals(f.getName().toLowerCase())).findAny().orElse(null);

        if(id != null && id.getType().getTypeName().toLowerCase().equals("string")) {
            String urlSafeKey = id.get(object).toString();

            Entity.Builder builder = Entity.newBuilder(Key.newBuilder(projectID, pojoEntity.getClass().getName(), urlSafeKey).build());
            for (Field field : getFieldsFor(pojoEntity.getClass())) {
                if(!field.getName().toLowerCase().equals("id")){
                    builder.set(field.getName(),ObjectCast.)
                }
            }
        }

        return null;
    }



}
