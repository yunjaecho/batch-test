package io.spring.databaseWrite.mapper;

import io.spring.databaseWrite.domain.WebCacheVacctData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by USER on 2017-11-30.
 */
@Mapper
public interface WebCacheaMapper {
    public List<WebCacheVacctData> getWebCacheVacctData(Map params);

}
