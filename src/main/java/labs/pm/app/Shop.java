package labs.pm.app;

import labs.pm.data.Product;

import java.math.BigDecimal;

// Class represents an application that manages Products
public class Shop {

    public static void main(String[] args){
        Product p1 = new Product();
        p1.setId(8);
        p1.setName("fruit bag");
        p1.setPrice(BigDecimal.valueOf(3.99));
        System.out.println(p1.getId() + " " + p1.getName() + " " + p1.getPrice() + " " +  p1.getDiscount());

    }
}
