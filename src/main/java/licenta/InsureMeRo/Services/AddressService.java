package licenta.InsureMeRo.Services;

import licenta.InsureMeRo.Models.Address;
import licenta.InsureMeRo.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void addAddress(Address address) {
        addressRepository.save(address);
    }

    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    //update
}
