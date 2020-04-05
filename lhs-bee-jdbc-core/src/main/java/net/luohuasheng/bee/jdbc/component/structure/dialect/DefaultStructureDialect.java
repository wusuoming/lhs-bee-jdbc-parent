package net.luohuasheng.bee.jdbc.component.structure.dialect;

import net.luohuasheng.bee.jdbc.component.structure.StructureDialect;

/**
 * 默认实现
 *
 * @author luohuasheng
 * @date 2020/4/5 15:19
 */
public class DefaultStructureDialect implements StructureDialect {
    private DefaultStructureDialect() {
    }

    private static DefaultStructureDialect INSTANCE = null;

    public static DefaultStructureDialect get() {
        if (INSTANCE == null) {
            INSTANCE = new DefaultStructureDialect();
        }
        return INSTANCE;
    }
}
