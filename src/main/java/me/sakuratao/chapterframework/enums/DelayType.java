package me.sakuratao.chapterframework.enums;

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
