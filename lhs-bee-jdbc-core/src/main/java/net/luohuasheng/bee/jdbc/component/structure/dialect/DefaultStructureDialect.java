package net.luohuasheng.bee.jdbc.component.structure.dialect;

import net.luohuasheng.bee.jdbc.component.structure.StructureDialect;
import net.luohuasheng.bee.jdbc.component.structure.dto.ColumnDto;
import net.luohuasheng.bee.jdbc.component.structure.dto.TableDto;

import java.util.List;

/**
 * 默认实现
 *
 * @author luohuasheng
 * @date 2020/4/5 15:19
 */
public class DefaultStructureDialect implements StructureDialect {


    private static DefaultStructureDialect INSTANCE = null;

    public static DefaultStructureDialect get() {
        if (INSTANCE == null) {
            INSTANCE = new DefaultStructureDialect();
        }
        return INSTANCE;
    }

    @Override
    public List<TableDto> mergeSpecificTable(List<TableDto> tables) {
        return tables;
    }

    @Override
    public List<ColumnDto> mergeSpecificColumns(List<ColumnDto> columns) {
        return columns;
    }

    @Override
    public String getSchema(String username) {
        return username;
    }
}
