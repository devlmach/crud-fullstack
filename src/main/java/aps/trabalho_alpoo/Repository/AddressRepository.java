package aps.trabalho_alpoo.Repository;

import aps.trabalho_alpoo.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository< Address, Long > {

}
