package com.br.common.constants;

/**
 * 系统常量类 - 统一管理所有提示信息和常量
 */
public class SystemConstants {

    // ==================== 用户相关 ====================
    public static final String USER_REGISTER_SUCCESS = "注册成功";
    public static final String USER_LOGIN_SUCCESS = "登录成功";
    public static final String USER_LOGOUT_SUCCESS = "退出登录成功";
    public static final String USER_UPDATE_SUCCESS = "更新成功";
    public static final String USER_DELETE_SUCCESS = "删除成功";
    public static final String USER_ADD_SUCCESS = "添加用户成功";
    public static final String USER_PASSWORD_CHANGE_SUCCESS = "密码修改成功";
    public static final String USER_PASSWORD_RESET_SUCCESS = "您的密码已被重置为123456";

    public static final String USER_ALREADY_EXISTS = "该用户名已注册";
    public static final String USER_NOT_FOUND = "该用户未注册";
    public static final String USER_PASSWORD_ERROR = "密码错误";
    public static final String USER_OLD_PASSWORD_ERROR = "原密码错误";
    public static final String USER_NEW_PASSWORD_SAME_AS_OLD = "新密码不能与原密码相同";
    public static final String USER_PHONE_MISMATCH = "手机号确认错误";
    public static final String USER_DISABLED = "该账号已被禁用";
    public static final String USER_PASSWORD_FORMAT_ERROR = "密码只能包含大小写字母、数字和 . !，长度6-20位";
    public static final String USER_CANNOT_DELETE_SELF = "不能删除自己的账号";

    // ==================== 权限相关 ====================
    public static final String TOKEN_INVALID = "登录已过期，请重新登录";
    public static final String PERMISSION_DENIED = "权限不足";
    public static final String ADMIN_ONLY = "仅管理员可操作";

    // ==================== 电池种类相关 ====================
    public static final String BATTERY_TYPE_ADD_SUCCESS = "添加电池种类成功";
    public static final String BATTERY_TYPE_UPDATE_SUCCESS = "更新电池种类成功";
    public static final String BATTERY_TYPE_DELETE_SUCCESS = "删除电池种类成功";
    public static final String BATTERY_TYPE_NOT_FOUND = "电池种类不存在";
    public static final String BATTERY_TYPE_NAME_EXISTS = "电池种类名称已存在";

    // ==================== 回收订单相关 ====================
    public static final String ORDER_CREATE_SUCCESS = "创建订单成功";
    public static final String ORDER_UPDATE_SUCCESS = "更新订单成功";
    public static final String ORDER_CANCEL_SUCCESS = "取消订单成功";
    public static final String ORDER_NOT_FOUND = "订单不存在";
    public static final String ORDER_CANNOT_CANCEL = "订单状态不允许取消";
    public static final String ORDER_UPDATE_STATUS_SUCCESS = "更新状态成功";
    public static final String POINTS_GRANT_FAILED = "积分发放失败";

    // ==================== 文件上传相关 ====================
    public static final String FILE_UPLOAD_SUCCESS = "文件上传成功";
    public static final String FILE_UPLOAD_FAILED = "文件上传失败";
    public static final String FILE_TYPE_ERROR = "文件类型不支持";
    public static final String FILE_SIZE_ERROR = "文件大小超出限制";
    public static final String FILE_EMPTY = "文件不能为空";

    // ==================== 数据统计相关 ====================
    public static final String STATISTICS_QUERY_SUCCESS = "查询统计数据成功";

    // ==================== 秒杀优惠券相关 ====================
    public static final String SECKILL_ACTIVITY_NOT_FOUND = "秒杀活动不存在";
    public static final String SECKILL_ACTIVITY_NOT_STARTED = "秒杀活动未开始";
    public static final String SECKILL_ACTIVITY_ENDED = "秒杀活动已结束";
    public static final String SECKILL_ACTIVITY_OFFLINE = "秒杀活动未上架";
    public static final String SECKILL_STOCK_NOT_ENOUGH = "秒杀券库存不足";
    public static final String SECKILL_REPEAT_ORDER = "每场活动只能抢一张券";
    public static final String SECKILL_COUPON_NOT_FOUND = "秒杀券不存在";
    public static final String SECKILL_COUPON_NOT_EFFECTIVE = "秒杀券尚未生效";
    public static final String SECKILL_COUPON_EXPIRED = "秒杀券已过期";
    public static final String SECKILL_COUPON_USED = "秒杀券已使用";
    public static final String SECKILL_POINTS_NOT_ENOUGH = "积分不足，无法秒杀";
    public static final String SECKILL_MQ_SEND_FAILED = "秒杀请求繁忙，请稍后再试";
    public static final String SECKILL_ADD_SUCCESS = "创建秒杀活动成功";
    public static final String SECKILL_UPDATE_SUCCESS = "更新秒杀活动成功";
    public static final String SECKILL_ONLINE_SUCCESS = "活动已上架并预热库存";
    public static final String SECKILL_OFFLINE_SUCCESS = "活动已下架";
    public static final String SECKILL_PREHEAT_SUCCESS = "预热成功";
    public static final String SECKILL_GRAB_SUCCESS = "抢券成功，优惠券将在生效时间后可用";
    public static final String SECKILL_END_AFTER_START = "秒杀结束时间必须晚于开始时间";
    public static final String SECKILL_COUPON_EXPIRE_AFTER_EFFECTIVE = "优惠券过期时间必须晚于生效时间";

    // ==================== 系统公告相关 ====================
    public static final String NOTICE_NOT_FOUND = "系统公告不存在";
    public static final String NOTICE_ADD_SUCCESS = "新增公告成功";
    public static final String NOTICE_UPDATE_SUCCESS = "更新公告成功";
    public static final String NOTICE_READ_SUCCESS = "已读成功";

    // ==================== 兑换相关 ====================
    public static final String EXCHANGE_PRODUCT_NOT_FOUND = "商品不存在";
    public static final String EXCHANGE_PRODUCT_OFFLINE = "该商品已下架";
    public static final String EXCHANGE_RECORD_NOT_FOUND = "兑换记录不存在";
    public static final String EXCHANGE_STOCK_NOT_ENOUGH = "库存不足";
    public static final String EXCHANGE_POINTS_NOT_ENOUGH = "积分不足";
    public static final String EXCHANGE_PRODUCT_ADD_SUCCESS = "添加商品成功";
    public static final String EXCHANGE_PRODUCT_UPDATE_SUCCESS = "更新商品成功";
    public static final String EXCHANGE_PRODUCT_DELETE_SUCCESS = "删除商品成功";
    public static final String EXCHANGE_SUCCESS = "兑换成功";

    // ==================== 通用提示 ====================
    public static final String OPERATION_FAILED = "操作失败";
    public static final String SYSTEM_ERROR = "系统异常，请稍后重试";

    // ==================== 用户角色 ====================
    public static final Integer ROLE_USER = 0;  // 普通用户
    public static final Integer ROLE_ADMIN = 1; // 管理员

    // ==================== 用户状态 ====================
    public static final Integer STATUS_DISABLED = 0; // 禁用
    public static final Integer STATUS_NORMAL = 1;   // 正常

    // ==================== 订单状态 ====================
    public static final Integer ORDER_STATUS_PENDING = 0;    // 待处理
    public static final Integer ORDER_STATUS_PROCESSING = 1; // 处理中
    public static final Integer ORDER_STATUS_COMPLETED = 2;  // 已完成
    public static final Integer ORDER_STATUS_CANCELLED = 3;  // 已取消
    public static final String ORDER_STATUS_ILLEGAL = "订单状态不合法";

    // ==================== 兑换类型 ====================
    public static final Integer EXCHANGE_TYPE_POINTS = 0; // 普通积分兑换
    public static final Integer EXCHANGE_TYPE_SECKILL_COUPON = 1; // 秒杀券兑换

    // ==================== 秒杀活动状态 ====================
    public static final Integer SECKILL_STATUS_DRAFT = 0; // 草稿
    public static final Integer SECKILL_STATUS_ONLINE = 1; // 上架
    public static final Integer SECKILL_STATUS_OFFLINE = 2; // 下架

    // ==================== 秒杀券状态 ====================
    public static final Integer COUPON_STATUS_UNUSED = 0; // 未使用
    public static final Integer COUPON_STATUS_USED = 1; // 已使用
    public static final Integer COUPON_STATUS_EXPIRED = 2; // 已过期

    // ==================== 公告状态 ====================
    public static final Integer NOTICE_STATUS_DRAFT = 0; // 草稿
}