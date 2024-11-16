package aps.trabalho_alpoo.Service;

import aps.trabalho_alpoo.Model.Address;
import aps.trabalho_alpoo.Model.Customer;
import aps.trabalho_alpoo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List< Customer > findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findById( Long id ) {
        return customerRepository.findById( id );
    }

    public void save( Customer customer ) {

        if ( customer.getCpf() == null || customer.getCpf().isEmpty()) {
            System.out.println("CPF EM BRANCO");
        }

        if ( customer.getAddresses() != null && !customer.getAddresses().isEmpty() ) {
            for ( Address address : customer.getAddresses() ) {
                address.setCustomer(customer);
            }
        }

        for ( Address address : customer.getAddresses() ) {
            if (address.getStreet().isEmpty() || address.getStreet() == null || address.getNumber().isEmpty() || address.getNumber() == null) {
                JOptionPane.showMessageDialog(null, "CAMPO VAZIO ");
            }

            if ( address.getNumber().isEmpty() ) {
                address.setNumber("S/N");
            }
        }
        customerRepository.save( customer );
    }

    public void deleteCustomerById( Long id ) {
        customerRepository.deleteById( id );
    }

}
