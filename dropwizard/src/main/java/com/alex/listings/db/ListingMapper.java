package com.alex.listings.db;

import com.alex.listings.entities.Listing;
import com.alex.listings.entities.Surface;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by alex on 20/07/2016.
 */
public class ListingMapper implements ResultSetMapper<Listing> {

    @Override
    public Listing map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Listing(
                r.getString("address"),
                r.getInt("postcode"),
                r.getString("state"),
                r.getInt("bedrooms"),
                r.getInt("bathrooms"),
                Optional.of(new Surface(r.getInt("floorSize"), Surface.Unit.METERS)),
                Optional.of(new Surface(r.getInt("landSize"), Surface.Unit.METERS))
        );
    }
}
