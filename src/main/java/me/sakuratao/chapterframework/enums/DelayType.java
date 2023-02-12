package me.sakuratao.chapterframework.enums;

import org.jetbrains.annotations.Contract;

public enum DelayType {

    LAST("Last"),
    NEXT("Next"),
    ;
    public final String delayType;
    DelayType(String delayType){
        this.delayType = delayType;
    }

    public String getDelayType() {
        return delayType;
    }
}
