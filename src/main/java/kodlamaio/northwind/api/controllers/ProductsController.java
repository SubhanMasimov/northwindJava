package kodlamaio.northwind.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

@CrossOrigin
@RestController
@RequestMapping("/api/products/")
public class ProductsController {

	private ProductService productService;

	@Autowired
	public ProductsController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("getall")
	public DataResult<List<Product>> getAll() {
		return this.productService.getAll();
	}

	@PostMapping("add")
	public Result add(@RequestBody Product product) {
		return productService.add(product);
	}

	@GetMapping("getByProductName")
	public DataResult<Product> getByProductName(@RequestParam String productName) {
		return productService.getByProductName(productName);
	}

	@GetMapping("getByProductNameAndCategoryId")
	public DataResult<Product> getByProductNameAndCategoryId(@RequestParam("productName") String productName,
			@RequestParam("categoryId") int categeroyId) {

		return productService.getByProductNameAndCategoryId(productName, categeroyId);
	}

	@GetMapping("getByProductNameContains")
	DataResult<List<Product>> getByProductNameContains(@RequestParam String productName) {

		return productService.getByProductNameContains(productName);
	}

	@GetMapping("getAllByPage")
	public DataResult<List<Product>> getAll(@RequestParam int pageNo, @RequestParam int pageSize) {

		return productService.getAll(pageNo, pageSize);
	}

	@GetMapping("getAllSortedDesc")
	public DataResult<List<Product>> getAllSorted() {

		return productService.getAllSorted();
	}

	@GetMapping("getProductWithCategoryDetails")
	public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
		return productService.getProductWithCategoryDetails();
	}
}
