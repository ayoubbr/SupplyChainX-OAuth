package ma.youcode.supplyChainX.controller;

import ma.youcode.supplyChainX.dto.SupplyOrderRequest;
import ma.youcode.supplyChainX.dto.SupplyOrderResponse;
import ma.youcode.supplyChainX.model.SupplyOrder;
import ma.youcode.supplyChainX.service.SupplyOrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supply-orders")
public class SupplyOrderController {

    private final SupplyOrderService supplyOrderService;

    public SupplyOrderController(SupplyOrderService supplyOrderService) {
        this.supplyOrderService = supplyOrderService;
    }

    @PostMapping
    @PreAuthorize("hasRole('RESPONSABLE_ACHATS')")
    public SupplyOrderResponse create(@RequestBody SupplyOrderRequest supplyOrderRequest) {
        SupplyOrder savedOrder = supplyOrderService.save(supplyOrderRequest);
        return supplyOrderService.toResponse(savedOrder);
    }

    @GetMapping
    @PreAuthorize("hasRole('SUPERVISEUR_LOGISTIQUE')")
    public List<SupplyOrderResponse> getAll() {
        List<SupplyOrder> orders = supplyOrderService.findAll();
        return supplyOrderService.toResponseList(orders);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('RESPONSABLE_ACHATS')")
    public SupplyOrderResponse getById(@PathVariable Long id) {
        SupplyOrder order = supplyOrderService.findById(id);
        return supplyOrderService.toResponse(order);
    }

    @GetMapping("/supplier/{supplierId}")
    public List<SupplyOrderResponse> getBySupplierId(@PathVariable Long supplierId) {
        List<SupplyOrder> orders = supplyOrderService.findBySupplierId(supplierId);
        return supplyOrderService.toResponseList(orders);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('RESPONSABLE_ACHATS')")
    public int delete(@PathVariable Long id) {
        return supplyOrderService.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('RESPONSABLE_ACHATS')")
    public SupplyOrderResponse update(@PathVariable Long id, @RequestBody SupplyOrderRequest supplyOrderRequest) {
        SupplyOrder updatedOrder = supplyOrderService.update(supplyOrderRequest, id);
        return supplyOrderService.toResponse(updatedOrder);
    }

}