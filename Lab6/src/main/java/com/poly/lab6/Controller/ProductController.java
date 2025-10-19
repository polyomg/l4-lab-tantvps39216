package com.poly.Lab6.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.poly.Lab6.Entity.Product;
import com.poly.Lab6.Entity.Category;
import com.poly.Lab6.Repository.ProductRepository;
import com.poly.Lab6.Repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    // Lấy danh sách sản phẩm với phân trang và sắp xếp
    @GetMapping
    public String getAllProducts(Model model,
                                 @RequestParam("page") Optional<Integer> page,
                                 @RequestParam("size") Optional<Integer> size,
                                 @RequestParam("sort") Optional<String> sortField) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10); // Mặc định 5 sản phẩm mỗi trang
        String sortBy = sortField.orElse("price"); // Mặc định sắp xếp theo giá

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by(Sort.Direction.DESC, sortBy));
        Page<Product> productPage = productRepo.findAll(pageable);
        List<Category> categories = categoryRepo.findAll();

        model.addAttribute("products", productPage);
        model.addAttribute("categories", categories);
        model.addAttribute("product", new Product());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("sortField", sortBy);

        return "ProductsCRUD.html";
    }

    // Lưu sản phẩm
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productRepo.save(product);
        return "redirect:/products";
    }

    // Chỉnh sửa sản phẩm
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        Product product = productRepo.findById(id).orElse(null);
        List<Category> categories = categoryRepo.findAll();

        model.addAttribute("product", product);
        model.addAttribute("categories", categories);

        return "ProductsCRUD.html";
    }

    // Xóa sản phẩm
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productRepo.deleteById(id);
        return "redirect:/products";
    }

    // Sắp xếp sản phẩm theo cột được chọn
    @GetMapping("/sort")
    public String sortProducts(Model model, @RequestParam("field") Optional<String> field) {
        String sortBy = field.orElse("price"); // Mặc định sắp xếp theo giá
        List<Product> sortedProducts = productRepo.findAll(Sort.by(Sort.Direction.DESC, sortBy));

        model.addAttribute("products", sortedProducts);
        model.addAttribute("sortField", sortBy.toUpperCase());

        return "ProductsCRUD.html";
    }
}
