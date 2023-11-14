package ar.unrn.tp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Page {
    private String id;
    private String title;
    private String text;
    private String author;
    private LocalDate date;
}
