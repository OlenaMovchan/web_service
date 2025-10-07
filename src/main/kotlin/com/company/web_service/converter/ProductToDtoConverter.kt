package com.company.web_service.converter

import com.company.web_service.dto.ProductDto
import com.company.web_service.entity.Product
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ProductToDtoConverter : Converter<Product, ProductDto> {

    override fun convert(source: Product): ProductDto {
        return ProductDto(
            id = source.id,
            name = source.name,
            category = source.category,
            price = source.price,
            description = source.description,
        )
    }
}
