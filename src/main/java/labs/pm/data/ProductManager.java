package labs.pm.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class ProductManager {

    private Locale locale;
    private ResourceBundle resources;
    private DateTimeFormatter dateFormat;
    private NumberFormat moneyFormat;

    private Map<Product, List<Review>> products;


    public ProductManager(Locale locale) {
        products = new HashMap<>();
        this.locale = locale;
        resources = ResourceBundle.getBundle("resources",locale);
        dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
        moneyFormat = NumberFormat.getCurrencyInstance(locale);

    }

    //allows you to disguise what sub-type you are using of Product, turn constructors to default access modifiers, in future shop would not need to change its function if more products added
    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore){
        Product product = new Food(id, name, price, rating, bestBefore);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating){
        Product product = new Drink(id, name, price, rating);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }

    public Product reviewProduct(Product product, Rating rating, String comments){
        List<Review> reviews = products.get(product);
        products.remove(product, reviews);
        reviews.add(new Review(rating, comments));

        int sum = 0;
        for(Review review: reviews){
            sum += review.getRating().ordinal();

        }
        product = product.applyRating(Rateable.convert(Math.round((float) sum / reviews.size())));

        products.put(product,reviews);
        return product;

    }

    public void printProductReport(Product product){

        List<Review> reviews = products.get(product);

        StringBuilder txt = new StringBuilder();
        txt.append(MessageFormat.format(resources.getString("product"),
                product.getName(),
                moneyFormat.format(product.getPrice()),
                product.getRating().getStars(), //help
                dateFormat.format(product.getBestBefore())));
        txt.append('\n');
        for(Review review: reviews) {
            txt.append(MessageFormat.format(resources.getString("review"),
                        review.getRating().getStars(),
                        review.getComments()));
            txt.append('\n');
            }
            if(reviews.isEmpty()){
                txt.append(resources.getString("no.reviews"));
                txt.append('\n');
            }
            System.out.println(txt);
    }
}
