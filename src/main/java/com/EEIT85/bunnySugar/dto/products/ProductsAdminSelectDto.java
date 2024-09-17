package com.EEIT85.bunnySugar.dto.products;

public class ProductsAdminSelectDto {
    private Long id;
    private String categoryName; //categories
    private String productName; //product
    private Integer price; //product_details
    private Boolean enable; //product_details
    private String description; //product_details
    private String materialDescription; // product_details
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String flavor;
    private Integer stocks;
    private String categoryDescription;


    public ProductsAdminSelectDto() {
    }


    public ProductsAdminSelectDto(Long id, String categoryName, String productName, Integer price, Boolean enable, String description, String materialDescription, String img1, String img2, String img3, String img4, String flavor, Integer stocks, String categoryDescription) {
        this.id = id;
        this.categoryName = categoryName;
        this.productName = productName;
        this.price = price;
        this.enable = enable;
        this.description = description;
        this.materialDescription = materialDescription;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
        this.flavor = flavor;
        this.stocks = stocks;
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public Integer getStocks() {
        return stocks;
    }

    public void setStocks(Integer stocks) {
        this.stocks = stocks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }
}
