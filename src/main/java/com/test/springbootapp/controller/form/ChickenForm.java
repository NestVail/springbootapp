package com.test.springbootapp.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

@Getter @Setter
public class ChickenForm {
    private Long id;

    @NotBlank(message = "Brand field cannot be empty or blank.")
    private String brand;

    @NotBlank(message = "Name field cannot be empty or blank")
    private String name;

    @NotBlank(message = "Price field cannot be empty or blank.")
    private BigInteger price;
}
