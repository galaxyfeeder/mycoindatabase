package io.github.galaxyfeeder.mycoindatabase;

/**
 * Coin
 * Created by galaxyfeeder on 06/04/17.
 */


class Coin {

    // Basic book data manipulation class
    // Contains basic information on the book

    private long id;
    private String currency;
    private double value;
    private int year;
    private String country;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getValue() {
        return value;
    }

    void setValue(double value) {
        this.value = value;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year= year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Will be used by the ArrayAdapter in the ListView
    // Note that it only produces the value and the currency
    // Extra information should be created by modifying this
    // method or by adding the methods required
    @Override
    public String toString() {
        return String.format("%s - %s", value, currency);
    }
}