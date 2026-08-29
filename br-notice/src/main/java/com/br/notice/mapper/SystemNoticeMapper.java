package com.br.notice.mapper;

import com.br.notice.entity.SystemNotice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统公告Mapper接口
 */
public interface SystemNoticeMapper {

    /**
     * 根据ID查询公告
     */
    SystemNotice getById(@Param("id") Long id);

    /**
     * 查询全部公告
     */
    List<SystemNotice> listAll();

    /**
     * 查询当前用户未读且正在展示的公告
     */
    List<SystemNotice> listActiveUnread(@Param("userId") Long userId);

    /**
     * 新增公告
     */
    int insert(SystemNotice notice);

    /**
     * 更新公告
     */
    int update(SystemNotice notice);
}
