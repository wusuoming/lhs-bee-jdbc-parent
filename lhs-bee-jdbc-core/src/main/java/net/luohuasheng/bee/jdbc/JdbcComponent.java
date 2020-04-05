package net.luohuasheng.bee.jdbc;

import net.luohuasheng.bee.jdbc.common.enums.DriverType;
import net.luohuasheng.bee.jdbc.component.execute.ExecuteComponent;
import net.luohuasheng.bee.jdbc.component.structure.StructureComponent;

import javax.sql.DataSource;

/**
 * sql语句执行方言组件类
 *
 * @author luohuasheng
 */
public class JdbcComponent {

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
        return new StructureComponent(dataSource, driverType);
    }

    /**
     * 构建Sql运行处理器组件
     *
     * @return Sql运行处理器组件
     */
    public ExecuteComponent execute() {
        return new ExecuteComponent(dataSource, driverType);
    }
}
