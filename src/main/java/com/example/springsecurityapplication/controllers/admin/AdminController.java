package com.example.springsecurityapplication.controllers.admin;
import com.example.springsecurityapplication.models.Image;
import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.CategoryRepository;
/*import com.example.springsecurityapplication.repositories.StatusRepozitory;*/
import com.example.springsecurityapplication.services.ProductService;
import com.example.springsecurityapplication.services.OrderServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
public class AdminController {

    @Value("${upload.path}")
    private String uploadPath;

    private final ProductService productService;

    private final OrderServise orderServise;

  /*  private final StatusRepozitory statusRepository;*/
    private final CategoryRepository categoryRepository;
    @Autowired
    public AdminController(ProductService productService, OrderServise orderServise, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.orderServise = orderServise;
        this.categoryRepository = categoryRepository;
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("")
    public String admin(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return "admin/admin";
    }


    // Метод по отображению страницы с возможностью добавления товаров
    @GetMapping("/product/add")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("category", categoryRepository.findAll());
        return "product/addProduct";
    }

    // Метод по добавлению продукта в БД через сервис->репозиторий
    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
                             @RequestParam("file_one") MultipartFile file_one,
                             @RequestParam("file_two")MultipartFile file_two,
                             @RequestParam("file_three")MultipartFile file_three,
                             @RequestParam("file_four")MultipartFile file_four,
                             @RequestParam("file_five") MultipartFile file_five
    ) throws IOException {
        if(bindingResult.hasErrors())
        {
            return "product/addProduct";
        }

        if(file_one != null)
        {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_one.getOriginalFilename();
            file_one.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if(file_two != null)
        {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_two.getOriginalFilename();
            file_two.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if(file_three != null)
        {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_three.getOriginalFilename();
            file_three.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if(file_four != null)
        {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_four.getOriginalFilename();
            file_four.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if(file_five != null)
        {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_five.getOriginalFilename();
            file_five.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }
        productService.saveProduct(product);
        return "redirect:/admin";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return "redirect:/admin";
    }

    // Метод по отображению страницы с возможностью редактирования товаров
    @GetMapping("/product/edit/{id}")
    public String editProduct(Model model, @PathVariable("id") int id){
        model.addAttribute("product", productService.getProductId(id));
        model.addAttribute("category", categoryRepository.findAll());
        return "product/editProduct";
    }

    // Метод по редактированию товара
    @PostMapping("/product/edit/{id}")
    public String editProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors())
        {
            return "product/editProduct";
        }
        productService.updateProduct(id, product);
        return "redirect:/admin";
    }
///////////////работа с заказами
// Метод по отображению страницы с заказами
@GetMapping("/allOrders")
public String allOrders(Model model){
    model.addAttribute("orderall", orderServise.getAllOrders());
    return "product/allOrders";
}

    @GetMapping("/order/delete/{id}")
    public String deleteOrder(@PathVariable("id") int id){
        orderServise.deleteOrder(id);
        return "redirect:/admin/allOrders";
    }

    // Метод по отображению страницы с возможностью редактирования товаров
    @GetMapping("/order/edit/{id}")
    public String editOrder(Model model, @PathVariable("id") int id){
        model.addAttribute("order", orderServise.getOrderId(id));
      /*  model.addAttribute("status", statusRepository.findAll());*/
        return "product/editOrder";
    }

    // Метод по редактированию товара
    @PostMapping("/order/edit/{id}")
    public String editProduct(@ModelAttribute("order") @Valid Order order, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors())
        {
            return "product/editOrder";
        }
        orderServise.updateOrder(id, order);
        return "redirect:/admin/allOrders";
    }

}

