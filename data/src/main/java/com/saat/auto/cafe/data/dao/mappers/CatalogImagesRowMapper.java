package com.saat.auto.cafe.data.dao.mappers;

import com.saat.auto.cafe.common.models.CatalogImage;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mcoletti on 6/7/16.
 */
public class CatalogImagesRowMapper implements RowMapper<CatalogImage> {
    @Override
    public CatalogImage mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
