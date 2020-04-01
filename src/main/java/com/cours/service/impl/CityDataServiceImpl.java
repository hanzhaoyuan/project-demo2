package com.cours.service.impl;

import com.cours.service.CityDataService;
import com.cours.util.XmlBuilder;
import com.cours.vo.City;
import com.cours.vo.CityList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @Author: cfx
 * @Description:
 * @Date: Created in 2018/4/5 22:33
 */
@Service
public class CityDataServiceImpl implements CityDataService {

    private static final Logger logger = LoggerFactory.getLogger(CityDataServiceImpl.class);

    @Override
    public List<City> listCity() throws Exception {
        //读取xml文件
        Resource resource = new ClassPathResource("citylist.xml");
        BufferedReader br = new BufferedReader(new InputStreamReader
                (resource.getInputStream(),"utf-8"));
        StringBuffer buffer = new StringBuffer();
        String line = "";

        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }

        br.close();
        //把xml转为java对象
        CityList cityList = (CityList) XmlBuilder.xmlStrToObject(CityList.class,buffer.toString());
        return cityList.getCityList();
    }
}
