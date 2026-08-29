-- 1.参数列表
-- 1.1 活动ID
local activityId = ARGV[1]
-- 1.2 用户ID
local userId = ARGV[2]

-- 2.Key列表
-- 2.1 秒杀库存Key
local stockKey = KEYS[1]
-- 2.2 已抢用户Set Key
local userKey = KEYS[2]

-- 3.判断库存是否充足
if (tonumber(redis.call('get', stockKey) or '0') <= 0) then
    return 1
end

-- 4.判断用户是否重复抢购
if (redis.call('sismember', userKey, userId) == 1) then
    return 2
end

-- 5.Redis中先预扣库存并记录用户
redis.call('incrby', stockKey, -1)
redis.call('sadd', userKey, userId)

-- 6.返回成功，后续由Java投递RabbitMQ异步扣积分和生成用户券
return 0
