package ma.youcode.supplyChainX.controller;

import lombok.RequiredArgsConstructor;
import ma.youcode.supplyChainX.dto.CustomerRequest;
import ma.youcode.supplyChainX.dto.CustomerResponse;
import ma.youcode.supplyChainX.service.CustomerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @PreAuthorize("hasRole('GESTIONNAIRE_COMMERCIAL')")
    public CustomerResponse createCustomer(@RequestBody CustomerRequest customer) {
        return customerService.save(customer);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('GESTIONNAIRE_COMMERCIAL')")
    public CustomerResponse updateCustomer(@RequestBody CustomerRequest customer,
                                           @PathVariable Long id) {
        return customerService.update(customer, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GESTIONNAIRE_COMMERCIAL')")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('GESTIONNAIRE_COMMERCIAL')")
    public List<CustomerResponse> getCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasRole('GESTIONNAIRE_COMMERCIAL')")
    public List<CustomerResponse> getCustomersByName(@PathVariable String name) {
        return customerService.findByName(name);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('GESTIONNAIRE_COMMERCIAL')")
    public CustomerResponse getCustomerById(@PathVariable Long id) {
        return customerService.findById(id);
    }
}
