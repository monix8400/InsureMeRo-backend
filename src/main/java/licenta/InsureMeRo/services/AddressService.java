package licenta.InsureMeRo.services;

import licenta.InsureMeRo.models.Address;
import licenta.InsureMeRo.repository.AddressRepository;
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

    public Address addAddress(Address address) {
        addressRepository.save(address);
        return address;
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

}
