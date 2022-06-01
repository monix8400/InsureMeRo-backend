package licenta.InsureMeRo.controllers;

import licenta.InsureMeRo.models.Address;
import licenta.InsureMeRo.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")//(origins = "http://localhost:4200")
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    // standard constructors, dependency injection
    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/getAddresses")
    public List<Address> getUsers() {
        return addressService.getAddresses();
    }

    @PostMapping("/addAddress")
    public void addAddress(@RequestBody Address address) {
        addressService.addAddress(address);
    }

    @GetMapping("/getAddressById/{id}")
    public Address getAddressById(@PathVariable("id") Long id) {
        return addressService.getAddressById(id).orElse(null);
    }

    @DeleteMapping("/deleteAddressById/{id}")
    public void deleteAddressById(@PathVariable("id") Long id) {
        addressService.deleteAddress(id);
    }

    //update

}

