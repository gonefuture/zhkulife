/**
 * Created by Administrator on 2017/8/29 0029.
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
        if (c.indexOf(name)==0) return c.substring(name.length,c.length);
    }
    return "";
}




///送水人员修改密码
function updatePassword(){
    var password= $("input[name='password']").val();
    $.ajax({
        type: "get",
        url: "../repair/updatePassword",
        dataType: "json",
        data: {'password': password},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            if(msg==1){
                alert("修改成功!");
            }else if(msg==2){
                alert("修改密码失败!请稍后再试!");
            }
        },
        error : function(xhr, status, errMsg) {
            alert("修改密码失败,请稍后再试!");
        }
    });
}

///送水人员修改用户手机
function updatePhone(){
    var phone= $("input[name='phone']").val();
    $.ajax({
        type: "get",
        url: "../repair/updatePhone",
        dataType: "json",
        data: {'phone': phone},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            if(msg==1){
                alert("修改成功!");
            }else if(msg==2){
                alert("修改手机失败!请稍后再试!");
            }
        },
        error : function(xhr, status, errMsg) {
            alert("修改手机失败,请稍后再试!");
        }
    });
}

/**
 * 用于维修人员加载按照订单状态加载订单
 * @param state要加载的订单的状态类型
 * @param pageNum
 */
function showRepair(state,pageNum) {
    console.log(state);
    var pageSize=10;
    var url="../repair/list";
    if(state==2||state==3){
        var adminId=getCookie("repairMan");
        url=url+"?adminId="+adminId;
    }else if(state==1){
        var adminZone=getCookie("repairZone");
        url=url+"?zone="+adminZone;
    }
    $.ajax({
        type:"get",
        url:url,
        data: {'pageNum': pageNum,'repairState':state,'pageSize':pageSize},
        dataType : "json",
        success : function(data, textStatus){
            var list = eval(data).list;
            var total= eval(data).total;
            $("#repairOrderList").empty();///清除原来的内容
            $("#repairOrderpageNav").empty();///清除原来的内容
            if(total==0){
                $("#main").append("<br/><p>还没有订单喔!</p>");
            }else{
                ///显示维修单,搭建内容区域
                for(var i in list){
                    var repairId=list[i].repairId;
                    var repairTime=list[i].repairTime;
                    repairTime=repairTime.substring(0,16);
                    var repaidDetail = list[i].repairDetial;
                    console.log(repaidDetail);
                    var userId=list[i].userId;
                    var operate="";
                    if(state==1){
                        var clickFunc= "onclick=takeRepair("+"'"+repairId+"',"+pageNum+");";
                        operate="<br/><button type='button' class='btn btn-info'"+clickFunc+">接单</button>";
                    }else if(state==2){
                        var clickFunc= "onclick=finishRepair("+"'"+repairId+"',"+pageNum+");";
                        operate="<br/><button type='button' class='btn btn-info'"+clickFunc+">确认完成</button>";
                    }else if(state==3){
                        operate="";
                    }
                    $('#repairOrderList').append("订单号:"+repairId+"<br/>下单时间:"+repairTime+"<br/>维修地点:"+userId+"<br/>订单详情:"+repaidDetail+operate+"<hr/>");
                }
                showPage(eval(data).pages,eval(data).pageNum,total,state);///底部页码栏,3 表示为维修单
            }
        },
        error : function(xhr, status, errMsg) {
            alert("系统出现异常!请稍后再试");
        }
    });
}




/**
 * 用于订单显示时分页
 * @param pages 表示总页数
 * @param pageNum 表示当前页面
 * @param total表示记录总数
 * @param state表示订单所属状态,为按钮触发时调用查找订单函数所传入state
 */
function showPage(pages,pageNum,total,state){
    var pageNav;
    var functionName;
    functionName="showRepair";
    pageNav="#repairOrderpageNav";
    var begin;
    var end;
    var next=pageNum+1;
    var pre=pageNum-1;
    if(pages<=10){
        begin=1;
        end=pages;
    }else{
        begin=pageNum-4;
        end=pageNum+5;
        if(begin<1){
            begin=1;
            end=10;
        }
        if(end>pages){
            begin=pages-9;
            end=pages;
        }
    }
    if(pageNum!=1){
        $(pageNav).append("<button type='button' class='btn btn-info' onclick='"+functionName+"("+state+","+pre+");'>上一页</button>");
    }
    for(var i=begin;i<=end;++i){
        if(i===pageNum){
            $(pageNav).append("<button type='button' class='btn btn-success' ><b>"+i+"</b></button>");
        }else{
            $(pageNav).append("<button type='button' class='btn btn-info' onclick='"+functionName+"("+state+","+i+");'>"+i+"</button>");
        }
    }
    if(pageNum!=pages){
        $(pageNav).append("<button type='button' class='btn btn-info' onclick='"+functionName+"("+state+","+next+");'>下一页</button>");
    }
    $(pageNav).append("<br/>共"+total+"条订单");
}



/**
 * 用于维修的接单操作
 * @param repairId 唯一标识一个维修订单
 */
function takeRepair(repairId,pageNum) {
    $.ajax({
        type: "get",
        url: "../repair/takeRepair",
        dataType: "json",
        data: {'repairId':repairId},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            if(msg==2){
                alert("接单失败!");
            }else if(msg==1){///接单成功后对订单列表进行刷新操作
                showRepair(1,pageNum);//1表示订单状态为1
            }
        },
        error : function(xhr, status, errMsg) {
            alert("系统异常,请稍后再试!");
        }
    });
}



/**
 * 用于维修订单的确认操作
 * @param waterId 唯一标识一个维修订单
 */
function finishRepair(repairId,pageNum) {
    console.log(repairId);
    $.ajax({
        type: "get",
        url: "../repair/finishRepair",
        dataType: "json",
        data: {'repairId':repairId},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            if(msg==2){
                alert("确认订单失败!");
            }else if(msg==1){////确认订单成功,对页面的订单列表进行刷新
                showRepair(2,pageNum);//2表示订单状态为2
            }
        },
        error : function(xhr, status, errMsg) {
            alert("系统异常,请稍后再试!");
        }
    });
}



/**
 * 用于获取url上参数的值
 * @param name url上的参数名
 * @returns {null} 返回参数的值
 */
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
