package ec.com.leodev.leoplay.persistence.repository;

import ec.com.leodev.leoplay.domain.dto.MovieDto;
import ec.com.leodev.leoplay.domain.dto.UpdateMovieDto;
import ec.com.leodev.leoplay.domain.exception.MovieAlreadyExistsException;
import ec.com.leodev.leoplay.domain.repository.IMovieRepository;
import ec.com.leodev.leoplay.persistence.entity.MovieEntity;
import ec.com.leodev.leoplay.persistence.mapper.MovieMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository implements IMovieRepository {

    private final ICrudMovieRepository crudMovieRepository;
    private final MovieMapper movieMapper;

    public MovieRepository(ICrudMovieRepository crudMovieRepository, MovieMapper movieMapper) {
        this.crudMovieRepository = crudMovieRepository;
        this.movieMapper = movieMapper;
    }


    @Override
    public List<MovieDto> findAll() {
        return this.movieMapper.toDto(this.crudMovieRepository.findAll());
    }

    @Override
    public MovieDto findById(long id) {
        MovieEntity movieEntity = this.crudMovieRepository.findById(id).orElse(null);
        return this.movieMapper.toDto(movieEntity);
    }

    @Override
    public MovieDto save(MovieDto movieDto) {
        if (this.crudMovieRepository.findFistByTitle(movieDto.title()) != null) {
            throw new MovieAlreadyExistsException(movieDto.title());
        }
        MovieEntity movieEntity = this.movieMapper.toEntity(movieDto);
        movieEntity.setStatus("1");
        return this.movieMapper.toDto(this.crudMovieRepository.save(movieEntity));
    }

    @Override
    public MovieDto update(long id, UpdateMovieDto updateMovieDto) {
        MovieEntity movieEntity = this.crudMovieRepository.findById(id).orElse(null);
        if (movieEntity == null) {
            return null;
        }
        this.movieMapper.updateEntityFromDto(updateMovieDto, movieEntity);
        return this.movieMapper.toDto(this.crudMovieRepository.save(movieEntity));
    }

    @Override
    public void delete(long id) {
        this.crudMovieRepository.deleteById(id);
    }
}
