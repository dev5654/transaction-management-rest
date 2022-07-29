package uz.ml.delivering_rest.repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ml.delivering_rest.entity.entity.Product;
import uz.ml.delivering_rest.repository.BaseRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, BaseRepository {
}
