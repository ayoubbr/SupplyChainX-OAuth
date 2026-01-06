package ma.youcode.supplyChainX.controller;

import ma.youcode.supplyChainX.dto.ProductionOrderRequest;
import ma.youcode.supplyChainX.dto.ProductionOrderResponse;
import ma.youcode.supplyChainX.model.ProductionOrder;
import ma.youcode.supplyChainX.service.ProductionOrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/production-orders")
public class ProductionOrderController {

    private final ProductionOrderService productionOrderService;

    public ProductionOrderController(ProductionOrderService productionOrderService) {
        this.productionOrderService = productionOrderService;
    }

    @PostMapping
    @PreAuthorize("hasRole('CHEF_PRODUCTION')")
    public ProductionOrderResponse create(@RequestBody ProductionOrderRequest request) {
        return productionOrderService.save(request);
    }

    @GetMapping
    @PreAuthorize("hasRole('SUPERVISEUR_PRODUCTION')")
    public List<ProductionOrderResponse> getAll() {
        return productionOrderService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SUPERVISEUR_PRODUCTION')")
    public ProductionOrderResponse getById(@PathVariable Long id) {
        return productionOrderService.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CHEF_PRODUCTION')")
    public ProductionOrderResponse update(@RequestBody ProductionOrderRequest productionOrderRequest, Long id) {
        return productionOrderService.update(productionOrderRequest, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CHEF_PRODUCTION')")
    public void delete(@PathVariable Long id) {
        productionOrderService.delete(id);
    }

    @PutMapping("/cancel/{id}")
    @PreAuthorize("hasRole('CHEF_PRODUCTION')")
    public void cancel(@PathVariable Long id) {
        productionOrderService.cancel(id);
    }


}
