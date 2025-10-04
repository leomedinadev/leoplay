package ec.com.leodev.leoplay.domain.repository;

import ec.com.leodev.leoplay.domain.dto.MovieDto;
import ec.com.leodev.leoplay.domain.dto.UpdateMovieDto;

import java.util.List;

public interface IMovieRepository {

    List<MovieDto> findAll();
    MovieDto findById(long id);
    MovieDto save(MovieDto movieDto);
    MovieDto update(long id, UpdateMovieDto updateMovieDto);
    void delete(long id);
}
