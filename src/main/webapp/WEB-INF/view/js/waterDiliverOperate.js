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
        url: "../water/updatePassword",
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
        url: "../water/updatePhone",
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
 * 用于role为2的工作人员进行查找订水订单
 * @param state要查找的订单的状态
 * @param pageNum根据页码查找相关的订单
 */
function  showWater(state,pageNum) {
    var pageSize=10;
    var url="../water/list";
    if(state==2||state==3){
        var adminId=getCookie("waterMan");
        url=url+"?adminId="+adminId;
    }else if(state==1){
        var adminZone=getCookie("waterZone");
        url=url+"?zone="+adminZone;
    }
    $.ajax({
        type:"get",
        url:url,
        data: {'pageNum': pageNum,'waterState':state,'pageSize':pageSize},
        dataType : "json",
        success : function(data, textStatus){
            var list = eval(data).list;
            var total= eval(data).total;
            $("#tableHead").empty();///清除原来的内容,为换页准备
            $("#tableBody").empty();///清除原来的内容,为换页准备
            $("#waterOrderpageNav").empty();///清除原来的内容,为换页准备
            if(total==0){
                $("#main").append("<br/><p>还没有订单喔!</p>");
            }else{
                ///搭建表格
                if(state==1){///根据要查找订单的不同进行不同的操作,这里区分表头
                    $("#tableHead").append("<tr> <th>桶数</th><th>下单时间</th><th>配送地点</th><th>操作</th></tr> ");
                }else if(state==2){
                    $("#tableHead").append("<tr> <th>桶数</th><th>下单时间</th><th>配送地点</th><th>操作</th></tr> ");
                }else if(state==3){
                    $("#tableHead").append("<tr> <th>桶数</th><th>下单时间</th><th>配送地点</th> ");
                }else if(state==4){
                    $("#tableHead").append("<tr> <th>桶数</th><th>下单时间</th><th>配送地点</th> ");
                }
                var totalWater=0;
                for(var i in list){
                    var waterId=list[i].waterId;
                    console.log(waterId);
                    var waterNum=list[i].waterNum;
                    totalWater=totalWater+waterNum;
                    var userId=list[i].userId;
                    var waterTime = list[i].waterTime;
                    waterTime=waterTime.substring(0,16);
                    var waterId=list[i].waterId;
                    var operate="";
                    if(state==1){
                        var clickFunc= "onclick=takeWater("+"'"+waterId+"',"+pageNum+");";
                        operate="<td><input type='button'class='btn btn-default' "+clickFunc+ " value='接单'/></td>";

                    }else if (state==2){
                        var clickFunc="onclick=deliveryWater("+"'"+waterId+"',"+pageNum+");";
                        operate="<td><input type='button' class='btn btn-default'"+clickFunc+  "value='配送'></td>";
                    }else if(state==3){
                        operate="";
                    }else if(state==4){
                        operate="";
                    }
                    $("#tableBody").append("<tr><td>"+waterNum+"</td> <td>"+waterTime+"</td><td>"+userId+"</td>"+operate+"</tr>");
                }
                if(state==2||state==3){
                    $("#tableBody").append("共计"+totalWater+"桶水");
                }
                showPage(eval(data).pages,eval(data).pageNum,total,state);///底部页码栏,2 表示为订水单
            }
        },
        error : function(xhr, status, errMsg) {
            alert("系统出现异常!请稍后再试");
        }
    });
}



/**
 * 用于订水订单显示时分页
 * @param pages 表示总页数
 * @param pageNum 表示当前页面
 * @param total表示记录总数
 * @param state表示订单所属状态,为按钮触发时调用查找订单函数所传入state
 */
function showPage(pages,pageNum,total,state){
    var pageNav;
    var functionName;
    functionName="showWater";
    pageNav="#waterOrderpageNav";
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
 * 用于进行订水接单操作
 * @param waterId 唯一标识一个订水订单
 * @param role 用于接单成功之后对对页面进行刷新
 * @param state
 * @param pageNum
 */
function takeWater(waterId,pageNum) {
    $.ajax({
        type: "get",
        url: "../water/takeWater",
        dataType: "json",
        data: {'waterId':waterId},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            if(msg==2){
                alert("接单失败!");
            }else if(msg==1){///接单成功后对订单列表进行刷新操作
                showWater(1,pageNum);//1表示订单状态为1
            }
        },
        error : function(xhr, state, errMsg) {
            alert("系统异常,请稍后再试!");
        }
    });
}




/***
 * 用于订水订单确认去配送
 * @param waterId 唯一标识一个订水订单
 */
function deliveryWater(waterId,pageNum) {
    $.ajax({
        type: "get",
        url: "../water/delivery",
        dataType: "json",
        data: {'waterId':waterId},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            if(msg==2){
                alert("确认失败!");
            }else if(msg==1){////确认订单成功,对页面的订单列表进行刷新
                showWater(2,pageNum);//2表示订单状态为2
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
