package aps.trabalho_alpoo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table( name = "customer_tb" )
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank(message = "Não pode campo vazio")
    private String name;

    @NotBlank(message = "Não pode campo vazio")
    @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}", message = "cpf invalido")
    @Column(length = 11, unique = true)
    private String cpf;

    @OneToMany( mappedBy = "customer", cascade = CascadeType.ALL )
    private List<Address> addresses = new ArrayList<>();

    public void addAddress( Address address ) {
        address.setCustomer( this );
        this.addresses.add( address );
    }

}
