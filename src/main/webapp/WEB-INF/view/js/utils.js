/**
 * Created by Administrator on 2017/10/23 0023.
 */
/**
 * 根据cookie名获取cookie的值
 * @param cname cookie名
 * @returns {*} cookie值
 */
function getCookie(cname)
{
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++)
    {
        var c = ca[i].trim();
        if (c.indexOf(name)==0) {
            return c.substring(name.length,c.length);
        }
    }
    return "";
}

/**
 * 用于创建cookie
 * @param cname cookie名
 * @param cvalue cookie值
 * @param exdays 保存天数
 */
function setCookie(cname,cvalue,exdays)
{
    var d = new Date();
    d.setTime(d.getTime()+(exdays*24*60*60*1000));
    var expires = "expires="+d.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

/**
 * 用于检测当前按钮是否被超频点击(约定:当前后两次点击的时间间隔小于3秒钟则判定为超频点击)
 * @param      btnName 被点击的按钮的名称
 * @returns   1表示为超频点击,0表示非超频点击
 */
function isFrequent(btnName) {
    var lastPress=getCookie(btnName);
    if(lastPress==""){///如果还未存在该cookie值,则表明此次点击为非超频点击
        setCookie(btnName, new Date(),7);////刷新该cookie的值
        return 0;
    }
    var nowPress=new Date();
    lastPress=new Date(lastPress);
    var gap=(nowPress.getTime()-lastPress.getTime())/1000;///获取此次点击和上次点击的时间间隔(单位:秒)
    if(gap<3) {///////前后两次点击时间小于三秒钟,则返回1,表示为超频点击
        setCookie(btnName, new Date(),7);////刷新该cookie的值
        return 1;
    }else{
        setCookie(btnName, new Date(),7);////刷新该cookie的值
        return 0;
    }
}


