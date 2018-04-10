package com.model.enums;

public enum CourseConclusion {

    EXAM(0), TEST(1);

    int i;

    CourseConclusion(int i){
        this.i = i;
    }

    public int getValue(){
        return i;
    }

}
