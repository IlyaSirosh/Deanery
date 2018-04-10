package com.model.enums;

public enum Day {Monday(1), Tuesday(2), Wednesday(3), Thursday(4), Friday(5), Saturday(6), Sunday(7);
    int i;

    private Day(int i){
        this.i = i;
    }

    public int getValue(){
        return i;
    }
}
