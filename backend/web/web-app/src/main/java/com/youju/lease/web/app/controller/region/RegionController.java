package com.youju.lease.web.app.controller.region;


import com.youju.lease.common.constant.RedisConstant;
import com.youju.lease.common.result.Result;
import com.youju.lease.model.entity.CityInfo;
import com.youju.lease.model.entity.DistrictInfo;
import com.youju.lease.model.entity.ProvinceInfo;
import com.youju.lease.web.app.service.CityInfoService;
import com.youju.lease.web.app.service.DistrictInfoService;
import com.youju.lease.web.app.service.ProvinceInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Tag(name = "地区信息")
@RestController
@RequestMapping("/app/region")
public class RegionController {

    @Autowired
    private ProvinceInfoService provinceInfoService;

    @Autowired
    private CityInfoService cityInfoService;

    @Autowired
    private DistrictInfoService districtInfoService;

    @Autowired
    private RedisTemplate<String, Object> stringObjectRedisTemplate;

    @Operation(summary = "查询省份信息列表")
    @GetMapping("province/list")
    public Result<List<ProvinceInfo>> listProvince() {
        List<ProvinceInfo> list = (List<ProvinceInfo>) stringObjectRedisTemplate.opsForValue()
                .get(RedisConstant.APP_REGION_PROVINCE_KEY);
        if (list == null) {
            list = provinceInfoService.list();
            stringObjectRedisTemplate.opsForValue().set(RedisConstant.APP_REGION_PROVINCE_KEY, list,
                    RedisConstant.APP_REGION_CACHE_TTL_HOUR, TimeUnit.HOURS);
        }
        return Result.ok(list);
    }

    @Operation(summary = "根据省份id查询城市信息列表")
    @GetMapping("city/listByProvinceId")
    public Result<List<CityInfo>> listCityInfoByProvinceId(@RequestParam Long id) {
        String key = RedisConstant.APP_REGION_CITY_PREFIX + id;
        List<CityInfo> cached = (List<CityInfo>) stringObjectRedisTemplate.opsForValue().get(key);
        if (cached != null) {
            return Result.ok(cached);
        }
        LambdaQueryWrapper<CityInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CityInfo::getProvinceId, id);
        List<CityInfo> list = cityInfoService.list(queryWrapper);
        stringObjectRedisTemplate.opsForValue().set(key, list,
                RedisConstant.APP_REGION_CACHE_TTL_HOUR, TimeUnit.HOURS);
        return Result.ok(list);
    }

    @GetMapping("district/listByCityId")
    @Operation(summary = "根据城市id查询区县信息")
    public Result<List<DistrictInfo>> listDistrictInfoByCityId(@RequestParam Long id) {
        String key = RedisConstant.APP_REGION_DISTRICT_PREFIX + id;
        List<DistrictInfo> cached = (List<DistrictInfo>) stringObjectRedisTemplate.opsForValue().get(key);
        if (cached != null) {
            return Result.ok(cached);
        }
        LambdaQueryWrapper<DistrictInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DistrictInfo::getCityId, id);
        List<DistrictInfo> list = districtInfoService.list(queryWrapper);
        stringObjectRedisTemplate.opsForValue().set(key, list,
                RedisConstant.APP_REGION_CACHE_TTL_HOUR, TimeUnit.HOURS);
        return Result.ok(list);
    }
}
