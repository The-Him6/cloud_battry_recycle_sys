package com.br.user.mapper;

import com.br.user.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户Mapper接口
 */
public interface UserMapper {

    /**
     * 根据ID查询用户
     */
    User getById(@Param("id") Long id);

    /**
     * 根据用户名查询用户
     */
    User getByUsername(@Param("username") String username);

    /**
     * 查询所有用户
     */
    List<User> listAll();

    /**
     * 分页查询用户
     */
    List<User> listByPage(@Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 统计用户总数
     */
    Integer count();

    /**
     * 插入用户
     */
    int insert(User user);

    /**
     * 更新用户
     */
    int update(User user);

    /**
     * 删除用户
     */
    int deleteById(@Param("id") Long id);

    /**
     * 按条件搜索用户
     */
    List<User> searchUsers(@Param("userId") Long userId,
            @Param("username") String username,
            @Param("offset") Integer offset,
            @Param("limit") Integer limit);

    /**
     * 统计搜索结果总数
     */
    Integer countBySearch(@Param("userId") Long userId,
            @Param("username") String username);

    /**
     * 根据用户名和手机号重置密码
     */
    int resetPasswordByUsernameAndPhone(@Param("username") String username,
            @Param("phone") String phone,
            @Param("password") String password);

    /**
     * 修改密码前校验原密码
     */
    User getByIdAndPassword(@Param("id") Long id, @Param("password") String password);
}
