package reasoneatapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reasoneatapi.dto.ProductDTO;
import reasoneatapi.service.ProductService;

import java.util.UUID;

@RestController
@RequestMapping("products")
@Api(tags = {"Produits"})
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @ApiOperation(value = "Lister les produits")
    public Page<ProductDTO> list(
            @RequestParam(required = false, name = "page", defaultValue = "0") int page,
            @RequestParam(required = false, name = "size", defaultValue = "10") int size
    ) {
        return productService.findAll(PageRequest.of(page, size));
    }

    public ProductDTO show(@PathVariable UUID id) {
        return productService.findOne(id);
    }
}