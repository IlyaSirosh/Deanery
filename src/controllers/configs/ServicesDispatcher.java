package controllers.configs;

import controllers.exceptions.UnsatisfiedDependencyException;
import services.TeacherService;
import services.impl.*;

import java.util.HashMap;
import java.util.Map;

public class ServicesDispatcher {
    private Map<String, Object> serviceInstances;

    private static ServicesDispatcher servicesDispatcher;

    private ServicesDispatcher() throws UnsatisfiedDependencyException {
        String currentClass = "";
        try {
            serviceInstances = new HashMap<>();
<<<<<<< HEAD
            Class[] services = new Class[]{CourseServiceImpl.class, DeaneryServiceImpl.class, LessonServiceImpl.class, ScheduleServiceImpl.class, DepartmentServiceImpl.class, TeacherServiceImpl.class};
=======
            Class[] services = new Class[]{TeacherServiceImpl.class, CourseServiceImpl.class, DeaneryServiceImpl.class, LessonServiceImpl.class, ScheduleServiceImpl.class, DepartmentServiceImpl.class};
>>>>>>> Oleksii
            for (Class service : services) {
                currentClass = service.getName();
                serviceInstances.put(service.getInterfaces()[0].getName().toLowerCase(), service.newInstance());
            }
        }catch (Exception e){
            throw  new UnsatisfiedDependencyException(currentClass);
        }
    }

    public static void createServiceDispatcher() throws UnsatisfiedDependencyException{
        servicesDispatcher = new ServicesDispatcher();
    }

    public static ServicesDispatcher getServicesDispatcher() throws UnsatisfiedDependencyException {
        if(servicesDispatcher==null) servicesDispatcher = new ServicesDispatcher();
        return servicesDispatcher;
    }

    public Object getService(String beanName){
        return serviceInstances.get(beanName.toLowerCase());
    }
}
