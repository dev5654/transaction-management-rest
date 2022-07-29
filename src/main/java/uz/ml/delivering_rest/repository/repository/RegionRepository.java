package uz.ml.delivering_rest.repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.ml.delivering_rest.entity.entity.Region;
import uz.ml.delivering_rest.repository.BaseRepository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long>, BaseRepository {
    boolean existsByName(String name);

    Region findByName(String name);


}
