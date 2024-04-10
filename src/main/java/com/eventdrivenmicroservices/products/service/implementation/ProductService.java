package com.eventdrivenmicroservices.products.service.implementation;

import com.eventdrivenmicroservices.products.model.Product;
import com.eventdrivenmicroservices.products.service.definition.IProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    @Override
    public String createProduct(Product product) {
        return "Product created";
    }
}
