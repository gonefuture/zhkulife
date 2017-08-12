package cn.zhku.zhkulife.utils.interfaceUtils;

import org.apache.ibatis.exceptions.TooManyResultsException;


import java.util.List;

/**
 * 提供基础接口
 *
 * @author 钱伟健 gonefutre
 * @date 2017/7/21 20:58.
 * @E-mail gonefuture@qq.com
 */
public interface IBaseService<T> {

    /**
     * //持久化
     * @param model
     * @return
     * @throws Exception
     */
    int save(T model)throws Exception;

    int save(List<T> models)throws Exception;//批量持久化

    int deleteById(Integer id)throws Exception;//通过主鍵刪除

    int deleteByIds(String ids)throws Exception;//批量刪除 eg：ids -> “1,2,3,4”

    int update(T model)throws Exception;//更新

    T findById(Integer id)throws Exception;//通过ID查找

    T findBy(String property, Object value) throws Exception; //通过某个成员属性查找,value需符合unique约束

    List<T> findByIds(String ids)throws Exception;//通过多个ID查找//eg：ids -> “1,2,3,4”

   // List<T> findByCondition(Condition condition)throws Exception;//根据条件查找

    List<T> findAll()throws Exception;//获取所有


}
