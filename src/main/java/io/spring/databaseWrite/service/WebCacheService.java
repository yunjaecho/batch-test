package io.spring.databaseWrite.service;

import io.spring.databaseWrite.domain.WebCacheVacctData;
import io.spring.databaseWrite.mapper.WebCacheaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by USER on 2017-12-01.
 */
@Service
public class WebCacheService {

    @Autowired
    private WebCacheaMapper webCacheaMapper;

    public List<WebCacheVacctData> getWebCacheVacctData(Map params) {
        return webCacheaMapper.getWebCacheVacctData(params);
    }
}
