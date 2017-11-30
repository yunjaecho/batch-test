package io.spring.databaseWrite.mapper;

import io.spring.databaseWrite.domain.TbRvasMast;

import java.util.List;

/**
 * Created by USER on 2017-11-30.
 */
public interface TbRvasMastMapper {
    public List<TbRvasMast> getTbRvasMast();

    public int setTbRvasMast();
}
