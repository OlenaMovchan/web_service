package com.company.web_service.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class ProductDto(
    @Schema(
        description = "Product ID",
        example = "1",
        required = false,
        hidden = true
    )
    val id: Int? = null,

    @get:NotBlank(message = "productDto.name must not be blank")
    @Schema(
        description = "Name of the product",
        example = "Phone",
        required = true
    )
    val name: String,

    @get:NotBlank(message = "productDto.category must not be blank")
    @Schema(
        description = "Category of the product",
        example = "Smartphones",
        required = true
    )
    val category: String,

    @field:NotNull(message = "productDto.price must not be null")
    @field:Positive(message = "productDto.price must be positive")
    @Schema(
        description = "Price of the product",
        example = "100.99",
        required = true
    )
    var price: Double,

    @field:Size(max = 500, message = "productDto.description must be less than 500 chars")
    @Schema(
        description = "Description of the product",
        example = "Latest Apple smartphone with amazing features",
        required = false
    )
    var description: String? = null,
)