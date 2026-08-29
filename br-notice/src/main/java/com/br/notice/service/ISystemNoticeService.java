package com.br.notice.service;


import com.br.notice.entity.SystemNotice;

import java.util.List;

/**
 * 系统公告服务接口。
 */
public interface ISystemNoticeService {

    SystemNotice getById(Long id);

    List<SystemNotice> listAll();

    List<SystemNotice> listActiveUnread(Long userId);

    void add(SystemNotice notice, Long adminId);

    void update(SystemNotice notice);

    void markRead(Long noticeId, Long userId);
}
