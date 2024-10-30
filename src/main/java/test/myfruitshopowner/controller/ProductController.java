package test.myfruitshopowner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import test.myfruitshopowner.dto.FilterDto;
import test.myfruitshopowner.dto.product.ProductResponseDto;
import test.myfruitshopowner.service.ProductService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    @GetMapping("/get-all")
    public ResponseEntity<Flux<ProductResponseDto>> getAllProducts(@ModelAttribute FilterDto filterDto) {
        return ResponseEntity.ok(productService.getAll(filterDto));
    }
    @GetMapping("/get-total-for-pagination")
    public ResponseEntity<Mono<Long>> getTotalForPagination(@RequestParam int pageSize, @RequestParam String name) {
        return ResponseEntity.ok(productService.getTotalElementForPagination(pageSize, name));
    }
}