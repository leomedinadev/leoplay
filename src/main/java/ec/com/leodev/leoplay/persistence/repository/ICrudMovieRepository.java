package ec.com.leodev.leoplay.persistence.repository;

import ec.com.leodev.leoplay.persistence.entity.MovieEntity;
import org.springframework.data.repository.CrudRepository;

public interface ICrudMovieRepository extends CrudRepository<MovieEntity, Long> {

    MovieEntity findFistByTitle(String title);
}
