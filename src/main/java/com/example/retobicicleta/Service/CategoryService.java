package com.example.retobicicleta.Service;

import com.example.retobicicleta.Model.Admin;
import com.example.retobicicleta.Model.Bike;
import com.example.retobicicleta.Model.Category;
import com.example.retobicicleta.Repository.AdminRepository;
import com.example.retobicicleta.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> getAll(){
        return categoryRepository.getAll();

    }
    public Optional<Category> getCategory(int id){
        return  categoryRepository.getCategory(id);

    }
    public Category save(Category category){
        if(category.getId() == null) {
            return categoryRepository.save(category);
        }else{
            Optional<Category> categoryEncontrado = categoryRepository.getCategory(category.getId());
            if(categoryEncontrado.isEmpty()){
                return categoryRepository.save(category);
            }else{
                return category;
            }
        }
    }
    public Category update(Category category){
        if(category.getId() != null){
            Optional<Category> categoryEncontrado = categoryRepository.getCategory((category.getId()));
            if (!categoryEncontrado.isEmpty()){
                if(category.getDescription() !=null){
                    categoryEncontrado.get().setDescription(category.getDescription());

                }
                if(category.getName() !=null ){
                    categoryEncontrado.get().setName(category.getName());
                }
                return categoryRepository.save(categoryEncontrado.get());
            }

        }
        return category;

    }
    public boolean deleteCategory(int id){
        Boolean resultado = getCategory(id).map(categoryporEliminar ->{
            categoryRepository.delete(categoryporEliminar);
            return true;
        }).orElse(false);
        return resultado;
    }
    public Optional<Category> getCategoryId(int id){
        return categoryRepository.getCategory(id);}

}
