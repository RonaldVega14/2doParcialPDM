package com.vega.gamenews.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.vega.gamenews.Database.Entities.CategoryEntity;
import com.vega.gamenews.Database.Repositories.CategoryRepository;

import java.util.List;

public class CategoryVModel extends AndroidViewModel {

    CategoryRepository categoryRepository;

    public CategoryVModel(@NonNull Application application) {
        super(application);
        categoryRepository = new CategoryRepository(application);
    }

    public LiveData<List<CategoryEntity>> getAllCategories(){
        return categoryRepository.getAllCategories();
    }

    public void insertCategory(CategoryEntity categoryEntity){

        categoryRepository.insert(categoryEntity);

    }
}
