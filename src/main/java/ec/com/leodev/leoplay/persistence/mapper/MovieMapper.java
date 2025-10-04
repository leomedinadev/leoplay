package ec.com.leodev.leoplay.persistence.mapper;

import ec.com.leodev.leoplay.domain.dto.MovieDto;
import ec.com.leodev.leoplay.domain.dto.UpdateMovieDto;
import ec.com.leodev.leoplay.persistence.entity.MovieEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GenderMapper.class})
public interface MovieMapper {

    @Mapping(source = "title", target = "title")
    @Mapping(source = "duration", target = "duration")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "stringToGender")
    @Mapping(source = "releaseDate", target = "releaseDate")
    @Mapping(source = "rating", target = "rating")
    MovieDto toDto(MovieEntity movieEntity);

    List<MovieDto> toDto(Iterable<MovieEntity> entities);

    @InheritInverseConfiguration
    @Mapping(source = "gender", target = "gender", qualifiedByName = "genderToString")
    MovieEntity toEntity(MovieDto movieDto);

    @Mapping(target = "title", source = "title")
    @Mapping(target = "releaseDate", source = "releaseDate")
    @Mapping(target = "rating", source = "rating")
    void updateEntityFromDto(UpdateMovieDto updateMovieDto, @MappingTarget MovieEntity movieEntity);
}
