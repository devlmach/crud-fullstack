package aps.trabalho_alpoo.Controller;

import aps.trabalho_alpoo.Model.Address;
import aps.trabalho_alpoo.Model.Customer;

import aps.trabalho_alpoo.Repository.AddressRepository;
import aps.trabalho_alpoo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping( "/" )
    public String home() {
        return "createForm";
    }

    /**
     * <b color="yellow">INICIO DE TUDO.</b> <br/>
     *
     * <b color="yellow"> CREATE PARA CARREGAR PAGINA DE <b color="red"> CADASTRO E EDIÇÃO DE CLIENTES. </b> </b>
     */
    @GetMapping( "/create" )
    public String create() {
        return "create";
    }

    @GetMapping( "/register" )
    public String register() {
        return "register";
    }

    /**
     * <b color="yellow"> CREATE PARA BOTÃO CADASTRAR. </b>
     */
    @PostMapping( "/create" )
    public String create( @ModelAttribute( "customer" ) Customer customer ) {
        customer.getAddresses().forEach( address -> address.setCustomer( customer ));

        customerRepository.save( customer );
        return "redirect:/list";
    }

    /**
     * <b color="yellow"> CARREGAR PAGINA DE LISTA DE CLIENTES. </b>
     */
    @GetMapping( "/list" )
    public String list( Model model, @RequestParam(defaultValue = "0") int page ) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Customer> customer = customerRepository.findAll( pageable );
        model.addAttribute( "customer", customer );
        return "list";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit( @PathVariable( value = "id" ) Long id, AddressRepository addressRepository ) {
        ModelAndView modelAndView = new ModelAndView( "create" );
        Customer findCustomer = customerRepository.findById( id ).orElseThrow(() -> new IllegalArgumentException("cliente nao encontrado"));

        Address newAddress = new Address();
        newAddress.setCustomer(findCustomer);
        findCustomer.getAddresses().add( newAddress );
        
        modelAndView.addObject( "customer", findCustomer );
        return modelAndView;
    }

    @GetMapping( "/delete/{id}" )
    public String delete( @PathVariable( value = "id" ) Long id ) {
        customerRepository.deleteById( id );
        return "redirect:/list";
    }
}


