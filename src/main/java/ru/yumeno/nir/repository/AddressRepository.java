package ru.yumeno.nir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yumeno.nir.entity.Address;
import ru.yumeno.nir.entity.District;
import ru.yumeno.nir.entity.Street;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAllByStreet(Street street);

    List<Address> findAllByDistrict(District district);

    Optional<Address> findByApartmentAndHouseAndPorchAndDistrictAndStreet(
            String apartment, String house, String porch, District district, Street street);
}
