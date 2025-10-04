package ec.com.leodev.leoplay.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "movie")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 150, unique = true)
    private String title;

    @Column(name = "duration", nullable = false, precision = 3)
    private Integer duration;

    @Column(name = "gender", nullable = false, length = 40)
    private String gender;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "rating", nullable = false, scale = 2)
    private BigDecimal rating;

    @Column(name = "status", nullable = false, length = 1)
    private String status;
}
