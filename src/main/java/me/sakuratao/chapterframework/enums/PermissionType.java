package me.sakuratao.chapterframework.enums;

public enum PermissionType {

    COMMAND("cfw.command"),
    DEBUG("cfw.debug")
    ;

    public final String permission;

    PermissionType(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
