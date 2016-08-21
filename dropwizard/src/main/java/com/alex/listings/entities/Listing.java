package com.alex.listings.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

/**
 * Created by alex on 20/07/2016.
 */

public final class Listing {
    private final String address;
    private final int postcode;
    private final String state;
    private final int bedrooms;
    private final int bathrooms;
    private final Optional<Surface> floorSize;
    private final Optional<Surface> landSize;

    @JsonCreator
    public Listing(@JsonProperty("address") String address,
                   @JsonProperty("postcode") int postcode,
                   @JsonProperty("state") String state,
                   @JsonProperty("bedrooms") int bedrooms,
                   @JsonProperty("bathrooms") int bathrooms,
                   @JsonProperty("floorSize") Optional<Surface> floorSize,
                   @JsonProperty("landSize") Optional<Surface> landSize) {
        this.address = address;
        this.postcode = postcode;
        this.state = state;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.floorSize = floorSize;
        this.landSize = landSize;
    }

    public String getAddress() {
        return address;
    }

    public int getPostcode() {
        return postcode;
    }

    public String getState() {
        return state;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public Optional<Surface> getFloorSize() {
        return floorSize;
    }

    public Optional<Surface> getLandSize() {
        return landSize;
    }
}
