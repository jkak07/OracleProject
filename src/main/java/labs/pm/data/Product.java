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

    public Product(){
        this(0,"no name", BigDecimal.valueOf(0));
    }

    public Product(int id, String name, BigDecimal price, Rating rating){
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public Product(int id, String name, BigDecimal price){
        this(id, name, price, Rating.NOT_RATED);
    }

    public Rating getRating(){
        return rating;
    }
    /**Creating new Product so it's immutable but with new rating**/
    public Product applyRating(Rating newRating){
        return new Product(id, name, price, newRating);
    }


    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public BigDecimal getPrice() {
        return price;
    }

    /** Calculate discount  based on a product price and discount rate, returns value of discount **/

    public BigDecimal getDiscount(){
        return this.price.multiply(DISCOUNT_RATE).setScale(2, HALF_UP);
    }
}
