package cn.com.njust.service.impl;

import cn.com.njust.entity.Category;
import cn.com.njust.mapper.CategoryMapper;
import cn.com.njust.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname cn.com.njust.service.impl
 * @Date 2022/2/10 3:34 下午
 * @Created by Zhang Shuheng
 * @Description service层接口的实现类
 */

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    /**
     * 添加类别
     * @param category 添加的类别
     * @return
     */
    @Override
    public int insertCategory(Category category) {
        int res=0;
        try {
            res=categoryMapper.insertCategory(category);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("创建分类失败, category:{}, cause:{}", category, e);
        }
        return res;
    }

    /**
     * 根据ID删除类别
     * @param id 删除的类别的id
     * @return
     */
    @Override
    public int deleteCategory(Integer id) {
        int res=0;
        try {
            res=categoryMapper.deleteCategory(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除分类失败，id:{}, cause:{}", id, e);
        }
        return res;
    }

    /**
     * 修改类别
     * @param category 需要修改的分类
     * @return
     */
    @Override
    public int updateCategory(Category category) {
        return 0;
    }

    @Override
    public List<Category> getCategory() {
        List<Category> category=null;
        try {
            category = categoryMapper.getCategory();
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询分类失败，失败原因：{}",e);
        }
        return category;
    }

    @Override
    public int getCategoryNums() {
        return 0;
    }

    @Override
    public Category getCategoryById(Integer id) {
        try {
            Category category = categoryMapper.getCategoryById(id);
            return  category;
        }catch (Exception e){
            e.printStackTrace();
            log.error("service层根据id获取分类失败，失败原因：{}",e);
            return null;
        }


    }

    @Override
    public Category getCategoryByName(String name) {
        return null;
    }

    @Override
    public List<Category> findChildCategory(Integer id) {
        return null;
    }
}
