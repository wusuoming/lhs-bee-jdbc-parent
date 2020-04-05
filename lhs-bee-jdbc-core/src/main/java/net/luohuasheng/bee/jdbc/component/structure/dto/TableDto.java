package net.luohuasheng.bee.jdbc.component.structure.dto;

import java.util.List;

/**
 * 表信息
 *
 * @author luohuasheng
 * @date 2019-02-13
 */
public class TableDto {
    private String tableType;
    private String tableName;
    private String tableSchem;
    private String tableCat;
    private String remarks;
    private List<ColumnDto> columnDtos;

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

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

    public String getTableSchem() {
        return tableSchem;
    }

    public void setTableSchem(String tableSchem) {
        this.tableSchem = tableSchem;
    }

    public String getTableCat() {
        return tableCat;
    }

    public void setTableCat(String tableCat) {
        this.tableCat = tableCat;
    }

    public List<ColumnDto> getColumnDtos() {
        return columnDtos;
    }

    public void setColumnDtos(List<ColumnDto> columnDtos) {
        this.columnDtos = columnDtos;
    }
}
