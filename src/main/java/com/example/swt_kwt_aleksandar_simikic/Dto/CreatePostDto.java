package com.example.swt_kwt_aleksandar_simikic.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostDto {
    private String content;
    private Long createdBy;
    private Long containedBy;
}
