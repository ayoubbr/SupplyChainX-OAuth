package ma.youcode.supplyChainX.controller;

import ma.youcode.supplyChainX.dto.ProductRequest;
import ma.youcode.supplyChainX.dto.ProductResponse;
import ma.youcode.supplyChainX.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @PreAuthorize("hasRole('CHEF_PRODUCTION')")
    public ProductResponse addProduct(@RequestBody ProductRequest productRequest) {
        return productService.save(productRequest);
    }

    @GetMapping
    @PreAuthorize("hasRole('SUPERVISEUR_PRODUCTION')")
    public List<ProductResponse> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasRole('SUPERVISEUR_PRODUCTION')")
    public ProductResponse getProductByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CHEF_PRODUCTION')")
    public ProductResponse updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return productService.update(productRequest, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CHEF_PRODUCTION')")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }

}
