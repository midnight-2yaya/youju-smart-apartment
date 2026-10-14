package com.youju.lease.common.constant;

public class RedisConstant {
    public static final String ADMIN_LOGIN_PREFIX = "admin:login:";
    public static final Integer ADMIN_LOGIN_CAPTCHA_TTL_SEC = 60;
    public static final String APP_LOGIN_PREFIX = "app:login:";
    public static final Integer APP_LOGIN_CODE_RESEND_TIME_SEC = 60;
    public static final Integer APP_LOGIN_CODE_TTL_SEC = 60 * 10;
    public static final String APP_ROOM_PREFIX = "app:room:";
    public static final String APP_APARTMENT_PREFIX = "app:apartment:";
    public static final Integer APP_DETAIL_CACHE_TTL_MIN = 30;
    public static final String APP_REGION_PROVINCE_KEY = "app:region:province";
    public static final String APP_REGION_CITY_PREFIX = "app:region:city:";
    public static final String APP_REGION_DISTRICT_PREFIX = "app:region:district:";
    public static final Integer APP_REGION_CACHE_TTL_HOUR = 24;
}
