package com.emall.service;

import com.emall.commom.ServerResponse;
import com.emall.pojo.Category;

import java.util.List;

public interface ICategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse setCategoryName(String categoryName, Integer categoryId);

    ServerResponse<List<Category>> selectChildrenParallelCategoryByParentId(Integer categoryId);

    ServerResponse<List<Integer>> selectChildrenCategoryById(Integer categoryId);
}
