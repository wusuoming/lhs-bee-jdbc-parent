package net.luohuasheng.bee.jdbc.common.enums;

public enum DataType {
    CHARACTER(1, "字符串"),
    INTEGER(2, "整数"),
    DECIMAL(3, "小数"),
    DATE(4, "日期"),
    TIME(5, "时间"),
    DATE_TIME(6, "日期时间");

    private Integer code;
    private String discription;

    DataType(Integer code, String discription) {
        this.code = code;
        this.discription = discription;
    }


    public static DataType getTypeByCode(String code) {
        for (DataType element : DataType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

     public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}


