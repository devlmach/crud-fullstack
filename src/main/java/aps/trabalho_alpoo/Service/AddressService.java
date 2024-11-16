package aps.trabalho_alpoo.Service;

import aps.trabalho_alpoo.Model.Address;
import aps.trabalho_alpoo.Model.Customer;
import aps.trabalho_alpoo.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public void saveAddress(Address address ) {
        address.setCustomer( new Customer() );
        addressRepository.save( address );
    }

}
