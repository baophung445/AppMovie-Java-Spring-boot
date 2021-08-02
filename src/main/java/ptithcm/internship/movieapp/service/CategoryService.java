package ptithcm.internship.movieapp.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.internship.movieapp.entrity.Category;
import ptithcm.internship.movieapp.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);
	
	public Optional<Category> findCategoryById(String cid) {
		LOGGER.info("findCategoryById: " + "cid=" + cid);
		 
		return categoryRepository.findById(cid);
	}
	
	public Category findByCategoryId(String cid) {
		LOGGER.info("findByCategoryId: " + "cid=" + cid);
		
		return categoryRepository.findByCategoryId(cid);
	}
	
	public List<Category> findAllCategory(){
		LOGGER.info("findAllCategory: ");
		
		return categoryRepository.findAll();
	}
	
	public List<Category> findAllCategoryByFid(int fid){
		LOGGER.info("findAllCategoryByFid: " + "fid=" + fid);
		
		return categoryRepository.findCategoriesByFid(fid);
	}
	
	public Category save(Category category) {
		LOGGER.info("save: " + "category=" + category);
		
		return categoryRepository.save(category);
	}
	
	public void delete(Category category) {
		LOGGER.info("delete: " + "category=" + category);
		
		categoryRepository.delete(category);
	}
}
