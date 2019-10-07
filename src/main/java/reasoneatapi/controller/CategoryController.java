package reasoneatapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reasoneatapi.dto.CategoryDTO;
import reasoneatapi.service.CategoryService;

import java.util.UUID;

@RestController
@RequestMapping("/categories")
@Api(tags = {"Catégorie"})
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @ApiOperation(value = "Lister les opérations")
    public Page<CategoryDTO> list(
            @RequestParam(required = false, name = "page", defaultValue = "0") int page,
            @RequestParam(required = false, name = "size", defaultValue = "10") int size
    ) {
        return categoryService.findAll(PageRequest.of(page,size));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Afficher une opération")
    public CategoryDTO show(@PathVariable UUID id) {
        return categoryService.findOne(id);
    }

    @PostMapping
    @ApiOperation(value = "Créer une opération")
    public CategoryDTO create(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Modifier une opération")
    public CategoryDTO update(@RequestBody CategoryDTO categoryDTO, @PathVariable UUID id) {
        return categoryService.update(categoryDTO, id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Supprimer une opération")
    public void delete(@PathVariable UUID id) {
        categoryService.delete(id);
    }
}
