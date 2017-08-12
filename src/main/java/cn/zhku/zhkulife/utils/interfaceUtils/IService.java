package cn.zhku.zhkulife.utils.interfaceUtils;

import java.util.List;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/8/1 21:38.
 * @E-mail gonefuture@qq.com
 */
public interface IService<T> {
    /**
     * 对某个实体对象（相对应的表）添加操作
     *
     * @param entity
     *            继承BaseEntity子类实体
     * @return 影响行数
     * @throws Exception
     *             抛出参数错误、SQL操作等异常
     */
    public int add(T entity) throws Exception;

    /**
     * 对某个实体对象（相对应的表）修改操作
     *
     * @param entity
     *            继承BaseEntity子类实体
     * @return 影响行数
     * @throws Exception
     *             抛出参数错误、SQL操作等异常
     */
    public int update(T entity) throws Exception;

    /**
     * 对某个实体对象（相对应的表）删除操作
     *
     * @param entity
     *            继承BaseEntity子类实体
     * @return 影响行数
     * @throws Exception
     *             抛出参数错误、SQL操作等异常
     */
    public int delete(T entity) throws Exception;

    /**
     * 通过ID获取某个对象实体
     *
     * @param id
     *            主键ID
     * @return
     * @throws Exception
     */
    public T get(String id) throws Exception;

    /**
     * 通过设置实体某些字获取个实体对象列表（相对应的表）
     * @return 影响行数
     * @throws Exception
     *             抛出参数错误、SQL操作等异常
     */
    public List<T> getList(T entity) throws Exception;

    /**
     * 通过查询条件获取某个实体对象（相对应的表）列表操作
     *
     * @param entity
     *            继承BaseEntity子类实体
     * @return 影响行数
     * @throws Exception
     *             抛出参数错误、SQL操作等异常
     */
    public List<T> findAll(T entity) throws Exception;
}
