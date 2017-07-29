package com.xmomen.module.authorization.mapper;

import com.xmomen.module.authorization.model.User;
import com.xmomen.module.authorization.model.UserModel;
import com.xmomen.module.authorization.model.UserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-6-16 22:59:53
 * @version 1.0.0
 */
public interface UserMapper {

    List<User> select(UserQuery userQuery);

    List<UserModel> selectModel(UserQuery userQuery);

    User selectByPrimaryKey(String primaryKey);

    UserModel selectModelByPrimaryKey(String primaryKey);

    int deleteByPrimaryKey(String primaryKey);

    int deletesByPrimaryKey(@Param("ids") List<String> primaryKeys);

    int insertSelective(User record);

    int updateSelective(User record);

    int updateSelectiveByQuery(@Param("record") User record, @Param("query") UserQuery example);

    UserModel selectModelByUsername(String username);
}
