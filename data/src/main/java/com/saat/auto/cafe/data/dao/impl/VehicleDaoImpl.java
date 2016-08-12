package com.saat.auto.cafe.data.dao.impl;

import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
//import com.saat.auto.cafe.common.AutoCafeConstants;
import com.saat.auto.cafe.common.AutoCafeConstants.Schema;
import com.saat.auto.cafe.common.AutoCafeConstants.Schema.ClientVehiclesTbl;
import com.saat.auto.cafe.common.AutoCafeConstants.Schema.VehicleImagesTbl;
import com.saat.auto.cafe.common.entitys.ClientVehicle;
import com.saat.auto.cafe.common.entitys.ClientVehicleCollection;
import com.saat.auto.cafe.common.entitys.VehicleImages;
import com.saat.auto.cafe.common.exceptions.ClientVehicleException;
import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.common.interfaces.VehicleDao;
import com.saat.auto.cafe.data.dao.mappers.ClientVehicleRowMapper;
import com.saat.auto.cafe.data.dao.mappers.VehicleImagesRowMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
     *
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
    public VehicleImages insertVehicleImage(VehicleImages vi) throws ClientVehicleException {

        try {
            vi = ci.getCassandraTemplate().insert(vi);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClientVehicleException(e);
        }
        return vi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VehicleImages> getVehicleImageList(UUID vehicleId) throws ClientVehicleException {

        List<VehicleImages> imageList;
        try {
            Select s = QueryBuilder.select().from(VehicleImagesTbl.NAME);
            s.where(eq(VehicleImagesTbl.Columns.VEHICLE_ID, vehicleId));

            imageList = ci.getCassandraTemplate().query(s, new VehicleImagesRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error Getting the image List for vehicleId: {} - {}", vehicleId, e.getMessage());
            throw new ClientVehicleException(e);
        }
        return imageList;
    }

    @Override
    public ClientVehicle upsetClientVehicle(ClientVehicle cv) throws ClientVehicleException {


        try {
            ClientVehicle cvCheck = get(cv.getClientId(), cv.getVehicleId());

            if (cvCheck == null) {
                // Add new Client Vehicle Record
                Insert insert = cv.getInsertStatement(ci.getCluster());
                ci.getCassandraTemplate().execute(insert);

            } else {
                // Update the Client Vehicle Record
                Statement update = cv.getUpdateStatement(ci.getCluster());
                ci.getCassandraTemplate().execute(update);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClientVehicleException(e);
        }


        return cv;
    }

    @Override
    public ClientVehicleCollection get(UUID clientId) throws ClientVehicleException {

        List<ClientVehicle> cvList = null;

        try {
            Select s = QueryBuilder.select().from(ClientVehiclesTbl.NAME);
            s.where(eq(ClientVehiclesTbl.Columns.CLIENT_ID, clientId));
            cvList = ci.getCassandraTemplate().query(s, new ClientVehicleRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ClientVehicleCollection cvc = ClientVehicleCollection.builder()
                .clientVehicles(cvList).build();

        return cvc;
    }

    @Override
    public ClientVehicle get(UUID clientId, UUID vehicleId) throws ClientVehicleException {

        ClientVehicle cv;

        try {
            Select s = QueryBuilder.select().from(ClientVehiclesTbl.NAME);
            s.where(eq(ClientVehiclesTbl.Columns.CLIENT_ID, clientId)).and(eq(ClientVehiclesTbl.Columns.VEHICLE_ID, vehicleId));
            cv = ci.getCassandraTemplate().queryForObject(s, new ClientVehicleRowMapper());
        } catch (Exception e) {
            if (e.getMessage().contains("0 rows")) {
                cv = null;
            } else {
                e.printStackTrace();
                log.error("Error getting the Client Vehicle for clientId: {} and vehicleId: {} - {}", clientId, vehicleId, e.getMessage());
                throw new ClientVehicleException(e);
            }
        }


        return cv;
    }
}
