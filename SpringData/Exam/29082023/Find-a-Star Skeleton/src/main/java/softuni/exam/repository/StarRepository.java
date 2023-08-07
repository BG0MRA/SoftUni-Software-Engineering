package softuni.exam.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StarType;

import java.util.List;
import java.util.Optional;

// TODO:
@Repository
public interface StarRepository extends JpaRepository<Star, Long> {

    Optional<Star> findFirstByName(String name);
    List<Star> findAllByStarTypeOrderByLightYearsAsc(StarType starType);

    List<Star> findAllByStarTypeAndObserversIsEmptyOrderByLightYearsAsc(StarType starType);


}