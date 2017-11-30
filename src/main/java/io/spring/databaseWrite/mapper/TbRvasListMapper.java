package io.spring.databaseWrite.mapper;

import io.spring.databaseWrite.domain.TbRvasList;

import java.util.List;

/**
 * Created by USER on 2017-11-30.
 */
public interface TbRvasListMapper {
    public List<TbRvasList> getTbRvasList();

    public int setTbRvasList();
}
