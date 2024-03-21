package fr.eni.filmotheque.dto;

import lombok.Data;

@Data
public class SearchParams {
    private String search;
    private int genre;
    private int anneeMin = 1900;
    private int anneeMax = 2024;
    private int dureeMin = 1;
    private int dureeMax = 300;
}
