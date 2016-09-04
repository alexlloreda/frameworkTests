package com.alex.listings.db;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Create complex queries based on filters
 * Created by alex on 23/08/2016.
 */
public class QueryCriteria {
    public class Query {
        private final String query;
        private final List<String> bindValues;

        protected Query(String query, List<String> values) {
            this.query = query;
            this.bindValues = values;
        }

        public String query() { return query; }
        public List<String> bindValues() { return bindValues; }
    }

    private abstract class Op {
        public abstract String preparedOp();
        public abstract Collection<String> values();
    }

    private final class In extends Op {
        private final Set<String> values;
        private final String field;

        public In(String field, Collection<String> values) {
            this.values = new HashSet<>(values);
            this.field = field;
        }

        @Override
        public String preparedOp() {
            StringBuilder sb = new StringBuilder(field).append(" IN (");
            sb.append(values.stream().map(s -> "?").collect(Collectors.joining(", ")));
            sb.append(")");
            return sb.toString();
        }

        @Override
        public Collection<String> values() {
            return values;
        }
    }
    // field operator values


    private final String tableName;

    // Ordered collection of fields to select
    private final List<String> fields;

    private final List<Op> ops = new ArrayList<>();

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
        ops.add(new In(field, values));
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
    public Query build() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(fields.stream().collect(Collectors.joining(", ")));
        sb.append(" FROM ").append(tableName);
        if (!ops.isEmpty()) {
            sb.append(" WHERE ");
            sb.append(ops.stream().map(Op::preparedOp).collect(Collectors.joining(" AND ")));
        }
        sb.append(";");
        return new Query(sb.toString(), ops.stream().flatMap(o -> o.values().stream()).collect(Collectors.toList()));
    }
}
