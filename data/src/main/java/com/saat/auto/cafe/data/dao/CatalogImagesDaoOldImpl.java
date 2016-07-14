package com.saat.auto.cafe.data.dao;

import com.saat.auto.cafe.common.db.SchemaConstants.CatalogImagesTable;
import com.saat.auto.cafe.common.db.SchemaConstants.Common;
import com.saat.auto.cafe.common.interfaces.CatalogImagesDao;
import com.saat.auto.cafe.common.models.CatalogImage;
import com.saat.auto.cafe.common.utils.fluentsql.SQL;
import com.saat.auto.cafe.data.dao.mappers.CatalogImagesRowMapper;

import java.util.List;

import javax.sql.DataSource;

import static com.saat.auto.cafe.common.utils.fluentsql.SQL.Statics.SELECT;

/**
 * Created by mcoletti on 6/7/16.
 */
public class CatalogImagesDaoOldImpl extends AbstractDaoOld implements CatalogImagesDao {


    /**
     * Main Constructor
     *
     * @param dataSource the dataSource to connect to
     */
    protected CatalogImagesDaoOldImpl(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CatalogImage get(int id) {
        SQL sql = SELECT("*").FROM(CatalogImagesTable.NAME).WHERE().FIELD(Common.Columns.Id).EQUAL(id);

        List<CatalogImage> imagesList = getJdbcTemplate().query(sql.build().getSql(), sql.build().getParameters(), new CatalogImagesRowMapper());
        return imagesList.size() != 0 ? imagesList.get(0) : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CatalogImage upsertCatalogImages(CatalogImage catalogImage) {

        CatalogImage ci = get(catalogImage.getId());

        if (ci == null) {


        } else {

        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CatalogImage> getCatalogImages(int vehicleInventoryId) {

        SQL sql = SELECT("*").FROM(CatalogImagesTable.NAME).WHERE().FIELD(CatalogImagesTable.Columns.VehicleInvId).EQUAL(vehicleInventoryId);

        List<CatalogImage> imagesList = getJdbcTemplate().query(sql.build().getSql(), sql.build().getParameters(), new CatalogImagesRowMapper());
        return imagesList;
    }


}
