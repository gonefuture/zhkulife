/**
 * Created by Administrator on 2017/8/8 0008.
 * 仅仅用来测试仅仅用来测试仅仅用来测试仅仅用来测试仅仅用来测试仅仅用来测试
 * 仅仅用来测试仅仅用来测试仅仅用来测试仅仅用来测试仅仅用来测试仅仅用来测试
 *
 * 仅仅用来测试仅仅用来测试仅仅用来测试仅仅用来测试仅仅用来测试仅仅用来测试
 * 仅仅用来测试仅仅用来测试仅仅用来测试仅仅用来测试仅仅用来测试仅仅用来测试
 */
function addParameter() {
    var userId= getUrlParam("userId");
    var zone=getUrlParam("zone");
    $(".home a").attr("href",($(".home a").attr("href")+"?userId="+userId+"&zone="+zone));
    $("#order .a a").attr("href",($("#order .a a").attr("href")+"&userId="+userId+"&zone="+zone));
    $("#order .b a").attr("href",($("#order .b a").attr("href")+"&userId="+userId+"&zone="+zone));
    $("#order .c a").attr("href",($("#order .c a").attr("href")+"&userId="+userId+"&zone="+zone));
    $("#modify a").attr("href",($("#modify a").attr("href")+"&userId="+userId+"&zone="+zone));
}


function getUrlParam(name){
    //构造一个含有目标参数的正则表达式对象
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    //匹配目标参数
    var r = window.location.search.substr(1).match(reg);
    //返回参数值
    if (r!=null) {
        return unescape(r[2]);
    }
    return null;
}