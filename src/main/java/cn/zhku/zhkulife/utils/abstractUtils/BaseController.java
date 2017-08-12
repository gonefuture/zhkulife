package cn.zhku.zhkulife.utils.abstractUtils;

import cn.zhku.zhkulife.utils.interfaceUtils.IService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/8/1 10:13.
 * @E-mail gonefuture@qq.com
 */
public class BaseController<T extends IService> {
    @Autowired
    protected T service;
}
