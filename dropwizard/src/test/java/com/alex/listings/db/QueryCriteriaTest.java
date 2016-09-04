package com.alex.listings.db;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by alex on 4/09/2016.
 */
public class QueryCriteriaTest {

    @Test
    public void queryCriteria_is_built_with_table_name() {
        String table = "test";
        String field = "field";
        QueryCriteria qc = new QueryCriteria(table, Arrays.asList(field));
        QueryCriteria.Query q = qc.build();

        String expectedQuery = "SELECT " + field + " FROM " + table + ";";
        assertEquals(expectedQuery, q.query());
        assertTrue("Values should be empty", q.bindValues().isEmpty());
    }

    @Test
    public void queryCriteria_with_IN_operator() {
        String table = "test";
        String field = "field";
        String value1 = "value1";
        String value2 = "value2";
        QueryCriteria qc = new QueryCriteria(table, Arrays.asList(field));
        qc.containsAny(field, Arrays.asList(value1, value2));
        QueryCriteria.Query q = qc.build();

        assertThat(q.bindValues(), containsInAnyOrder(value1, value2));
        String expected = "SELECT field FROM test WHERE field IN (?, ?);";
        assertThat(q.query(), equalToIgnoringCase(expected));
    }


}
