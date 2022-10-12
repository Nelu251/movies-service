package com.example.moviesservice.model.dto;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieDto implements Serializable {
    String title;
    Long year;
    String director;
    Integer stars;
}
