package net.luohuasheng.bee.jdbc;

import net.luohuasheng.bee.jdbc.common.enums.DriverType;
import net.luohuasheng.bee.jdbc.component.execute.ExecuteComponent;
import net.luohuasheng.bee.jdbc.component.structure.StructureComponent;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * sql语句执行方言组件类
 *
 * @author luohuasheng
 */
public class JdbcComponent {
    private static Map<DataSource, StructureComponent> structureComponentMap =Collections.synchronizedMap(new HashMap<>());

    private static Map<DataSource, ExecuteComponent> executeComponentMap = Collections.synchronizedMap(new HashMap<>());

    /**
     * 数据源
     */
    private DataSource dataSource;
    /**
     * 数据驱动类型
     */
    private DriverType driverType;


    public JdbcComponent(DataSource dataSource, DriverType driverType) {
        this.dataSource = dataSource;
        this.driverType = driverType;
    }

    /**
     * 构建库结构处理器组件
     *
     * @return 库结构处理器组件
     */
    public StructureComponent structure() {
        return structureComponentMap.computeIfAbsent(dataSource, k -> new StructureComponent(dataSource, driverType));
    }

    /**
     * 构建Sql运行处理器组件
     *
     * @return Sql运行处理器组件
     */
    public ExecuteComponent execute() {
        return executeComponentMap.computeIfAbsent(dataSource, k -> new ExecuteComponent(dataSource, driverType));
    }

    public void clear(DataSource dataSource) {
        structureComponentMap.remove(dataSource);
        executeComponentMap.remove(dataSource);
    }
}
