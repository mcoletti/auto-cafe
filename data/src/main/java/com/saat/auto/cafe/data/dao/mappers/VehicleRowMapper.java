package com.saat.auto.cafe.data.dao.mappers;

import com.saat.auto.cafe.common.db.SchemaConstants.Common;
import com.saat.auto.cafe.common.db.SchemaConstants.VehicleDetailsTable.Columns;
import com.saat.auto.cafe.common.models.VehicleDetails;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mcoletti on 6/1/16.
 */
public class VehicleRowMapper implements RowMapper<VehicleDetails> {
    @Override
    public VehicleDetails mapRow(ResultSet rs, int rowNum) throws SQLException {


        VehicleDetails vi = VehicleDetails.builder().build();

        vi.setId(rs.getInt(Common.Columns.Id));
        vi.setKeyName(rs.getString(Columns.KeyName));
        vi.setExtColor(rs.getString(Columns.ExtColor));
        vi.setBodyStyleId(rs.getInt(Columns.BodyStyleId));
        vi.setClientId(rs.getInt(Columns.ClientId));
        vi.setIntColor(rs.getString(Columns.IntColor));
        vi.setLocationId(rs.getInt(Columns.Location));
        vi.setManufactureId(rs.getInt(Columns.ManufactureId));
        vi.setStockNum(rs.getString(Columns.StockNum));
        vi.setTrim(rs.getString(Columns.Trim));
        vi.setVehicleCategoryId(rs.getInt(Columns.Category));
        vi.setVehicleMileage(rs.getString(Columns.VehicleMileage));
        vi.setVehiclePrice(rs.getBigDecimal(Columns.VehiclePrice));
        vi.setVehicleYearId(rs.getInt(Columns.VehicleYearId));
        vi.setVehicleModelId(rs.getInt(Columns.VehicleModelId));
        vi.setCreatedBy(rs.getString(Common.Columns.CreatedBy));
        vi.setCreatedDtm(new DateTime(rs.getTimestamp(Common.Columns.CreatedDtm).getTime()));
        vi.setModifiedBy(rs.getString(Common.Columns.ModifiedBy));
        vi.setCreatedDtm(new DateTime(rs.getTimestamp(Common.Columns.ModifiedDtm).getTime()));
        return vi;
    }
}
