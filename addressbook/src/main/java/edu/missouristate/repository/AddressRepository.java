package edu.missouristate.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository; import edu.missouristate.domain.Address;
@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

}