package edu.missouristate;

// Keegan Spell

import java.math.BigDecimal;

public class Item {

    private String sku;
    private String shortDescription;
    private boolean taxable;
    private BigDecimal price;

    public Item(String sku, String shortDescription, boolean taxable, BigDecimal price) {
        super();
        this.sku = sku;
        this.shortDescription = shortDescription;
        this.taxable = taxable;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public boolean isTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
