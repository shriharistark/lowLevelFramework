package com.lowLevelFrameworkTest.lowLevelFrameworkTest.Utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Util {

    public static<T> boolean hasProperty(Map<String, T> inputProps, String prop){

        if(!inputProps.containsKey(prop) || inputProps.get(prop) == null){
            return false;
        }
        else if(inputProps.get(prop) instanceof Collection && ((Collection)inputProps.get(prop)).isEmpty()){
            return false;
        }
        else if(inputProps.get(prop).getClass().isArray() && ((Object[])inputProps.get(prop)).length <= 0){
            return false;
        }
        else if(String.valueOf(inputProps.get(prop)).length() <= 0){
            return false;
        }

        return true;
    }

    public static<T> boolean hasRequiredProperties(Map<String, T> inputProps, String[] requiredProps){

        for(String prop : requiredProps){
            if(!inputProps.containsKey(prop) || inputProps.get(prop) == null){
                return false;
            }
            else if(inputProps.get(prop) instanceof Collection && ((Collection)inputProps.get(prop)).isEmpty()){
                return false;
            }
            else if(inputProps.get(prop).getClass().isArray() && ((Object[])inputProps.get(prop)).length <= 0){
                return false;
            }
            else if(String.valueOf(inputProps.get(prop)).length() <= 0){
                return false;
            }
        }

        return true;
    }
}
