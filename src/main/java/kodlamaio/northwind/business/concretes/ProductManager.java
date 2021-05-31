package kodlamaio.northwind.business.concretes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.results.SuccessResult;
import kodlamaio.northwind.dataAccess.abstracts.ProductDao;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

@Service
public class ProductManager implements ProductService {

	private ProductDao productDao;

	@Autowired
	public ProductManager(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public DataResult<List<Product>> getAll() {

		return new SuccessDataResult<List<Product>>(productDao.findAll(), "Məhsullar listələndi");
	}

	@Override
	public Result add(Product product) {

		productDao.save(product);
		return new SuccessResult("Məhsul əlavə edildi");
	}

	@Override
	public DataResult<Product> getByProductName(String productName) {

		return new SuccessDataResult<Product>(productDao.getByProductName(productName), "Məhsullar listələndi");
	}

	@Override
	public DataResult<Product> getByProductNameAndCategoryId(String productName, int categeroyId) {

		return new SuccessDataResult<Product>(
				productDao.getByProductNameAndCategory_CategoryId(productName, categeroyId), "Məhsullar listələndi");
	}

	@Override
	public DataResult<List<Product>> getByProductNameOrCategoryId(String productName, int categeroyId) {

		return new SuccessDataResult<List<Product>>(
				productDao.getByProductNameOrCategory_CategoryId(productName, categeroyId), "Məhsullar listələndi");
	}

	@Override
	public DataResult<List<Product>> getByCategoryIdIn(List<Integer> categories) {
		return new SuccessDataResult<List<Product>>(productDao.getByCategoryIn(categories), "Məhsullar listələndi");
	}

	@Override
	public DataResult<List<Product>> getByProductNameContains(String productName) {
		return new SuccessDataResult<List<Product>>(productDao.getByProductNameContains(productName),
				"Məhsullar listələndi");
	}

	@Override
	public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
		return new SuccessDataResult<List<Product>>(productDao.getByProductNameStartsWith(productName),
				"Məhsullar listələndi");
	}

	@Override
	public DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>(productDao.getByNameAndCategory(productName, categoryId),
				"Məhsullar listələndi");
	}

	@Override
	public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		return new SuccessDataResult<List<Product>>(productDao.findAll(pageable).getContent(), "Məhsullar listələndi");
	}

	@Override
	public DataResult<List<Product>> getAllSorted() {
		Sort sort = Sort.by(Sort.Direction.DESC, "productName");

		return new SuccessDataResult<List<Product>>(productDao.findAll(sort), "Məhsullar listələndi");
	}

	@Override
	public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
		return new SuccessDataResult<List<ProductWithCategoryDto>>(productDao.getProductWithCategoryDetails(), "Dto-lar listələndi");
	}

}
