package uz.ml.delivering_rest.repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ml.delivering_rest.entity.entity.Carrier;
import uz.ml.delivering_rest.repository.BaseRepository;

import java.util.Optional;

@Repository
public interface CarrierRepository extends JpaRepository<Carrier, Long>, BaseRepository {
    Optional<Carrier> findByName(String name);
}
