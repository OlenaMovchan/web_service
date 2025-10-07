package com.company.web_service.service

import com.company.web_service.dto.ProductDto
import com.company.web_service.entity.Product
import com.company.web_service.repository.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.core.convert.ConversionService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ProductService(
    val productRepository: ProductRepository,
    val converterService: ConversionService,
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun createProduct(productDto: ProductDto): ProductDto {

        val productEntity = converterService.convert(productDto, Product::class.java)
            ?: throw IllegalStateException("Conversion from ProductDto to entity failed")

        val savedProduct = productRepository.save(productEntity)

        logger.info("Saved product is : $savedProduct")

        return converterService.convert(savedProduct, ProductDto::class.java)
            ?: throw IllegalStateException("Conversion from entity to ProductDto failed")
    }

    fun getProduct(productId: Int): ProductDto {
        val savedProduct = productRepository.findById(productId)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND,"No Product Found for the Id $productId") }

        return converterService.convert(savedProduct, ProductDto::class.java)
            ?: throw IllegalStateException("Conversion from entity to ProductDto failed")
    }

    fun deleteProduct(productId: Int) {
        val existingProduct = productRepository.findById(productId)
            .orElseThrow() { ResponseStatusException(HttpStatus.NOT_FOUND,"No Product Found for the Id $productId") }

        logger.info("Deleted product is : $existingProduct")

        productRepository.delete(existingProduct)
    }
}
