package com.e_commerce.FoodCat.Controller;

import com.e_commerce.FoodCat.entity.Product;
import com.e_commerce.FoodCat.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;


    private static final String IMAGE_DIR = "uploads/"; // Directory to save images

//    @PostMapping
//    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
//        Product savedProduct = productService.save(product);
//        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
//    }


    //add product api
    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("category") String category,
            @RequestParam("image") MultipartFile image) {

        try {
            // Save image to the filesystem

            //String name_dir=IMAGE_DIR + System.currentTimeMillis() + "_" + image.getOriginalFilename().// save with directory to db

            String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            //upload to uploads folder
            Path path = Paths.get(IMAGE_DIR + filename);
            Files.createDirectories(path.getParent());
            Files.write(path, image.getBytes());

            // Create and save product
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setCategory(category);
            product.setImagePath(filename); // Save the image path to the product

            Product savedProduct = productService.save(product);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/search")
//    public List<Product> getProducts(@RequestParam(required = false) String search) {
//        if (search != null && !search.isEmpty()) {
//            return productService.searchProducts(search);
//        } else {
//            return productService.getAllProducts();
//        }
//    }

    //testing search
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String query) {
        return productService.searchProducts(query);
    }
}



