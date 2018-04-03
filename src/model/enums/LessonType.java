package model.enums;

public enum LessonType {
    LECTURE(0), SEMINAR(1);

    private  int i;
    LessonType(int i){
        this.i = i;
    }

    public int getValue(){
        return i;
    }
}
