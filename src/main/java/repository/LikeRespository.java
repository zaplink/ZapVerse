package repository;

import model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRespository extends JpaRepository<Like, Integer> {
}
