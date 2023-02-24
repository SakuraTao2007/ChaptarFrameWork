package me.sakuratao.chapterframework.enums;

public enum ActionType {

    TITLE("Title"), /* 在 Title 现实文本 */
    ACTION_BAR("ActionBar"), /* 在 ActionBar 处显示文本*/
    CHAT_FRAME("ChatFrame"), /* 在 聊天框 现实文本*/
    DELAY("Delay"), /* 设置 Delay */
    TASK("Task"), /* 用于转跳任务 */
    CONDITIONS("Conditions"), /* 调价判断 */
    ;

    private final String action;

    ActionType(String action) {
        this.action = action;
    }

    public String getType() {
        return action;
    }
}
