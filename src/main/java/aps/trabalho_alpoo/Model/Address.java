package aps.trabalho_alpoo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table( name = "address_tb" )
public class Address {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long idAddress;

    @NotBlank(message = "Não pode campo vazio")
    private String street;

    @NotBlank(message = "Não pode campo vazio")
    private String number;

    @ManyToOne
    @JoinColumn( name = "id_customer", nullable = false)
    private Customer customer;

}
