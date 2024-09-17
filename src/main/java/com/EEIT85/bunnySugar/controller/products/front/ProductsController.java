package com.EEIT85.bunnySugar.controller.products.front;

import com.EEIT85.bunnySugar.dto.products.ProductsSelectDto;
import com.EEIT85.bunnySugar.service.products.front.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/api/products")
@RestController
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @GetMapping
    public ResponseEntity<Page<ProductsSelectDto>> getAll(Pageable pageable, @RequestParam(required = false) String sort) {
        Page<ProductsSelectDto> result = productsService.getAll(pageable, sort);
        if (result.isEmpty()) {
            return new ResponseEntity<>(Page.empty(pageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductsSelectDto> getById(@PathVariable Long id) {
        ProductsSelectDto product = productsService.getById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<Set<String>> getAllCategoryNames() {
        Set<String> categories = productsService.getAllCategoryNames();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/categories/flavors")
    public ResponseEntity<Set<String>> getFlavorsByCategoryName(@RequestParam String categoryName) {
        Set<String> flavors = productsService.getFlavorsByCategoryName(categoryName);
        return new ResponseEntity<>(flavors, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<Page<ProductsSelectDto>> getByCategoryName(@PathVariable String categoryName, Pageable pageable, @RequestParam(required = false) String sort) {
        Page<ProductsSelectDto> result = productsService.getProductsByCategoryName(categoryName, pageable, sort);
        if (result.isEmpty()) {
            return new ResponseEntity<>(Page.empty(pageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/flavor/{flavor}")
    public ResponseEntity<Page<ProductsSelectDto>> getByFlavorName(@PathVariable String flavor, Pageable pageable, @RequestParam(required = false) String sort) {
        Page<ProductsSelectDto> result = productsService.getProductsByFlavor(flavor, pageable, sort);
        if (result.isEmpty()) {
            return new ResponseEntity<>(Page.empty(pageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductsSelectDto>> searchProducts(@RequestParam String keyword, Pageable pageable, @RequestParam(required = false) String sort) {
        Page<ProductsSelectDto> result = productsService.searchProductsByNameLike(keyword, pageable, sort);
        if (result.isEmpty()) {
            return new ResponseEntity<>(Page.empty(pageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}


