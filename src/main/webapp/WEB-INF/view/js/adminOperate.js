/**
 * Created by Administrator on 2017/8/10 0010.
 * 不同角色的管理员的统一登录方法
 */
function login(){
    var adminId= $("input[name='adminId']").val();
    var adminPassword= $("input[name='adminPassword']").val();
    console.log(adminId+" "+adminPassword);
    $.ajax({
        type: "post",
        url: "/zhkulife/login/admin",
        dataType: "json",
        data: {'adminId': adminId, 'adminPassword': adminPassword},
        success: function (data, textStatus) {
            var key = eval(data).key;
            var info= eval(data).info;
            var msg = eval(data).msg;
            var zone = eval(data).zone;
            if(msg==2){
                alert(info);///登录失败
            }else {
                if(key==2){
                    ////设置cookie,保存当期送水工作人员的ID,七天过期
                    setCookie("waterMan",adminId,7);
                    ///设置cookie,保存当前工作人员所处的校区,七天过期
                    setCookie("waterZone",zone,7);
                    window.location.href="water/index.html";
                }else if(key==3){
                    ////设置cookie,保存当期送水工作人员的ID,七天过期
                    setCookie("repairMan",adminId,7);
                    ///设置cookie,保存当前工作人员所处的校区,七天过期
                    setCookie("repairZone",zone,7);
                    window.location.href="repair/index.html";
                }else if(key==4){
                    ////设置cookie,保存当期送水工作人员的ID,七天过期
                    setCookie("waterAdm",adminId,7);
                    ///设置cookie,保存当前工作人员所处的校区,七天过期
                    setCookie("waterAdmZone",zone,7);
                    window.location.href="wateradm/index.html";
                }else if(key==5){
                    ////设置cookie,保存当期送水工作人员的ID,七天过期
                    setCookie("repairAdm",adminId,7);
                    ///设置cookie,保存当前工作人员所处的校区,七天过期
                    setCookie("repairAdmZone",zone,7);
                    window.location.href="repairadm/index.html";
                }else if(key==6){
                    ////设置cookie,保存当期送水工作人员的ID,七天过期
                    setCookie("officeAdm",adminId,7);
                    ///设置cookie,保存当前工作人员所处的校区,七天过期
                    setCookie("officeAdmZone",zone,7);
                    window.location.href="office/index.html";
                }

            }
        },
        error : function(xhr, status, errMsg) {
            alert("系统异常，请稍候再试!");
        }
    })
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



///修改密码
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
///修改用户手机
function updatePhone(){
    var phone= $("input[name='phone']").val();
    $.ajax({
        type: "get",
        url: "../admin/updatePhone",
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
 *
 * @param role  用于确定是查找订水订单还是维修订单
 * @param state 用于确定将要查找的订单状态
 * @param pageNum 用于确定查找订单的页码
 */
/**此方法失效!
function  showOrder(role,state,pageNum) {
    var url;
    if(role==2){
        url="../water/list?pageNum="+pageNum+"&pageSize=10?repairState="+state;
    }else if(role==3){
        url="../repair/list?pageNum="+pageNum+"&pageSize=10?waterState="+state;
    }
    $.ajax({
        type:"get",
        url:url,
        dataType : "json",
        success : function(data, textStatus){
            var list = eval(data).list;
            var total= eval(data).total;
            $("#main").empty();///清除原来的内容,为换页准备
            if(total==0){
                $("#main").append("<br/><p>还没有订单喔!</p>");
            }else{
                if(role==2){///显示订水单
                    ///搭建表格
                    $("#main").append("<table><thead id='tableHead'></thead><tbody id='tableBody'></tbody></table><div id='waterOrderpageNav'></div></div>");
                    if(state==1){///根据要查找订单的不同进行不同的操作,这里区分表头
                        $("#tableHead").append("<tr> <th>订水数量</th><th>下单时间</th><th>配送地点</th><th>操作</th></tr> ");
                    }else if(state==2){
                        $("#tableHead").append("<tr> <th>订水数量</th><th>下单时间</th><th>配送地点</th><th>操作</th></tr> ");
                    }else if(state==3){
                        $("#tableHead").append("<tr> <th>订水数量</th><th>下单时间</th><th>配送地点</th> ");
                    }
                    for(var i in list){
                        var waterNum=list[i].waterNum;
                        var userId=list[i].userId;
                        var waterTime = list[i].waterTime;
                        var waterId=list[i].waterId;
                        var operate="";
                        if(state==1){
                            operate="<td><button type='button' class='btn btn-info'onclick='"+"takeWater("+waterId+","+role+","+state+","+pageNum+");"+"'><b>接单</b></button></td>";
                        }else if (state==2){
                            operate="<td><button type='button' class='btn btn-info'onclick='"+"finishWater("+waterId+");"+"'><b>确认配送</b></button></td>";
                        }else if(state==3){
                            operate="";
                        }
                        $("#tableBody").append("<tr><td>"+waterNum+"</td> <td>"+waterTime+"</td><td>"+userId+"</td>"+operate+"</tr>");
                    }
                    showPage(eval(data).pages,eval(data).pageNum,2,total,state);///底部页码栏,2 表示为订水单
                }else if(role==3){///显示维修单
                    ///搭建内容区域
                    $("#main").append("<div class='container img-rounded 'id='thumbnail '><br /> <br/> <div class='container'id='repairOrderList'> </div> <div id='repairOrderpageNav'> </div> /div>");
                    for(var i in list){
                        var repairId=list[i].repairId;
                        var repairTime=list[i].repairTime;
                        var repairDetial = list[i].repairDetial;
                        var userId=list[i].userId;
                        var operate="";
                        if(state==1){
                            operate="<br/><button type='button' class='btn btn-info'onclick='takeRepair();'>接单</button>";
                        }else if(state==2){
                            operate="<br/><button type='button' class='btn btn-info'onclick='finishRepair();'>接单</button>";
                        }else if(state==3){
                            operate="";
                        }
                        $('#repairOrderList').append("订单号:"+repairId+"<br/>下单时间:"+repairTime+"<br/>维修地点:"+userId+"<br/>订单详情:"+repairDetial+operate+"<hr/>");
                    }
                    showPage(eval(data).pages,eval(data).pageNum,3,total,state);///底部页码栏,3 表示为维修单
                }
            }
        },
        error : function(xhr, status, errMsg) {
            alert("系统出现异常!请稍后再试");
        }
    });
}
**/

/**
 * 用于role为2的工作人员进行查找订水订单
 * @param state要查找的订单的状态
 * @param pageNum根据页码查找相关的订单
 */
function  showWater(state,pageNum) {
    var pageSize=10;
    var url="../water/list";
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
                    $("#tableHead").append("<tr> <th>订水数量</th><th>下单时间</th><th>配送地点</th><th>操作</th></tr> ");
                }else if(state==2){
                    $("#tableHead").append("<tr> <th>订水数量</th><th>下单时间</th><th>配送地点</th><th>操作</th></tr> ");
                }else if(state==3){
                    $("#tableHead").append("<tr> <th>订水数量</th><th>下单时间</th><th>配送地点</th> ");
                }
                for(var i in list){
                    var waterNum=list[i].waterNum;
                    var userId=list[i].userId;
                    var waterTime = list[i].waterTime;
                    var waterId=list[i].waterId;
                    var operate="";
                    if(state==1){
                        operate="<td><input type='button'class='btn btn-default'  onclick='"+"takeWater("+2+","+pageNum+");"+"' value='接单'/></td>";
                    }else if (state==2){
                        operate="<td><input type='button' class='btn btn-default' onclick='"+"finishWater("+waterId+","+pageNum+");"+"'value='确认配送'></td>";
                    }else if(state==3){
                        operate="";
                    }
                    $("#tableBody").append("<tr><td>"+waterNum+"</td> <td>"+waterTime+"</td><td>"+userId+"</td>"+operate+"</tr>");
                }
                showPage(eval(data).pages,eval(data).pageNum,2,total,state);///底部页码栏,2 表示为订水单
            }
        },
        error : function(xhr, status, errMsg) {
            alert("系统出现异常!请稍后再试");
        }
    });
}



function showRepair(state,pageNum) {
    console.log(state);
    var pageSize=10;
    var url="../repair/list";
    $.ajax({
        type:"get",
        url:url,
        data: {'pageNum': pageNum,'repairState':state,'pageSize':pageSize},
        dataType : "json",
        success : function(data, textStatus){
            var list = eval(data).list;
            var total= eval(data).total;
            if(total==0){
                $("#main").append("<br/><p>还没有订单喔!</p>");
            }else{
              ///显示维修单,搭建内容区域
                for(var i in list){
                    var repairId=list[i].repairId;
                    var repairTime=list[i].repairTime;
                    var repairDetial = list[i].repairDetial;
                    var userId=list[i].userId;
                    var operate="";
                    if(state==1){
                        operate="<br/><button type='button' class='btn btn-info'onclick='takeRepair("+1+","+pageNum+");'>接单</button>";
                    }else if(state==2){
                        operate="<br/><button type='button' class='btn btn-info'onclick='finishRepair("+repairId+","+pageNum+");'>接单</button>";
                    }else if(state==3){
                        operate="";
                    }
                    $('#repairOrderList').append("订单号:"+repairId+"<br/>下单时间:"+repairTime+"<br/>维修地点:"+userId+"<br/>订单详情:"+repairDetial+operate+"<hr/>");
                }
                showPage(eval(data).pages,eval(data).pageNum,3,total,state);///底部页码栏,3 表示为维修单
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
 * @param type表示订单类型,2表示为订水订单,3表示为维修订单
 * @param total表示记录总数
 * @param state表示订单所属状态,为按钮触发时调用查找订单函数所传入state
 */
function showPage(pages,pageNum,type,total,state){
    var pageNav;
    var functionName;
    if(type==2){
        functionName="showWater";
        pageNav="#waterOrderpageNav";
    }else if(type==3){
        functionName="showRepair";
        pageNav="#repairOrderpageNav";
    }
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
    $(pageNav).append("<br/>共"+total+"条记录");
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

/***
 * 用于订水订单确认操作
 * @param waterId 唯一标识一个订水订单
 */
function finishWater(waterId,pageNum) {
    $.ajax({
        type: "get",
        url: "../water/finishWater",
        dataType: "json",
        data: {'waterId':waterId},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            if(msg==2){
                alert("确认订单失败!");
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
 * 用于维修订单的确认操作
 * @param waterId 唯一标识一个维修订单
 */
function finishRepair(waterId) {
    $.ajax({
        type: "get",
        url: "../repair/finishRepair",
        dataType: "json",
        data: {'waterId':waterId},
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
 * 获取URL中的参数值
 * @param name 参数名称
 * @returns {null}
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