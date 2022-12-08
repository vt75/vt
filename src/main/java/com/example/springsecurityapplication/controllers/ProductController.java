package com.example.springsecurityapplication.controllers;
import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.ProductRepository;
import com.example.springsecurityapplication.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    @Autowired
    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping("")
    public String getAllProduct(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "/product/product";
    }

    @GetMapping("/info/{id}")
    public String infoUser(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productService.getProductId(id));
        return "product/infoProduct";
    }

    @PostMapping("/search")
    public String productSearch(@RequestParam("search") String search, @RequestParam("ot") String ot, @RequestParam("do") String Do, @RequestParam(value = "price", required = false, defaultValue = "")String price, @RequestParam(value = "contact", required = false, defaultValue = "")String contact, Model model){
        if(!ot.isEmpty() & !Do.isEmpty()){
            if(!price.isEmpty()){
                if(price.equals("sorted_by_ascending_price")){
                    if(!contact.isEmpty())
                    {
                        if(contact.equals("toys")){
                            model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 1));
                        } else if(contact.equals("yelki")){
                            model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 3));
                        }else if(contact.equals("other")){
                            model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 4));
                        }
                    }
                }
                else if (price.equals("sorted_by_descending_price")){
                    if(!contact.isEmpty())
                    {
                        if(contact.equals("toys")){
                            model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 1));
                        } else if(contact.equals("yelki")){
                            model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 3));
                        }else if(contact.equals("other")){
                            model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 4));
                        }
                    }
                }
            } else {
                model.addAttribute("search_product", productRepository.findByTitleAndPriceGreaterThanEqualAndPriceLessThanEqual(search, Float.parseFloat(ot), Float.parseFloat(Do)));

            }
        }
        else {
            model.addAttribute("search_product", productRepository.findByTitleContainingIgnoreCase(search));
        }

        model.addAttribute("value_search", search);
        model.addAttribute("value_price_ot", ot);
        model.addAttribute("value_price_do", Do);
        model.addAttribute("products", productService.getAllProduct());
        return "/product/product";

    }
    @PostMapping("/search_u")
    public String productSearchUser(@RequestParam("search") String search, @RequestParam("ot") String ot, @RequestParam("do") String Do, @RequestParam(value = "price", required = false, defaultValue = "")String price, @RequestParam(value = "contact", required = false, defaultValue = "")String contact, Model model){
        if(!ot.isEmpty() & !Do.isEmpty()){
            if(!price.isEmpty()){
                if(price.equals("sorted_by_ascending_price")){
                    if(!contact.isEmpty())
                    {
                        if(contact.equals("toys")){
                            model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 1));
                        } else if(contact.equals("yelki")){
                            model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 3));
                        }else if(contact.equals("other")){
                            model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 4));
                        }
                    }
                }
                else if (price.equals("sorted_by_descending_price")){
                    if(!contact.isEmpty())
                    {
                        if(contact.equals("toys")){
                            model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 1));
                        } else if(contact.equals("yelki")){
                            model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 3));
                        }else if(contact.equals("other")){
                            model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 4));
                        }
                    }
                }
            } else {
                model.addAttribute("search_product", productRepository.findByTitleAndPriceGreaterThanEqualAndPriceLessThanEqual(search, Float.parseFloat(ot), Float.parseFloat(Do)));

            }
        }
        else {
            model.addAttribute("search_product", productRepository.findByTitleContainingIgnoreCase(search));
        }

        model.addAttribute("value_search", search);
        model.addAttribute("value_price_ot", ot);
        model.addAttribute("value_price_do", Do);
        model.addAttribute("products", productService.getAllProduct());
        return "/user/index";

    }

    @PostMapping("/search_contact")
    public String productSearchContact(
                                @RequestParam(value = "contact", required = false, defaultValue = "")String contact, Model model){
                    if(!contact.isEmpty())
                    {
                        if(contact.equals("toys")){
                            model.addAttribute("search_product", productRepository.findByCategory(1));
                        } else if(contact.equals("toys1")){
                            model.addAttribute("search_product", productRepository.findByCategory(4));
                        } else if(contact.equals("yelki")){
                            model.addAttribute("search_product", productRepository.findByCategory(3));
                        }else if(contact.equals("other")){
                            model.addAttribute("search_product", productRepository.findByCategory(2));
                        }
                    }



        model.addAttribute("products", productService.getAllProduct());
        return "/product/product";

    }
    @PostMapping("/search_contact_u")
    public String productSearchContactUser(
            @RequestParam(value = "contact", required = false, defaultValue = "")String contact, Model model){
        if(!contact.isEmpty())
        {
            if(contact.equals("toys")){
                model.addAttribute("search_product", productRepository.findByCategory(1));
            } else if(contact.equals("toys1")){
                model.addAttribute("search_product", productRepository.findByCategory(4));
            } else if(contact.equals("yelki")){
                model.addAttribute("search_product", productRepository.findByCategory(3));
            }else if(contact.equals("other")){
                model.addAttribute("search_product", productRepository.findByCategory(2));
            }
        }


        model.addAttribute("products", productService.getAllProduct());
        return "/user/index";

    }


}
