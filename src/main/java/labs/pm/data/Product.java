package labs.pm.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static java.math.RoundingMode.HALF_UP;

//abstract method inherited from rateable interface, class acts a conduit for implementation to child classes
public abstract class Product implements Rateable<Product> {
    /** Discount Rate  is 30% **/
    public static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.3);
    private int id;
    private String name;
    private BigDecimal price;
    private Rating rating;

    //Product(){
    //    this(0,"no name", BigDecimal.valueOf(0));
    //}

    Product(int id, String name, BigDecimal price, Rating rating){
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

     Product(int id, String name, BigDecimal price){
        this(id, name, price, Rating.NOT_RATED);
    }
    @Override
    public Rating getRating(){
        return rating;
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

    /** Get value of the best before date for the product**/
    public LocalDate getBestBefore(){
        return LocalDate.now();

    }


    @Override
    public String toString(){
        return getId() + " " + getName() + " " + getPrice() + " " + getDiscount() + " " + getRating().getStars() + "" + getBestBefore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        //if (o == null || getClass() != o.getClass())
        if (o instanceof Product) {
            Product product = (Product) o;
            return id == product.id; //&& Objects.equals(name, product.name); //use objects class as it checks if parameter isn't null before comparison
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
