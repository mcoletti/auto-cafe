package com.saat.auto.cafe.data.dao.impl;

import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.saat.auto.cafe.common.exceptions.VehicleDetailsException;
import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.common.interfaces.VehicleDao;
import com.saat.auto.cafe.common.entitys.ClientVehicles;
import com.saat.auto.cafe.common.entitys.VehicleDetails;
import com.saat.auto.cafe.common.entitys.VehicleImages;
import com.saat.auto.cafe.data.dao.mappers.VehicleDetailsRowMapper;
import com.saat.auto.cafe.data.dao.mappers.VehicleImagesRowMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

/**
 * Created by micahcoletti on 7/18/16.
 */
public class VehicleDaoImpl implements VehicleDao {


    private static Logger log = LoggerFactory.getLogger(ClientsDaoImpl.class);

    @Inject
    private CassandraInstance ci;


    protected VehicleDaoImpl(CassandraInstance ci) {
        this.ci = ci;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleDetails upsertVehicleDetails(VehicleDetails vi) throws VehicleDetailsException {

        VehicleDetails vdUpdate = get(vi.getId(),vi.getClientId());

        try {
            if (vdUpdate == null) {

                // Insert new Vehicle Details Record
                Insert insert = vi.getInsertStatement(ci.getCluster());
                ci.getCassandraOperations().execute(insert);

                ClientVehicles cv = ClientVehicles.builder()
                        .clientId(vi.getClientId())
                        .vehicleId(vi.getId())
                        .keyName(vi.getKeyName()).build();

                // Insert Record into the ClientsModel Vehicles Table
                ci.getCassandraOperations().execute(cv.getInsertStatement());
            } else {
                vi = ci.getCassandraOperations().update(vi);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error Inserting/Updating the Vehicle Details for id: {} - {}",vi.getId(),e.getMessage());
            throw new VehicleDetailsException(e);
        }


        return vi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleDetails get(UUID vehicleId,UUID clientId) throws VehicleDetailsException {


        VehicleDetails vd;
        try {
            Select s = QueryBuilder.select().from("vehicle_details");
            s.where(eq("id", vehicleId)).and(eq("client_id",clientId));
            vd = ci.getCassandraOperations().queryForObject(s, new VehicleDetailsRowMapper());
        } catch (Exception e) {
            if (e.getMessage().contains("0 rows")) {
                vd = null;
            } else {
                e.printStackTrace();
                log.error("Error getting the Vehicle Details for id: {} - {}", vehicleId, e.getMessage());
                throw new VehicleDetailsException(e);
            }

        }
        return vd;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleDetails get(String keyName) throws VehicleDetailsException {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VehicleDetails> getByClientId(UUID clientId) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleImages insertVehicleImage(VehicleImages vi) throws VehicleDetailsException {

        try {
            vi = ci.getCassandraOperations().insert(vi);
        } catch (Exception e) {
            e.printStackTrace();
            throw new VehicleDetailsException(e);
        }
        return vi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VehicleImages> getVehicleImageList(UUID vehicleId) throws VehicleDetailsException {

        List<VehicleImages> imageList;
        try {
            Select s = QueryBuilder.select().from("vehicle_images");
            s.where(eq("vehicle_id",vehicleId));

            imageList = ci.getCassandraOperations().query(s,new VehicleImagesRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error Getting the IMage List for vehicleId: {} - {}",vehicleId,e.getMessage());
            throw new VehicleDetailsException(e);
        }
        return imageList;
    }


}
