package model.enums;

public enum CourseConclusion {

    EXAM(1), TEST(2);

    int i;

    CourseConclusion(int i){
        this.i = i;
    }

    public int getValue(){
        return i;
    }

}
