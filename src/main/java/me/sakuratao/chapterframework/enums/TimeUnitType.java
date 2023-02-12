package me.sakuratao.chapterframework.enums;

public enum TimeUnitType {

    TICKS("Ticks"),
    SECOND("Second"),
    MINUTE("Minute"),
    HOUR("Hour"),
    ;

    private final String unit;

    TimeUnitType(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
    
}
