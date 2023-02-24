package me.sakuratao.chapterframework.enums;

public enum ConditionType {

    MOVE("Move"),
    INTERACT("Interact"),
    ;

    private final String condition;

    ConditionType(String condition) {
        this.condition = condition;
    }

    public String getType() {
        return condition;
    }

}
