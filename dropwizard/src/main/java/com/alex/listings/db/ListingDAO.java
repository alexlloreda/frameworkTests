package com.alex.listings.db;

import com.alex.listings.Listing;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by alex on 20/07/2016.
 */
@RegisterMapper(ListingMapper.class)
public interface ListingDAO {
    @SqlUpdate("create table listings (id int primary key, address varchar(100))")
    void createListingsTable();

    @SqlUpdate("insert into listings (id, address), values (:id, :address)")
    void addListing(@Bind("id") int id, @Bind("address") String address);

    @SqlQuery("select id, address from listings")
    List<Listing> getListings();
}
