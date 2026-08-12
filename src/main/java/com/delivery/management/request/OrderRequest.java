package com.delivery.management.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class OrderRequest {

    @NotNull
    @Digits(integer = 6, fraction = 0, message = "Item number must be at most 6 digits")
    private Long itemNumber;

    @NotNull
    private Integer customerNumber;

    @NotBlank
    private String customerName;

    @Pattern(regexp = "\\d{3}-\\d{4}", message = "Zip code must be in format 123-4567")
    private String zipCode;

    @NotBlank
    @Size(max = 255)
    private String shippingAddress;
}