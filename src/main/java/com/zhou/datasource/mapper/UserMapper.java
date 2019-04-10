package com.zhou.datasource.mapper;


import com.zhou.datasource.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IDEA
 *
 * @author : zhoupan
 * @date : 2019/4/9 11:44
 * @info :
 */
public interface UserMapper {

    @Select("SELECT * FROM user")
    List<User> get();
}
