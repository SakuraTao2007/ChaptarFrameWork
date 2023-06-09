package me.sakuratao.chapterframework.enums;

public enum ActionType {

    TITLE("Title"), /* 在 Title 现实文本 */
    ACTION_BAR("ActionBar"), /* 在 ActionBar 处显示文本*/
    CHAT_FRAME("ChatFrame"), /* 在 聊天框 现实文本*/
    PRINT_CF("PrintCF"), /* 在 聊天框 实现 打印输出 文本 */
    CLEAN_PCF("CleanPCF"), /* 用于清理 PrintCF 的语句缓存 */
    PRINT_AB("PrintAB"), /* 在 ActionBar 实现 打印输出 文本 */
    DELAY("Delay"), /* 设置 Delay */
    TASK("Task"), /* 用于转跳任务 */
    CP("CP"), /* 用于转跳章节 */
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
