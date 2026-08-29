package com.br.common.constants;

/**
 * Redis Key常量类
 */
public class RedisConstants {

    /**
     * 登录态Key前缀，后面拼接JWT中的jti
     */
    public static final String LOGIN_TOKEN_KEY = "login:token:";

    /**
     * 商品缓存Key前缀
     */
    public static final String CACHE_PRODUCT_KEY = "cache:product:";

    /**
     * 秒杀活动缓存Key前缀
     */
    public static final String CACHE_SECKILL_ACTIVITY_KEY = "cache:seckill:activity:";

    /**
     * 活跃弹窗缓存Key
     */
    public static final String CACHE_NOTICE_ACTIVE_KEY = "cache:notice:active";
    /**
     * 管理员首页统计缓存Key
     */
    public static final String CACHE_STATISTICS_DASHBOARD_KEY = "cache:statistics:dashboard";
    /**
     * 电池类型统计缓存Key
     */
    public static final String CACHE_STATISTICS_BATTERY_TYPE_KEY = "cache:statistics:batteryType";

    /**
     * 每日回收统计缓存Key前缀，后面拼接天数
     */
    public static final String CACHE_STATISTICS_DATE_KEY = "cache:statistics:date:";

    /**
     * 月度回收统计缓存Key
     */
    public static final String CACHE_STATISTICS_MONTHLY_KEY = "cache:statistics:monthly";

    /**
     * 年度回收统计缓存Key
     */
    public static final String CACHE_STATISTICS_YEARLY_KEY = "cache:statistics:yearly";

    /**
     * 订单状态分布统计缓存Key
     */
    public static final String CACHE_STATISTICS_ORDER_STATUS_KEY = "cache:statistics:orderStatus";

    /**
     * 地区回收排行缓存Key前缀，后面拼接条数
     */
    public static final String CACHE_STATISTICS_CITY_RANK_KEY = "cache:statistics:cityRank:";


    /**
     * 缓存空值TTL，防止缓存穿透
     */
    public static final Long CACHE_NULL_TTL = 2L;

    /**
     * 普通业务缓存TTL
     */
    public static final Long CACHE_NORMAL_TTL = 30L;

    /**
     * 缓存重建锁Key前缀
     */
    public static final String LOCK_CACHE_KEY = "lock:cache:";

    /**
     * 用户级秒杀落库锁Key前缀
     */
    public static final String LOCK_SECKILL_USER_KEY = "lock:seckill:user:";

    /**
     * 秒杀库存Key前缀
     */
    public static final String SECKILL_STOCK_KEY = "seckill:stock:";

    /**
     * 秒杀已抢用户Set前缀
     */
    public static final String SECKILL_USERS_KEY = "seckill:users:";

    /**
     * 秒杀优惠券Stream名称
     */
    public static final String SECKILL_STREAM_KEY = "stream.seckill.coupon";

    /**
     * 秒杀优惠券消费组
     */
    public static final String SECKILL_CONSUMER_GROUP = "g1";
}
