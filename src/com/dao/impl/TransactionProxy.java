package com.dao.impl;

import com.resources.BeansDispatcher;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

public class TransactionProxy implements InvocationHandler {
    TransactionProxy() {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object implementedObject = BeansDispatcher.getBean(method.getDeclaringClass().getName());
        if(method.getAnnotation(Transactional.class)==null) return method.invoke(implementedObject, args);
        Connection connection = JDBCDaoFactory.getConnection();
        try {
            connection.setAutoCommit(false);
            Object result = method.invoke(implementedObject, args);
            connection.commit();
            return result;
        }catch (Exception e){
            connection.rollback();
            throw e;
        }
    }
}
