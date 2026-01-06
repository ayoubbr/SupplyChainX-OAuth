package ma.youcode.supplyChainX.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import ma.youcode.supplyChainX.model.Supplier;
import ma.youcode.supplyChainX.service.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@Tag(name = "Supplier Controller", description = "CRUD operations for managing suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('GESTIONNAIRE_APPROVISIONNEMENT')")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        return  ResponseEntity.ok(supplierService.findById(id));
    }

    @GetMapping()
    @PreAuthorize("hasRole('SUPERVISEUR_LOGISTIQUE')")
    public List<Supplier> getAllSuppliers() {
        return supplierService.findAll();
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasRole('RESPONSABLE_ACHATS')")
    public Supplier getByName(@PathVariable String name) {
        return supplierService.findByName(name);
    }

    @PostMapping()
    @PreAuthorize("hasRole('GESTIONNAIRE_APPROVISIONNEMENT')")
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return supplierService.save(supplier);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('GESTIONNAIRE_APPROVISIONNEMENT')")
    public Supplier updateSupplier(@RequestBody Supplier supplier, @PathVariable Long id) {
        return supplierService.update(supplier, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GESTIONNAIRE_APPROVISIONNEMENT')")
    public Supplier deleteSupplier(@PathVariable Long id) {
        return supplierService.deleteById(id);
    }
}
