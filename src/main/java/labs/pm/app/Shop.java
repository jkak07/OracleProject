package labs.pm.app;

import labs.pm.data.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Locale;

// Class represents an application that manages Products
public class Shop {

    public static void main(String[] args){
        ProductManager pm = new ProductManager("en-GB");

        pm.createProduct(101, "Tea",BigDecimal.valueOf(1.99), Rating.NOT_RATED);

        pm.reviewProduct(101, Rating.FOUR_STAR, "nice hot cup of tea");

        pm.reviewProduct(101, Rating.TWO_STAR, "rather weak tea");
        pm.reviewProduct(101, Rating.FOUR_STAR, "fine tea");
        pm.reviewProduct(101, Rating.FOUR_STAR, "good tea");
        pm.reviewProduct(101, Rating.FIVE_STAR, "perfect tea");
        pm.reviewProduct(101, Rating.THREE_STAR, "add some lemon");
        //pm.printProductReport(101);

        pm.changeLocale("en-US");

        pm.createProduct(102, "Coffee", BigDecimal.valueOf(2.49), Rating.NOT_RATED);
        pm.reviewProduct(102, Rating.THREE_STAR, "average");
        pm.reviewProduct(102, Rating.TWO_STAR, "rather weak coffee");
        pm.reviewProduct(102, Rating.ONE_STAR, "abysmal");
        pm.reviewProduct(102, Rating.FOUR_STAR, "good coffee");
        pm.reviewProduct(102, Rating.FIVE_STAR, "perfect coffee");
        pm.reviewProduct(102, Rating.ONE_STAR, "what is this, sewage?");
        //pm.printProductReport(102);


        pm.createProduct(103, "Cake", BigDecimal.valueOf(3.99),  Rating.NOT_RATED, LocalDate.now().plusDays(2));
        pm.reviewProduct(103, Rating.THREE_STAR, "average");
        pm.reviewProduct(103, Rating.FIVE_STAR, "cake from heaven");
        pm.reviewProduct(103, Rating.FIVE_STAR, "if Carlsberg did Cakes");
        pm.reviewProduct(103, Rating.FOUR_STAR, "good cake");
        pm.reviewProduct(103, Rating.FIVE_STAR, "exquisite");
        pm.reviewProduct(103, Rating.ONE_STAR, "what is this, cardboard?");
        //pm.printProductReport(103);

        pm.createProduct(106, "Chocolate", BigDecimal.valueOf(2.50),  Rating.NOT_RATED, LocalDate.now().plusDays(4));
        pm.reviewProduct(106, Rating.TWO_STAR, "");
        pm.reviewProduct(106, Rating.THREE_STAR, "");
        pm.reviewProduct(106, Rating.TWO_STAR, "");
        pm.reviewProduct(106, Rating.ONE_STAR, "");
        pm.reviewProduct(106, Rating.FIVE_STAR, "");

        Comparator<Product> ratingSorter = (p1,p2) -> p2.getRating().ordinal() - p1.getRating().ordinal();
        Comparator<Product> sortByID = (p1,p2) -> p2.getId() - p1.getId();
        Comparator<Product> priceSorter = (p1,p2) -> p2.getPrice().compareTo(p1.getPrice());

        //pm.printProducts(sortByID);
        //pm.printProducts(ratingSorter.thenComparing(priceSorter).reversed());
        //pm.printProducts(p -> p.getPrice().floatValue() <2 ,priceSorter);

        pm.getDiscount().forEach( (rating, discount) -> System.out.println(rating + "\t" + discount));




    }
}
