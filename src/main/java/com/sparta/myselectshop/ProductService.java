package com.sparta.myselectshop;


import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.entity.Product;
import com.sparta.myselectshop.repository.ProductRepositroty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepositroty productRepositroty;

    public static final int MIN_MY_PRICE = 100;

    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = productRepositroty.save(new Product(requestDto));
        return new ProductResponseDto(product);
    }

    @Transactional
    public ProductResponseDto updateProduct(Long id,ProductMypriceRequestDto requestDto) {
    int myprice = requestDto.getMyprice();
    if(myprice < MIN_MY_PRICE){
        throw new IllegalArgumentException("유효하지 않은 관심 가격입니다.  최소 " + MIN_MY_PRICE + "원 이상으로 설정해 주세요.");
    }

    Product product = productRepositroty.findById(id).orElseThrow(()->
            new NullPointerException("해당 상품을 찾을 수 없습니다."));
    product.update(requestDto);

    return new ProductResponseDto(product);
    }

    public List<ProductResponseDto> getProduct() {
        List<Product> productList = productRepositroty.findAll();
        List<ProductResponseDto> responseDtoList = new ArrayList<>();

        for (Product product : productList) {
            responseDtoList.add(new ProductResponseDto(product));
        }

        return responseDtoList;
    }
}
