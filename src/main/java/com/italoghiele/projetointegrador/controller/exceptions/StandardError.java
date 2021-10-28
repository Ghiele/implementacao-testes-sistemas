package com.italoghiele.projetointegrador.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StandardError {

    private Integer status;

    private String message;

    private Long timestamp;
}
