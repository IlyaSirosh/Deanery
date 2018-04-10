package com.resources;

import com.controllers.configs.MainController;
import com.controllers.decorators.Controller;
import com.controllers.decorators.View;
import com.controllers.exceptions.UnsatisfiedDependencyException;
import com.dao.impl.JDBCDaoFactory;
import com.dao.impl.Repository;
import com.dao.impl.Transactional;
import com.services.configs.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class BeansDispatcher {
    private static Map<Class, Object> instantiatedBeans = new HashMap<>();
    private static List<Class> secondLevelClasses;

    public static void instantiateControllers() throws Exception{
        secondLevelClasses = new ArrayList<>();
        Class[] beans = getClasses("com");
        List<Class> controllers = new ArrayList<>();
        List<com.ui.View> views = new ArrayList<>();
        List<Class> transactions = new ArrayList<>();
        for(Class bean: beans){
            if(bean.getAnnotation(Controller.class)==null && bean.getAnnotation(Service.class)==null && bean.getAnnotation(View.class)==null && bean.getAnnotation(Repository.class)==null) continue;
            if(bean.getAnnotation(View.class)!=null) views.add((com.ui.View)bean.newInstance());
            if(bean.getAnnotation(Controller.class)!=null) controllers.add(bean);
            for(Method m: bean.getMethods()){
                if(m.getAnnotation(Transactional.class)!=null){
                    transactions.add(bean);
                    break;
                }
            }
            Object beanInstance = null;
            try {
                beanInstance = instantiateBean(bean);
                if(beanInstance==null) continue;
            }catch (UnsatisfiedDependencyException e){
                throw e;
            }
            for(Class interfaceItem: bean.getInterfaces())
                instantiatedBeans.put(interfaceItem, beanInstance);
            instantiatedBeans.put(bean, beanInstance);
        }
        for(Class secondBean: secondLevelClasses){
            Object beanInstance = instantiateBean(secondBean);
            for(Class interfaceItem: secondBean.getInterfaces())
                instantiatedBeans.put(interfaceItem, beanInstance);
            instantiatedBeans.put(secondBean, beanInstance);
        }
//        instantiatedBeans.forEach((o,s)->System.out.println(o+" -- "+s));
        secondLevelClasses = null;
        MainController.createMainController();
        MainController.getMainController().linkControllers(controllers, views);
        JDBCDaoFactory.useAnnotations(transactions.toArray(new Class[0]));
    }

    public static Object instantiateBean(Class bean) throws UnsatisfiedDependencyException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor;
        try {
            constructor = bean.getConstructors()[0];
        }catch (java.lang.ArrayIndexOutOfBoundsException ex){
            System.err.println("Error while instantiating nested bean "+bean.getName()+"; Check it's constructor and arguments");
            throw new UnsatisfiedDependencyException(bean.getName(), bean);
        }
        List<Object> params = new ArrayList<>();
        for(Class parameter: constructor.getParameterTypes()) {
            Object param = instantiatedBeans.get(parameter);
            if(param==null) {
                if(!secondLevelClasses.contains(bean)) secondLevelClasses.add(bean);
                return null;
            }
            else
                params.add(param);
        }
        return constructor.newInstance(params.toArray());
    }

    public static Object getBean(String beanName) throws ClassNotFoundException {
        return instantiatedBeans.get(Class.forName(beanName));
    }

    public static Object getBean(Class beanName){
        return instantiatedBeans.get(beanName);
    }

    private static Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
