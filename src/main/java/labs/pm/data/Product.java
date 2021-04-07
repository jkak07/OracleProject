package labs.pm.data;

import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_UP;

public class Product {
    /** Discount Rate  is 10% **/
    public static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);
    private int id;
    private String name;
    private BigDecimal price;
    private Rating rating;

    public Rating getRating(){
        return rating;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }
    /** Calculate discount  based on a product price and discount rate, returns value of discount **/

    public BigDecimal getDiscount(){
        return this.price.multiply(DISCOUNT_RATE).setScale(2, HALF_UP);
    }
}
