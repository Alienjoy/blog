package cn.com.njust.mapper;

import cn.com.njust.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname cn.com.njust.mapper
 * @Date 2022/2/11 8:13 下午
 * @Created by Zhang Shuheng
 * @Description
 */
@Mapper
public interface CategoryMapper {
    /**
     * @param category 添加的类别
     * @return 影响的函数
     * @Description 增
     */
    int insertCategory(Category category);

    /**
     * @param id 删除的类别的id
     * @return 影响的行数
     * @Description 删除
     */
    int deleteCategory(Integer id);

    /**
     * @param category 需要修改的分类
     * @return 影响行数
     * @Description 修改分类
     */
    int updateCategory(Category category);

    /**
     * 获取所有的分类
     *
     * @return 分类的列表
     */
    List<Category> getCategory();

    /**
     * 获取分类的总数量
     *
     * @return 返回分类的数量
     */
    int getCategoryNums();

    /**
     * @param id
     * @return 查询到的分类
     * 根据id查询
     */
    Category getCategoryById(Integer categoryId);

    /**
     * @param name
     * @return 查到的分类
     * 根据名字查找分类
     */
    Category getCategoryByName(String name);

    /**
     * 根据id获取子分类
     *
     * @param id
     * @return 获取的到子分类的集合
     */
    List<Category> findChildCategory(@Param(value = "id") Integer id);
}