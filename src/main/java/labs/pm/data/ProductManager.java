package labs.pm.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class ProductManager {

    private Map<Product, List<Review>> products= new HashMap<>();;
    private ResourceFormatter formatter;
    private static Map<String, ResourceFormatter> formatters =
            Map.of("en-GB", new ResourceFormatter(Locale.UK),
                    "en-US", new ResourceFormatter(Locale.US),
                    "fr-FR", new ResourceFormatter(Locale.FRANCE),
                    "ru-RU", new ResourceFormatter(new Locale("ru", "RU")),
                    "zh-CN", new ResourceFormatter(Locale.CHINA));

    public ProductManager(Locale locale) {
        this(locale.toLanguageTag());
    }

    public ProductManager(String languageTag) {
        changeLocale(languageTag);
    }

    public void changeLocale(String languageTag){
        formatter = formatters.getOrDefault(languageTag,formatters.get("en-GB"));
    }

    public static Set<String> getSupportedLocales(){
        return formatters.keySet();
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
        txt.append(formatter.formatProduct(product));
        txt.append('\n');
        Collections.sort(reviews);
        for(Review review: reviews) {
            txt.append(formatter.formatReviews(review));
            txt.append('\n');
            }
            if(reviews.isEmpty()){
                txt.append(formatter.getText("no.reviews"));
                txt.append('\n');
            }
            System.out.println(txt);
    }

    public void printProducts(Comparator<Product> sorter){
        List<Product> productList = new ArrayList<>(products.keySet());
        productList.sort(sorter);
        StringBuilder txt = new StringBuilder();
        for (Product product : productList){
            txt.append(formatter.formatProduct((product)));
            txt.append('\n');
        }
        System.out.println(txt);


    }

    public Product findProduct(int id){
        Product result = null;
        for(Product prod: products.keySet()){
            if(prod.getId() == id){
                result = prod;
                break;
            }
        }
        return result;
    }

    public Product reviewProduct(int id, Rating rating, String comments){

        Product product = findProduct(id);

        Product reviewedProduct = reviewProduct(product, rating, comments);

     return reviewedProduct;
    }

    public void printProductReport(int id){
        Product product = findProduct(id);

        printProductReport(product);
    }

    private static class ResourceFormatter{

        private Locale locale;
        private ResourceBundle resources;
        private DateTimeFormatter dateFormat;
        private NumberFormat moneyFormat;



        private ResourceFormatter(Locale locale){
            this.locale = locale;
            this.resources = ResourceBundle.getBundle("resources",locale);
            this.dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
            this.moneyFormat = NumberFormat.getCurrencyInstance(locale);
        }

        private String formatProduct(Product product){
            return MessageFormat.format(resources.getString("product"),
                    product.getName(),
                    moneyFormat.format(product.getPrice()),
                    product.getRating().getStars(),
                    dateFormat.format(product.getBestBefore()));
        }
        private String formatReviews(Review review){
            return MessageFormat.format(resources.getString("review"),
                    review.getRating().getStars(),
                    review.getComments());
        }

        private String getText(String key){
            return resources.getString(key);

        }




    }
}
