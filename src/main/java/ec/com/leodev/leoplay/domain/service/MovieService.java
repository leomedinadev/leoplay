package ec.com.leodev.leoplay.domain.service;

import ec.com.leodev.leoplay.domain.dto.MovieDto;
import ec.com.leodev.leoplay.domain.dto.UpdateMovieDto;
import ec.com.leodev.leoplay.domain.repository.IMovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final IMovieRepository movieRepository;

    public MovieService(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDto> findAll() {
        return this.movieRepository.findAll();
    }

    public MovieDto findById(long id) {
        return this.movieRepository.findById(id);
    }

    public MovieDto add(MovieDto movieDto) {
        return this.movieRepository.save(movieDto);
    }

    public MovieDto update(long id, UpdateMovieDto updateMovieDto) {
        return this.movieRepository.update(id, updateMovieDto);
    }

    public boolean delete(long id) {
        return this.movieRepository.delete(id);
    }
}
