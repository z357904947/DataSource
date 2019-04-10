package com.zhou.datasource;

import com.zhou.datasource.annotation.DBChange;
import com.zhou.datasource.dbconfig.DBContextHolder;
import com.zhou.datasource.dbconfig.DynamicDataSource;
import com.zhou.datasource.mapper.DataSourceMapper;
import com.zhou.datasource.mapper.UserMapper;
import com.zhou.datasource.model.DataSource;
import com.zhou.datasource.model.User;
import com.zhou.datasource.service.DBChangeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatasourceApplicationTests {
    @Autowired
    private DataSourceMapper dataSourceMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DynamicDataSource dynamicDataSource;
    @Autowired
    private DBChangeService dbChangeService;

    /**
     * 测试自动切换
     */
    @Test
    public void contextLoads() {
        //取出数据库中的第一条数据源配置信息
        List<DataSource> list=dataSourceMapper.get();
        DataSource d = list.get(0);
        List<User> list1 = dbChangeService.getUser(d);
        list1.forEach(user -> System.out.println(user.getUserName()));
        /**
         * 第二次查询不用再创建数据源，直接使用
         */
        List<User> list2 = dbChangeService.getUser(d);
        list2.forEach(user -> System.out.println(user.getUserName()));
    }

    /**
     * 手动改变数据源
     */
    @Test
    public void get(){
        List<DataSource> list=dataSourceMapper.get();
        DataSource d = list.get(0);
        //创建数据源
        try {
            dynamicDataSource.createDataSourceWithCheck(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //切换数据源
        DBContextHolder.setDataSource(d.getDatasourceId());
        List<User> list1=userMapper.get();
        list1.forEach(dataSource1 -> System.out.println(dataSource1.getUserName()));

    }


}
