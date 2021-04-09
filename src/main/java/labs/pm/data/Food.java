package labs.pm.data;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class Food extends Product{
    private LocalDate bestBefore;

    Food( int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore){
        super(id,name,price, rating);
        this.bestBefore = bestBefore;
    }


    /** Get value of the best before date for the product**/
    @Override
    public LocalDate getBestBefore(){
        return bestBefore;

    }

    @Override
    public Product applyRating(Rating newRating) {
        return new Food(getId(), getName(), getPrice(), newRating, bestBefore);
    }

    @Override
    public BigDecimal getDiscount(){
        return(bestBefore.isEqual(LocalDate.now())) ? super.getDiscount() : BigDecimal.ZERO;
    }

    @Override
    public String toString(){
        return super.toString() + ", " + bestBefore;
    }
}

