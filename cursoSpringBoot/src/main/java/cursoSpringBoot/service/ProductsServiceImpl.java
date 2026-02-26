package cursoSpringBoot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import cursoSpringBoot.domain.Product;

@Service
public class ProductsServiceImpl implements ProductService {

    List<Product> products = new ArrayList<>(Arrays.asList(
        new Product(1, "Laptop", 999.99, 10),
        new Product(2, "Smartphone", 499.99, 20),
        new Product(3, "Tablet", 299.99, 15),
        new Product(4, "Headphones", 199.99, 25)
    ));

    @Override
    public List<Product> getProducts(){
        return products;
    }

}
