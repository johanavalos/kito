package com.example.hiber_api.controller;

import java.lang.reflect.Field;

public abstract class Controller {

    protected Object convertUsingReflection(Object newObject, Object oldObject) throws IllegalAccessException {
        Field[] newFields = newObject.getClass().getDeclaredFields();
        Field[] oldFields = oldObject.getClass().getDeclaredFields();

        for (int i = 0; i < oldFields.length; i++) {
            newFields[i].setAccessible(true);
            if (newFields[i].get(newObject) == null) {
                oldFields[i].setAccessible(true);
                newFields[i].set(newObject, oldFields[i].get(oldObject));
            }
        }

        return newObject;
    }
}
