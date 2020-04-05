package net.luohuasheng.bee.jdbc.component.execute;

import net.luohuasheng.bee.jdbc.common.enums.DriverType;
import net.luohuasheng.bee.jdbc.component.BaseComponent;

import javax.sql.DataSource;

/**
 * @author luohuasheng
 * @date 2020/4/5 10:14
 */
public class ExecuteComponent extends BaseComponent {
    public ExecuteComponent(DataSource dataSource, DriverType driverType) {
        super(dataSource, driverType);
    }


}
