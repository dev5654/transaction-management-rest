package uz.ml.delivering_rest.repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ml.delivering_rest.entity.entity.Request;
import uz.ml.delivering_rest.repository.BaseRepository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>, BaseRepository {
}
