package com.alex.listings.entities;

/**
 * Created by alex on 21/08/2016.
 */
public class Surface {


    public enum Unit {METERS, FEET;}

    private final Unit unit;
    private final int amount;

    public Surface(int amount, Unit unit) {
        this.unit = unit;
        this.amount = amount;
    }

    public Unit getUnit() { return unit; }
    public int getAmount() { return amount; }
}
