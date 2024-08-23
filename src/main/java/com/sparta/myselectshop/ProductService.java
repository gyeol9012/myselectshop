package com.sparta.myselectshop;


import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.entity.Product;
import com.sparta.myselectshop.repository.ProductRepositroty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepositroty productRepositroty;

    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = productRepositroty.save(new Product(requestDto));
        return new ProductResponseDto(product);
    }
}
