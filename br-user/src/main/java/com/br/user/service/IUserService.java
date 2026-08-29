package com.br.user.service;

import com.br.common.domain.PageRequest;
import com.br.common.domain.PageResult;
import com.br.user.dto.ChangePasswordDTO;
import com.br.user.entity.User;
import com.br.user.vo.UserVO;

import java.util.List;

/**
 * 用户服务接口。
 */
public interface IUserService {

    UserVO getById(Long id);

    Long count();

    List<UserVO> listAll();

    PageResult<UserVO> getUserPage(PageRequest pageRequest);

    void addUser(User user);

    void update(User user);

    void changePassword(Long userId, ChangePasswordDTO dto);

    void deleteById(Long id);

    PageResult<UserVO> searchUsers(Long userId, String username, PageRequest pageRequest);
}
