package net.luohuasheng.bee.jdbc.component.structure.dto;


import net.luohuasheng.bee.jdbc.common.enums.DataType;

/**
 * 列信息
 *
 * @author wusuoming
 */
public class ColumnDto {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 是否可为空
     */
    private boolean isNullable;
    private String remarks;
    private int columnSize;
    private String typeName;
    private DataType dataType;
    private String columnName;
    private int decimalDigits;
    private boolean isAutoincrement;
    /**
     * 是否主键
     */
    private boolean isPk;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }


    public boolean isPk() {
        return isPk;
    }

    public void setPk(boolean pk) {
        isPk = pk;
    }

    public boolean isAutoincrement() {
        return isAutoincrement;
    }

    public void setAutoincrement(boolean autoincrement) {
        isAutoincrement = autoincrement;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }
}