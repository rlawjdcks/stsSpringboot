package com.sky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sky.model.Product;
import com.sky.repository.ProductMapper;
import com.sky.service.ProductService;

@Controller
public class MyController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("userName","길동");
		model.addAttribute("aaa","ㄴ단");
		model.addAttribute("product",productService.getProductById(3L));
		return "main";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("list",productService.getAllProducts());
		return "list";
	}
	
	@PostMapping("/save")
    public String saveProduct(@RequestParam Long prodId,
                              @RequestParam String prodName,
                              @RequestParam Integer prodPrice) {
        // 폼에서 받은 데이터로 Product 객체 생성
        Product product = new Product();
        product.setProdId(prodId);
        product.setProdName(prodName);
        product.setProdPrice(prodPrice);

        // ProductService를 사용하여 제품 등록
        productService.addProduct(product);

        // 등록 후 목록 페이지로 이동
        return "redirect:/list";
    }
	
	@GetMapping("/save")
	public String showSaveForm() {
	    return "save"; // 게시글 등록 폼을 보여줄 뷰 이름을 반환합니다.
	}
}
