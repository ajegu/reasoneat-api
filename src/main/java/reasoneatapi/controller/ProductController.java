package reasoneatapi.controller;

import com.owlike.genson.Genson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import reasoneatapi.dto.ProductDTO;
import reasoneatapi.dto.ProductFilterDTO;
import reasoneatapi.helper.SortHelper;
import reasoneatapi.service.CategoryService;
import reasoneatapi.service.MonthService;
import reasoneatapi.service.ProductService;
import reasoneatapi.validator.ProductValidator;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("products")
@Api(tags = {"Produits"})
public class ProductController {

    private final ProductService productService;

    private final CategoryService categoryService;

    private final MonthService monthService;

    @Autowired
    private SortHelper sortHelper;

    private final Genson genson;

    public ProductController(MonthService monthService, CategoryService categoryService, ProductService productService) {
        this.genson = new Genson();
        this.monthService = monthService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new ProductValidator(productService, categoryService, monthService));
    }

    @GetMapping
    @ApiOperation(value = "Lister les produits")
    public Page<ProductDTO> list(
            @RequestParam(required = false, name = "page", defaultValue = "0") int page,
            @RequestParam(required = false, name = "size", defaultValue = "10") int size,
            @RequestParam(required = false, name = "sortProperty", defaultValue = "name") String sortProperty,
            @RequestParam(required = false, name = "sortDirection", defaultValue = "ASC") Sort.Direction sortDirection,
            @RequestParam(required = false, name = "filters", defaultValue = "{}") String filters
            ) {
        String validSortProperty = sortHelper.getValidSortProperty(sortProperty, ProductDTO.class, "name");
        Sort sort = new Sort(sortDirection, validSortProperty);
        ProductFilterDTO productFilterDTO = genson.deserialize(filters, ProductFilterDTO.class);
        return productService.findAll(productFilterDTO, PageRequest.of(page, size, sort));
    }

    @GetMapping("/{id}")
    @ApiOperation("Afficher un produit")
    public ProductDTO show(@PathVariable UUID id) {
        return productService.findOne(id);
    }

    @PostMapping
    @ApiOperation("Créer un produit")
    @ApiResponses({ @ApiResponse(code = 400, message = "ProductDTO invalid") })
    public ProductDTO create(@Valid @RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation("Modifier un produit")
    @ApiResponses({ @ApiResponse(code = 400, message = "ProductDTO invalid") })
    public ProductDTO update(@Valid @RequestBody ProductDTO productDTO, @PathVariable UUID id) {
        return productService.update(productDTO, id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Supprimer un produit")
    public void delete(@PathVariable UUID id) {
        productService.delete(id);
    }
}
