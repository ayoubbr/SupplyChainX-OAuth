package ma.youcode.supplyChainX.controller;

import ma.youcode.supplyChainX.model.RawMaterial;
import ma.youcode.supplyChainX.service.RawMaterialService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/raw-materials")
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;

    public RawMaterialController(RawMaterialService rawMaterialService) {
        this.rawMaterialService = rawMaterialService;
    }

    @GetMapping
    @PreAuthorize("hasRole('SUPERVISEUR_LOGISTIQUE')")
    public List<RawMaterial> getRawMaterials() {
        return rawMaterialService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('GESTIONNAIRE_APPROVISIONNEMENT')")
    public RawMaterial getRawMaterialById(@PathVariable Long id) {
        return rawMaterialService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('GESTIONNAIRE_APPROVISIONNEMENT')")
    public RawMaterial createRawMaterial(@RequestBody RawMaterial rawMaterial) {
        return rawMaterialService.save(rawMaterial);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('GESTIONNAIRE_APPROVISIONNEMENT')")
    public RawMaterial updateRawMaterial(@RequestBody RawMaterial rawMaterial, @PathVariable Long id) {
        return rawMaterialService.update(rawMaterial, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GESTIONNAIRE_APPROVISIONNEMENT')")
    public void deleteRawMaterial(@PathVariable Long id) {
        rawMaterialService.deleteById(id);
    }

    @GetMapping("/below-stock")
    @PreAuthorize("hasRole('SUPERVISEUR_LOGISTIQUE')")
    public List<RawMaterial> getRawMaterialsBelowStock() {
        return rawMaterialService.getRawMaterialsBelowStock();
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasRole('SUPERVISEUR_LOGISTIQUE')")
    public List<RawMaterial> getRawMaterialByName(@PathVariable String name) {
        return rawMaterialService.findByName(name);
    }

}
