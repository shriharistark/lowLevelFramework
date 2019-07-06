package com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM;

import com.google.cloud.datastore.*;
import com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.OmegaAnnotations.OmegaEntity;
import com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.OmegaException.EntityAlreadyRegisteredException;
import com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.OmegaException.EntityNotRegisteredException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

@Component
public class Omega {

    private static Map<String, List<Field>> mapper;
    private static String projectID;

    public static void setProjectID(String id) throws IllegalAccessException {
        if(id == null || id.isEmpty()){
            throw new IllegalArgumentException("Project ID cannot be null or empty");
        }
        else if(projectID != null){
            throw new IllegalAccessException("Project Id is already set with "+projectID);
        }
        else {
            projectID = id;
        }
    }

    public static void register(Class classEntity) throws Exception{
        if(mapper == null){
            mapper = new LinkedHashMap<>();
        }

        if(mapper.containsKey(classEntity.getName())){
            throw new EntityAlreadyRegisteredException(classEntity.getName()+" is already registered!");
        }

        if(!classEntity.isAnnotationPresent(OmegaEntity.class)){
            throw new EntityNotRegisteredException("Make sure to annotate "+classEntity.getName()+" with @OmegaEntity");
        }

        else {
//            System.out.println("\n-----\n"+classEntity.getCanonicalName()+" has "+" omega entity");

            mapper.put(classEntity.getName(),Arrays.asList(classEntity.getDeclaredFields()));
            System.out.println(classEntity.getName()+ " is registered!");

//            System.out.println("\n------------\nPrinting Registered Classes and respective fields");
//            for(Map.Entry<String, List<Field>> entry : mapper.entrySet()){
//                System.out.println("Fields for "+entry.getKey()+": ");
//                for(Field k : entry.getValue()){
//                    System.out.print(k.getName()+" ");
//                }
//            }


        }
    }

    private static List<Field> getFieldsFor(Class classEntity) throws EntityNotRegisteredException{
        if(mapper == null){
            System.out.println("The mapper is not yet called or initiated!");
        }
        if(!mapper.containsKey(classEntity.getName())){
            throw new EntityNotRegisteredException("Register your class first! "+classEntity.getName()+" is not registered");
        }else {
            return mapper.get(classEntity.getName());
        }
    }

    public static List<Field> getFieldsForThe(Class classEntity){
        return Collections.unmodifiableList(getFieldsForThe(classEntity));
    }

//    protected<T> Object doCast(Entity datastoreEntity, Field field){
//
//        Object result = null;
//
//        try {
//
//            switch (field.getType().getTypeName().toLowerCase()){
//
//                case "boolean":
//                    result = datastoreEntity.getBoolean(field.getName());
//                    break;
//
//                case "blob":
//                    result = datastoreEntity.getBlob(field.getName());
//                    break;
//
//                case "double":
//                    result = datastoreEntity.getDouble(field.getName());
//                    break;
//
//                case "latlng":
//                    result = datastoreEntity.getLatLng(field.getName());
//                    break;
//
//                case "list":
//                    result = datastoreEntity.getList(field.getName());
//                    break;
//
//                case "long":
//                    result = datastoreEntity.getLong(field.getName());
//                    break;
//
//                case "string":
//                    result = datastoreEntity.getString(field.getName());
//                    break;
//
//                case "timestamp":
//                    result = datastoreEntity.getTimestamp(field.getName());
//                    break;
//
//                case "value":
//                    result = datastoreEntity.getValue(field.getName());
//                    break;
//
//                case "key":
//                    result = datastoreEntity.getKey().toUrlSafe();
//                    break;
//
//                default:
//                    throw new IllegalArgumentException(field.getType().getTypeName()+ "is unrecognizable for Datastore");
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
//
//    @SuppressWarnings("Unchecked")
//    protected <T> FullEntity buildEntityFromPojov2(T pojoEntity) throws Exception {
//
//        List<Field> fields = getFieldsFor(pojoEntity.getClass());
//
//        Field id = fields.parallelStream().filter(f -> "id".equals(f.getName().toLowerCase())).findAny().orElse(null);
//
//        if(id != null && id.getType().getTypeName().toLowerCase().equals("string")) {
//            String urlSafeKey = id.get(pojoEntity).toString();
//
//            Entity.Builder builder = Entity.newBuilder(Key.newBuilder(projectID, pojoEntity.getClass().getName(), urlSafeKey).build());
//
//            for (Field field : getFieldsFor(pojoEntity.getClass())) {
//
//                if(!field.getName().toLowerCase().equals("id")){
//
//                    switch (field.getType().getTypeName().toLowerCase()){
//
//                        case "string":
//                            builder.set(field.getName(),field.get(pojoEntity).toString());
//                            break;
//
//                        case "integer":
//                            builder.set(field.getName(),field.getInt(pojoEntity));
//                            break;
//
//                        case "long":
//                            builder.set(field.getName(),field.getLong(pojoEntity));
//                            break;
//
//                        case "float":
//                            builder.set(field.getName(),field.getFloat(pojoEntity));
//                            break;
//
//                        case "double":
//                            builder.set(field.getName(),field.getDouble(pojoEntity));
//                            break;
//
//                        case "boolean":
//                            builder.set(field.getName(),field.getBoolean(pojoEntity));
//                            break;
//
//                        case "list":
//                            if(field.getGenericType() instanceof Value<?>){
//                                builder.set(field.getName(),(List<? extends Value<?>>)field.get(pojoEntity));
//                            }
//                            break;
//
//                        default:
//                            throw new IllegalArgumentException(field.getType().getTypeName()+" is not recognizable for datastore v2");
//                    }
//                }
//            }
//
//            return builder.build();
//
//        }else{
//            throw new IllegalAccessException("Id field is not present or is invalid.");
//        }
//
//    }



}
