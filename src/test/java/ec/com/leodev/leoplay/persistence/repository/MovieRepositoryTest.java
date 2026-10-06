package ec.com.leodev.leoplay.persistence.repository;

import ec.com.leodev.leoplay.domain.dto.MovieDto;
import ec.com.leodev.leoplay.domain.dto.UpdateMovieDto;
import ec.com.leodev.leoplay.domain.enums.Gender;
import ec.com.leodev.leoplay.domain.exception.MovieAlreadyExistsException;
import ec.com.leodev.leoplay.persistence.entity.MovieEntity;
import ec.com.leodev.leoplay.persistence.mapper.MovieMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieRepositoryTest {

    @Mock
    private ICrudMovieRepository crudMovieRepository;

    @Mock
    private MovieMapper movieMapper;

    private MovieRepository movieRepository;

    private final MovieDto shrek = new MovieDto(null, "Shrek", 90, Gender.ANIMATED,
            LocalDate.of(2001, 5, 18), new BigDecimal("4.5"));

    @BeforeEach
    void setUp() {
        movieRepository = new MovieRepository(crudMovieRepository, movieMapper);
    }

    @Test
    void saveRejectsDuplicateTitle() {
        when(crudMovieRepository.existsByTitle("Shrek")).thenReturn(true);

        assertThatThrownBy(() -> movieRepository.save(shrek))
                .isInstanceOf(MovieAlreadyExistsException.class);
        verify(crudMovieRepository, never()).save(any());
    }

    @Test
    void saveMarksNewMovieAsAvailable() {
        MovieEntity entity = new MovieEntity();
        when(crudMovieRepository.existsByTitle("Shrek")).thenReturn(false);
        when(movieMapper.toEntity(shrek)).thenReturn(entity);
        when(crudMovieRepository.save(entity)).thenReturn(entity);
        when(movieMapper.toDto(entity)).thenReturn(shrek);

        MovieDto result = movieRepository.save(shrek);

        assertThat(result).isEqualTo(shrek);
        assertThat(entity.getStatus()).isEqualTo(MovieEntity.STATUS_AVAILABLE);
    }

    @Test
    void updateReturnsNullWhenMovieDoesNotExist() {
        when(crudMovieRepository.findById(99L)).thenReturn(Optional.empty());

        MovieDto result = movieRepository.update(99L, new UpdateMovieDto("Nuevo", null, 3.0));

        assertThat(result).isNull();
        verify(crudMovieRepository, never()).save(any());
    }

    @Test
    void deleteReturnsFalseWhenMovieDoesNotExist() {
        when(crudMovieRepository.existsById(99L)).thenReturn(false);

        assertThat(movieRepository.delete(99L)).isFalse();
        verify(crudMovieRepository, never()).deleteById(any());
    }

    @Test
    void deleteRemovesExistingMovie() {
        when(crudMovieRepository.existsById(1L)).thenReturn(true);

        assertThat(movieRepository.delete(1L)).isTrue();
        verify(crudMovieRepository).deleteById(1L);
    }
}
