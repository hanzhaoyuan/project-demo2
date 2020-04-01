package com.cours.service;

import com.cours.vo.WeatherResponse;

public interface WeatherDataService {
    /**
     * 根据城市Id查询天气
     * @param cityId
     * @return
     */
    WeatherResponse getDataByCityId(String cityId);

    /**
     * 根据城市名称查询天气
     * @param cityName
     * @return
     */
    WeatherResponse getDataByCityName(String cityName);

    /**
     * 根据城市Id来同步天气
     * @param cityId
     */
    void syncDataByCityId(String cityId);
}
