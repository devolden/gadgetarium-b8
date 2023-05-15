package com.example.gadgetariumb8.db.api;

import com.example.gadgetariumb8.db.dto.request.ProductRequest;
import com.example.gadgetariumb8.db.dto.response.*;
import com.example.gadgetariumb8.db.model.Category;
import com.example.gadgetariumb8.db.service.BrandService;
import com.example.gadgetariumb8.db.service.CategoryService;
import com.example.gadgetariumb8.db.service.ProductService;
import com.example.gadgetariumb8.db.service.SubProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
@Tag(name = "Product Admin API")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminProductApi {
    private final ProductService productService;
    private final SubProductService subProductService;
    private final BrandService brandService;
    private final CategoryService categoryService;

    @Operation(summary = "To save the product.", description = "This method to save the product.")
    @PostMapping
    public SimpleResponse saveProduct(@RequestBody @Valid ProductRequest productRequest) {
        return productService.saveProduct(productRequest);
    }

    @Operation(summary = "Get all products", description = "This method to find all products in admin page")
    @GetMapping
    public PaginationResponse<ProductAdminResponse> getAll(@RequestParam(required = false) String keyWord,
                                                           @RequestParam(defaultValue = "все товары") String status,
                                                           @RequestParam(required = false) LocalDate from,
                                                           @RequestParam(required = false) LocalDate before,
                                                           @RequestParam(required = false) String sortBy,
                                                           @RequestParam(required = false, defaultValue = "1") int page,
                                                           @RequestParam(required = false, defaultValue = "7") int pageSize) {
        return productService.getAll(keyWord, status, from, before, sortBy, page, pageSize);
    }

    @GetMapping("/last_views")
    @Operation(summary = "Last viewed products ", description = "This method shows the last 7 items viewed")
    @PreAuthorize("hasAuthority('USER')")
    public PaginationResponse<SubProductResponse> findAllSubProductLastViews(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize) {
        return subProductService.lastViews(page, pageSize);
    }

    @GetMapping("/get_all/categories")
    @Operation(summary = "Find all categories", description = "This endpoints the find all categories. ;)")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/get_all/{id}/brands_and_sub_categories")
    @Operation(summary = "Find all brands", description = "This endpoints the find all brands. ;)")
    @PreAuthorize("hasAuthority('ADMIN')")
    public BrandAndSubCategoryResponse getAllBrandsAndSubCategoriesByCategoryId(@PathVariable("id") Long categoryId) {
        return brandService.getAllBrands(categoryId);
    }

}
