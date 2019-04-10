package com.zhou.datasource.dbconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auther zhoupan
 * @date 2019/4/8 21:57
 * @info
 */

public class DBContextHolder {
    private final static Logger log = LoggerFactory.getLogger(DBContextHolder.class);
    // 对当前线程的操作-线程安全的
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    // 调用此方法，切换数据源
    public static void setDataSource(String dataSource) {

        contextHolder.set(dataSource);
        log.info("已切换到数据源:{}",dataSource);
    }

    // 获取数据源
    public static String getDataSource() {
        return contextHolder.get();
    }

    // 删除数据源
    public static void clearDataSource() {
        contextHolder.remove();
        log.info("已切换到主数据源");
    }

}
