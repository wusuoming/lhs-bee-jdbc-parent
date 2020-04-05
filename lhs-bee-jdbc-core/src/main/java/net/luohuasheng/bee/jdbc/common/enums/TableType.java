package net.luohuasheng.bee.jdbc.common.enums;

/**
 * 表类型
 *
 * @author luohuasheng
 * @date 2020/4/5 12:21
 */
public enum TableType {
    /**
     * 数据库表
     */
    TABLE("TABLE", "数据库表"),
    /**
     * 数据库视图
     */
    VIEW("VIEW", "数据库视图"),
    ;

    private final String code;
    private final String description;

    TableType(String code, String description) {
        this.code = code;
        this.description = description;
    }


    public static TableType getTypeByCode(String code) {
        for (TableType element : TableType.values()) {
            if (code.equals(element.getCode())) {
                return element;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
