package com.saat.auto.cafe.data.dao.impl;

import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.saat.auto.cafe.common.entitys.ClientVehicles;
import com.saat.auto.cafe.common.entitys.VehicleDetails;
import com.saat.auto.cafe.common.entitys.VehicleImages;
import com.saat.auto.cafe.common.exceptions.VehicleDetailsException;
import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.common.interfaces.VehicleDao;
import com.saat.auto.cafe.data.dao.mappers.ClientVehicleRowMapper;
import com.saat.auto.cafe.data.dao.mappers.VehicleDetailsRowMapper;
import com.saat.auto.cafe.data.dao.mappers.VehicleImagesRowMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

/**
 * Created by micahcoletti on 7/18/16.
 */
@Component
public class VehicleDaoImpl implements VehicleDao {


    private static final Logger log = LoggerFactory.getLogger(ClientsDaoImpl.class);

    private final CassandraInstance ci;

    /**
     * Constructor used for testing
     * @param ci the cassandra instance
     */
    @Autowired
    public VehicleDaoImpl(CassandraInstance ci) {
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
                ci.getCassandraTemplate().execute(insert);

                ClientVehicles cv = ClientVehicles.builder()
                        .clientId(vi.getClientId())
                        .vehicleId(vi.getId())
                        .keyName(vi.getKeyName()).build();

                // Insert Record into the ClientsModel Vehicles Table
                ci.getCassandraTemplate().execute(cv.getInsertStatement());
            } else {
                vi = ci.getCassandraTemplate().update(vi);
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
            vd = ci.getCassandraTemplate().queryForObject(s, new VehicleDetailsRowMapper());
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
    public List<ClientVehicles> getByClientId(UUID clientId) {

        List<ClientVehicles> cvList = null;

        try {
            Select s = QueryBuilder.select().from("client_vehicles");

            s.where(eq("client_id",clientId));

            cvList = ci.getCassandraTemplate().query(s,new ClientVehicleRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return cvList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleImages insertVehicleImage(VehicleImages vi) throws VehicleDetailsException {

        try {
            vi = ci.getCassandraTemplate().insert(vi);
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

            imageList = ci.getCassandraTemplate().query(s,new VehicleImagesRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error Getting the IMage List for vehicleId: {} - {}",vehicleId,e.getMessage());
            throw new VehicleDetailsException(e);
        }
        return imageList;
    }
}
