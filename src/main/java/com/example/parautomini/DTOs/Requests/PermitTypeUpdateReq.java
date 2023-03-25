package com.example.parautomini.DTOs.Requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermitTypeUpdateReq {
    @NotBlank(message = "Description must not be blank")
    private String description;
}
