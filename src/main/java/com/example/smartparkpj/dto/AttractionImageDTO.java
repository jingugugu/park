package com.example.smartparkpj.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttractionImageDTO {
    private String uuid;
    private String fileName;
    private int ord;
    private int ano;
}
