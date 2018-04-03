package dao;

import dao.impl.Config;

/**
 * Created by PANNA on 02.04.2018.
 */
public abstract class DaoFactory {
    public abstract CourseDao createCourseDao();

    public static DaoFactory getInstance() {
        String className = Config.getInstance().getFactoryClassName();
        DaoFactory factory = null;
        try {
            factory = (DaoFactory) Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory;
    }
}
