package com.alex.listings;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by alex on 20/07/2016.
 */
public class Listing {
    private final int id;
    private final String address;

    @JsonCreator
    public Listing(int id, String address) {
        this.id = id;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }
}
