package com.alex.listings.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Create complex queries based on filters
 * Created by alex on 23/08/2016.
 */
public class QueryCriteria {

    private final String tableName;

    // Ordered collection of fields to select
    private final List<String> fields;

    public QueryCriteria(String tableName, List<String> fields) {
        this.tableName = tableName;
        this.fields = new ArrayList<>(fields);
    }

    /**
     * Check if the value of the field is in the given values
     * @param field: Name of the field to check
     * @param values: Collection of possible values the field should have
     */
    public void containsAny(String field, Collection<String> values) {
        // TODO
    }

    /**
     * Check if the value of the field is non of the given values
     * @param field: Name of the field to check
     * @param values: Collection of possible values the field should not have
     */
    public void containesNone(String field, Collection<String> values) {
        // TODO
    }

    /**
     * Self explanatory?
     * @param field: Name of the field to check
     * @param minValue: String representing the min value
     * @param maxValue: String representing the max value
     */
    public void between(String field, String minValue, String maxValue) {
        // TODO
    }


    /**
     * Builds the actual prepared statement
     */
    public void build() {

    }
}
