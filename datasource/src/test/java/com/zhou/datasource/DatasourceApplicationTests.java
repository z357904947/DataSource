package com.zhou.datasource;

import com.zhou.datasource.config.DBContextHolder;
import com.zhou.datasource.config.DynamicDataSource;
import com.zhou.datasource.mapper.DataSourceMapper;
import com.zhou.datasource.model.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatasourceApplicationTests {
    @Autowired
    DataSourceMapper dataSourceMapper;
    @Autowired
    private DynamicDataSource dynamicDataSource;
    @Test
    public void contextLoads() {

        List<DataSource> list=dataSourceMapper.get();
        list.forEach(dataSource -> System.out.println(dataSource.getUrl()));
    }
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
        List<DataSource> list1=dataSourceMapper.get();
        list1.forEach(dataSource1 -> System.out.println(dataSource1.getUrl()));


    }
}
