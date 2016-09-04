package com.alex.listings.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

/**
 * Created by alex on 14/07/2016.
 */
public class Saying {
    private long id;

    @Length(max = 3)
    private String content;

    @JsonCreator
    public Saying(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}
