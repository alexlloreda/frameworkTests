package com.alex.listings.db;

import com.alex.listings.Listing;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alex on 20/07/2016.
 */
public class ListingMapper implements ResultSetMapper<Listing> {

    @Override
    public Listing map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Listing(r.getInt("id"), r.getString("address"));
    }
}
