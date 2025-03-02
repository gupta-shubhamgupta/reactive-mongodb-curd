package com.personal.learning.utils;

import com.personal.learning.dto.ProductDto;
import com.personal.learning.entity.Product;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class AppUtils {

    public static ProductDto entityToDto(Product product){
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
//        System.out.println(product.getDob().toInstant());
//        System.out.println(productDto.getDob().toString());
        return productDto;
    }

    public static Product dtoToEntity(ProductDto productDto){
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        Date d  = new Date();
        product.setDob(d);
        return product;
    }
}
