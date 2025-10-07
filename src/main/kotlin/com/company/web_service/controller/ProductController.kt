package com.company.web_service.controller

import com.company.web_service.dto.ProductDto
import com.company.web_service.entity.Product
import com.company.web_service.service.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityRequirements
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/product")
@Validated
@SecurityRequirements(SecurityRequirement(name = "basicAuth", scopes = ["admin"]))
@Tag(name = "Products", description = "Product management endpoints")
class ProductController(val productService: ProductService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new product", description = "Creates a new product and returns it")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Product created",
            content = [Content(mediaType = "application/json",
                schema = Schema(implementation = Product::class))]),
        ApiResponse(responseCode = "400", description = "Invalid input")
    ])
    fun createProduct(@RequestBody @Valid productDto: ProductDto): ProductDto {
        return productService.createProduct(productDto)
    }

    @GetMapping("/{product_id}")
    @Operation(summary = "Get product by id", description = "Returns a product by ID")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Product found",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = Product::class)
            )]),
        ApiResponse(responseCode = "404", description = "Product not found")
    ])
    fun getProduct(@PathVariable("product_id") productId: Int) : ProductDto {
        return productService.getProduct(productId)
    }


    @DeleteMapping("/{product_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete product by id", description = "Deletes a product by ID")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Product deleted"),
        ApiResponse(responseCode = "404", description = "Product not found")
    ])
    fun deleteProduct(@PathVariable("product_id") productId: Int) {
        productService.deleteProduct(productId)

    }
}
