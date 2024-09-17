package com.EEIT85.bunnySugar.dto.products;

public class ProductsUpdateDto extends ProductsAdminBaseDto {

    private  Long id;

    public ProductsUpdateDto() {
    }

    public ProductsUpdateDto(String productName, Integer stocks, String description, Integer price, String materialDescription, String categoryDescription, Boolean enable, String categoryName, String flavor, String img1, String img2, String img3, String img4, Long id) {
        super(productName, stocks, description, price, materialDescription, categoryDescription, enable, categoryName, flavor, img1, img2, img3, img4);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}