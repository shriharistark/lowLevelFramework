package com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM;

import java.lang.reflect.Field;

public class ObjectCast {

    private static <T> String getAsString(Field field, T pojoEntity) throws IllegalAccessException {
        if(field.getType().getTypeName().toLowerCase().equals("string")){
            return field.get(pojoEntity).toString();
        }

        return null;
    }

    private static <T> Integer getAsInteger(Field field, T pojoEntity) throws IllegalAccessException {
        if(field.getType().getTypeName().toLowerCase().equals("integer")){
            return field.getInt(pojoEntity);
        }

        return null;
    }

    private static <T> Long getLong(Field field, T pojoEntity) throws IllegalAccessException {
        if(field.getType().getTypeName().toLowerCase().equals("long")){
            return field.getLong(pojoEntity);
        }

        return null;
    }

    private static <T> Boolean getBoolean(Field field, T pojoEntity) throws IllegalAccessException {
        if(field.getType().getTypeName().toLowerCase().equals("boolean")){
            return field.getBoolean(pojoEntity);
        }

        return null;
    }

    private static <T> Double getDouble(Field field, T pojoEntity) throws IllegalAccessException {
        if(field.getType().getTypeName().toLowerCase().equals("double")){
            return field.getDouble(pojoEntity);
        }

        return null;
    }

    private static <T> Float getFloat(Field field, T pojoEntity) throws IllegalAccessException {
        if(field.getType().getTypeName().toLowerCase().equals("float")){
            return field.getFloat(pojoEntity);
        }

        return null;
    }
}
