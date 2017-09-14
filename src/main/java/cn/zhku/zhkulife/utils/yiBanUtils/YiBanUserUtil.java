package cn.zhku.zhkulife.utils.yiBanUtils;

import cn.yiban.util.HTTPSimple;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/8/23 16:48.
 * @E-mail gonefuture@qq.com
 */
public class YiBanUserUtil {

    private final String YIBAN_OPEN_URL		= "https://openapi.yiban.cn/";


    private final String YIBAN_USER_VERIFYME_INFO	= "user/verify_me";


    /**
     * 已经通过授权的访问令牌
     */
    private String token;

    /**
     * 构造函数
     *
     * @param token
     */
    /**
     * 构造函数
     *
     * @param
     */
    public YiBanUserUtil(String token)
    {
        this.token = token;
    }


    public String verifyMe()
    {
        String query = YIBAN_OPEN_URL;
        query += YIBAN_USER_VERIFYME_INFO;
        query += "?access_token=";
        query += token;
        return HTTPSimple.GET(query);
    }
}
