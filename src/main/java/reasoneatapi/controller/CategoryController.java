package reasoneatapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reasoneatapi.dto.CategoryDTO;
import reasoneatapi.service.CategoryService;

import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Page<CategoryDTO> list(
            @RequestParam(required = false, name = "page", defaultValue = "0") int page,
            @RequestParam(required = false, name = "size", defaultValue = "10") int size
    ) {
        return categoryService.findAll(PageRequest.of(page,size));
    }

    @GetMapping("/{id}")
    public CategoryDTO show(@PathVariable UUID id) {
        return categoryService.findOne(id);
    }

    @PostMapping
    public CategoryDTO create(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }

    @PutMapping("/{id}")
    public CategoryDTO update(@RequestBody CategoryDTO categoryDTO, @PathVariable UUID id) {
        return categoryService.update(categoryDTO, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        categoryService.delete(id);
    }
}
