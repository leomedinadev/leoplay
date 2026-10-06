package ec.com.leodev.leoplay.web.controller;

import ec.com.leodev.leoplay.domain.dto.MovieDto;
import ec.com.leodev.leoplay.domain.dto.SuggestRequestDto;
import ec.com.leodev.leoplay.domain.dto.UpdateMovieDto;
import ec.com.leodev.leoplay.domain.service.IPlayAiService;
import ec.com.leodev.leoplay.domain.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name = "Movies", description = "Operations pertaining to movies of LeoPlay")
public class MovieController {

    private final MovieService movieService;
    private final IPlayAiService playAiService;


    public MovieController(MovieService movieService, IPlayAiService playAiService) {
        this.movieService = movieService;
        this.playAiService = playAiService;
    }

    @GetMapping()
    public List<MovieDto> findAll() {
        return movieService.findAll();
    }


    @Operation(
            summary = "Obtener una película por su identificador",
            description = "Retorna la película que coincida con el identificador enviado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Película encontrada"),
                    @ApiResponse(responseCode = "404", description = "Película no encontrada", content = @Content)
            }
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDto> getById(@Parameter(description = "Identificador de la película a recuperar", example = "9") @PathVariable long id) {
        MovieDto movieDto = this.movieService.findById(id);
        if (movieDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movieDto);
    }

    @PostMapping("/suggest")
    public ResponseEntity<String> generateMoviesSuggestion(@RequestBody SuggestRequestDto suggestRequestDto) {
        return ResponseEntity.ok(this.playAiService.generateMoviesSuggestion(suggestRequestDto.userPreferences()));

    }

    @PostMapping
    public ResponseEntity<MovieDto> add(@RequestBody @Valid MovieDto movieDto) {
        MovieDto movieDtoResponse = this.movieService.add(movieDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieDtoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> update(@PathVariable long id, @RequestBody @Valid UpdateMovieDto updateMovieDto) {
        MovieDto updated = this.movieService.update(id, updateMovieDto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        if (!this.movieService.delete(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
