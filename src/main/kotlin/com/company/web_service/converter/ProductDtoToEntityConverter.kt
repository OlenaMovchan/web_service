package com.company.web_service.converter

import com.company.web_service.dto.ProductDto
import com.company.web_service.entity.Product
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ProductDtoToEntityConverter : Converter<ProductDto, Product> {

    override fun convert(source: ProductDto): Product {
        return Product(
            id = source.id,
            name = source.name,
            category = source.category,
            price = source.price,
            description = source.description,
            )
    }
}

