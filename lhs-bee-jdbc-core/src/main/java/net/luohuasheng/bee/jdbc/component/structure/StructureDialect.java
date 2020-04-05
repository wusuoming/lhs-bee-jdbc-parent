package net.luohuasheng.bee.jdbc.component.structure;

import net.luohuasheng.bee.jdbc.component.structure.dto.ColumnDto;
import net.luohuasheng.bee.jdbc.component.structure.dto.TableDto;

import java.util.List;

/**
 * 获取数据库结构方言接口类
 *
 * @author luohuasheng
 */
public interface StructureDialect {


    /**
     * 合并个性化表信息
     *
     * @param tables 原表信息
     * @return 合并后的表信息
     */
    default List<TableDto> mergeSpecificTable(List<TableDto> tables) {
        return tables;
    }

    /**
     * 合并个性化字段信息
     *
     * @param columns 原字段信息
     * @return 合并后的字段信息
     */
    default List<ColumnDto> mergeSpecificColumns(List<ColumnDto> columns) {
        return columns;
    }

    /**
     * 获取数据库模式
     *
     * @param username 用户名
     * @return 数据库模式
     */
    default String getSchema(String username) {
        return username;
    }
}
