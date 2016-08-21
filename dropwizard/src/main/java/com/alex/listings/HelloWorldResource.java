package com.alex.listings;

import com.alex.listings.db.ListingDAO;
import com.alex.listings.entities.Listing;
import com.alex.listings.entities.Saying;
import com.alex.listings.entities.Surface;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by alex on 14/07/2016.
 */
@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter = new AtomicLong();
    private final ListingDAO listingDAO;

    public HelloWorldResource(ListingDAO listingDAO, String template, String defaultName) {
        this.listingDAO = listingDAO;
        this.template = template;
        this.defaultName = defaultName;

        listingDAO.deleteListingsTable();
        listingDAO.createListingsTable();
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
                l.getFloorSize().map(s -> s.getAmount()).orElse(0),
                l.getLandSize().map(s -> s.getAmount()).orElse(0));
    }
}
