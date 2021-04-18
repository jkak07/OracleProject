package labs.pm.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


public final class Drink extends Product{

    Drink(int id, String name, BigDecimal price, Rating rating){
        super(id,name,price, rating);

    }

    @Override
    public Product applyRating(Rating newRating) {
        return new Drink(getId(), getName(), getPrice(), newRating);
    }

    @Override
    public BigDecimal getDiscount(){
        return(LocalTime.now().isAfter(LocalTime.of(0,1)) && LocalTime.now().isBefore(LocalTime.of(23,59)))
                ? super.getDiscount() : BigDecimal.ZERO;
    }
}
