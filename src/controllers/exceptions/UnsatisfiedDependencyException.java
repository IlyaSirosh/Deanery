package controllers.exceptions;

public class UnsatisfiedDependencyException extends Exception {
    public UnsatisfiedDependencyException(String className) {
        super("Can't create new instance of "+className+"; Maybe constructor isn't public or has unresolvable arguments");
    }
}
