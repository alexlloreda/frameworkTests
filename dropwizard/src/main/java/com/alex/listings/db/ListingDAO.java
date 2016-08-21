package com.alex.listings.db;

import com.alex.listings.entities.Listing;
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
    @SqlUpdate("create table listings (id varchar(40) primary key, " +
            "address varchar(100), " +
            "postcode int, " +
            "state varchar(5), " +
            "bedrooms int, " +
            "bathrooms int, " +
            "floorSize int, " +
            "landSize int)")
    void createListingsTable();

    @SqlUpdate("insert into listings (id, address, postcode, state, bedrooms, bathrooms, floorSize, landSize), " +
            "values (:id, :address, :postcode, :state, :bedrooms, :bathrooms, :floorSize, :landSize)")
    void addListing(@Bind("id") String id,
                    @Bind("address") String address,
                    @Bind("postcode") int postcode,
                    @Bind("state") String state,
                    @Bind("bedrooms") int bedrooms,
                    @Bind("bathrooms") int bathrooms,
                    @Bind("floorSize") int floorSize,
                    @Bind("landSize") int landSize);

    @SqlQuery("select id, address from listings")
    List<Listing> getListings();

    @SqlUpdate("drop table listings")
    void deleteListingsTable();
}
