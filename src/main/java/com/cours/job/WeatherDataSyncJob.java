package com.cours.job;

import com.cours.service.CityDataService;
import com.cours.service.WeatherDataService;
import com.cours.vo.City;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 *  天气数据同步Job
 */
public class WeatherDataSyncJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Autowired
    private CityDataService cityDataService;

    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("天气数据同步Job,Start!");
        //获取城市ID列表
        List<City> cityList = null;

        try {
            cityList = cityDataService.listCity();
        } catch (Exception e) {
            logger.error("Exception!!",e);
        }
        //遍历城市ID获取天气
        for (City city : cityList) {
            String cityId = city.getCityId();
            logger.info("天气数据同步Job,cityId:" + cityId);

            weatherDataService.syncDataByCityId(cityId);
        }

        logger.info("天气数据同步Job,End!");
    }
}
