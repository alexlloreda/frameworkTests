package com.alex.listings;

import com.alex.listings.db.ListingDAO;
import com.alex.listings.db.ListingMapper;
import com.alex.listings.entities.Listing;
import com.alex.listings.entities.Saying;
import com.alex.listings.entities.Surface;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Something else
 * Created by alex on 14/07/2016.
 */
@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter = new AtomicLong();
    private final ListingDAO listingDAO;
    private final DBI jdbi;

    public HelloWorldResource(ListingDAO listingDAO, String template, String defaultName, DBI jdbi) {
        this.listingDAO = listingDAO;
        this.template = template;
        this.defaultName = defaultName;
        this.jdbi = jdbi;

        //listingDAO.deleteListingsTable();
        //listingDAO.createListingsTable();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.or(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }

    @GET
    @Timed
    @Path("/listings")
    public List<Listing> getListings() {
        return listingDAO.getListings();
    }

    @POST
    public void createListing(Listing l) {
        listingDAO.addListing(UUID.randomUUID().toString(),
                l.getAddress(),
                l.getPostcode(),
                l.getState(),
                l.getBedrooms(),
                l.getBathrooms(),
                l.getFloorSize().map(Surface::getAmount).orElse(0),
                l.getLandSize().map(Surface::getAmount).orElse(0));
    }

    @GET
    @Path("/listingsf")
    public List<Listing> getListingsWithFilter(
            @QueryParam("postcode") List<String> postcodes,
            @QueryParam("not-postcode") List<String> notPostcodes) {
        Handle h = jdbi.open();
        try {
            StringBuilder query = new StringBuilder("select * from listings where postcode in (");
            query.append(postcodes.stream().collect(Collectors.joining(", ")));
            query.append(")");
            return h.createQuery(query.toString()).map(new ListingMapper()).list();
        } finally {
            h.close();
        }
    }
}
