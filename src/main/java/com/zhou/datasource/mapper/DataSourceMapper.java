package com.zhou.datasource.mapper;

import com.zhou.datasource.model.DataSource;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auther zhoupan
 * @date 2019/4/8 21:12
 * @info
 */
public interface DataSourceMapper {

    @Select("SELECT * FROM datasource")
    List<DataSource> get();


}
