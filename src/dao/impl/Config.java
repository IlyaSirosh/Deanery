<<<<<<< HEAD
package dao.impl;

/**
 * Created by PANNA on 02.04.2018.
 */
import dao.impl.JDBCDaoFactory;

public class Config {

    private String url = "jdbc:mysql://localhost:3306/mydb";
    private String user = "root";
    private String pass= "12345";
    private String factoryClassName = JDBCDaoFactory.class.getName();

    private static class Holder{
        private static Config INSTANCE = new Config();
    }

    public static Config getInstance(){
        return Holder.INSTANCE;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
    public String getFactoryClassName() {
        return factoryClassName;
    }

=======
package dao.impl;

/**
 * Created by PANNA on 02.04.2018.
 */
import dao.impl.JDBCDaoFactory;

public class Config {

    private String url = "jdbc:mysql://localhost:3306/mydb";
    private String user = "root";
    private String pass= "1234";
    private String factoryClassName = JDBCDaoFactory.class.getName();

    private static class Holder{
        private static Config INSTANCE = new Config();
    }

    public static Config getInstance(){
        return Holder.INSTANCE;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
    public String getFactoryClassName() {
        return factoryClassName;
    }

>>>>>>> ui
}