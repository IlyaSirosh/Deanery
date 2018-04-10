package com.controllers.exceptions;

public class UnsatisfiedDependencyException extends Exception {
    public Class classObj;
    public UnsatisfiedDependencyException(String className, Class classname) {
        super("Can't create new instance of "+className+"; Maybe constructor isn't public or has unresolvable arguments");
        this.classObj=classname;
    }
}
