/**
 * Created by Administrator on 2017/8/8 0008.
 */


/**
 * 用户进行登录操作
 */
function login(){
    var user_id= $("input[name='user_id']").val();
    var user_password= $("input[name='user_password']").val();
    console.log(user_id+" "+user_password);
    $.ajax({
        type: "post",
        url: "../user/login",
        dataType: "json",
        data: {'userId': user_id, 'userPassword': user_password},
        success: function (data, textStatus) {
            var back_id = eval(data).userId;
            var back_zone=eval(data).userZone;
            if(back_id==user_id){
                window.location.href="user/index.html";
            }
        },
        error : function(xhr, status, errMsg) {
            alert("账号或密码错误!");
        }
    })
}


/////给导航栏的超链接添加参数
/**此方法已弃用
function addParameter() {
    var userId= getUrlParam("userId");
    var zone=getUrlParam("zone");
    $(".home a").attr("href",($(".home a").attr("href")+"?userId="+userId+"&zone="+zone));
    $("#order .a a").attr("href",($("#order .a a").attr("href")+"&userId="+userId+"&zone="+zone));
    $("#order .b a").attr("href",($("#order .b a").attr("href")+"&userId="+userId+"&zone="+zone));
    $("#order .c a").attr("href",($("#order .c a").attr("href")+"&userId="+userId+"&zone="+zone));
    $(".modify a").attr("href",($(".modify a").attr("href")+"?userId="+userId+"&zone="+zone));
}
***/








/*
 根据state传入的参数值查找不同状态的订单
 */
function  showWater(pageNum) {
    var state=getUrlParam("state");
    $.ajax({
        type:"get",
        url:"../water/list",
        dataType : "json",
        data: {'pageNum': pageNum, 'waterState': state},
        success : function(data, textStatus){
            $("#waterOrderList").empty();
            $("#waterOrderpageNav").empty();
            list = eval(data).list;
            total= eval(data).total;
            if(total==0){
                $("#waterOrderList").append("<br/><br/>你还没有相关的订单哦!");
            }else {
                for (var i in list) {
                    var waterId = list[i].waterId;
                    var waterNum = list[i].waterNum;
                    var waterTime = list[i].waterTime;
                    var time = new Date(waterTime);
                    var orderTime = time.toLocaleString();
                    var html="";
                    if(state==3){///如果订单状态为3,则添加一个评价按钮
                        $("#waterOrderList").append("订单号:" + waterId + "<br/>数量:" + waterNum + "桶<br/>下单时间:" + orderTime + "<br/><a href='feedback.html?id="+waterId+"&type=2"+"'><button>评价</button></a>");
                    }else{
                        $("#waterOrderList").append("订单号:" + waterId + "<br/>数量:" + waterNum + "桶<br/>下单时间:" + orderTime + "<hr/>");
                    }

                }
                showPage(eval(data).pages,eval(data).pageNum,2,total);
            }
        },
        error : function(xhr, status, errMsg) {
            alert("查找订单失败!请稍后再试");
        }
    });
}



function showRepair(pageNum) {
    var state=getUrlParam("state");
    $.ajax({
        type:"get",
        url:"../repair/list",
        dataType : "json",
        data: {'pageNum': pageNum, 'waterState': state},
        success : function(data, textStatus){
            $("#repairOrderList").empty();
            $("#repairOrderpageNav").empty();
            list = eval(data).list;
            total= eval(data).total;
            if(total==0){
                $("#repairOrderList").append("<br/><br/>你还没有相关的订单哦!");
            }else{
                for(var i in list){
                    var repairId=list[i].repairId;
                    var operateTime=list[i].operateTime;
                    var repairTime=list[i].repairTime;
                    $("#repairOrderList").append("订单号:"+waterId+"<br/>订单时间:"+operateTime+"<br/>维修时间:"+repairTime+"<br/><a href='feedback.html?id="+repairId+"&type=3"+"'><button>评价</button></a>");
                }
                showPage(eval(data).pages,eval(data).pageNum,3,total);
            }
        },
        error : function(xhr, status, errMsg) {
            alert("查找订单失败!请稍后再试");
        }
    });
}
/**
 *  用于订单显示时分页

 */
/**
 *
 * @param pages 表示总页数
 * @param pageNum 表示当前页面
 * @param type表示订单类型,2表示为订水订单,3表示为维修订单
 * @param total表示记录总数
 */
function showPage(pages,pageNum,type,total){
    var pageNav;
    var functionName;
    if(type==2){
        pageNav="#waterOrderpageNav";
        functionName="showWater";
    }else if(type==3){
        pageNav="#repairOrderpageNav";
        functionName="showRepair"
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
         $(pageNav).append("<button type='button' class='btn btn-info' onclick='"+functionName+"("+pre+")"+"'>上一页</button>");
     }
     for(var i=begin;i<=end;++i){
         if(i===pageNum){
              $(pageNav).append("<button type='button' class='btn btn-success' ><b>"+i+"</b></button>");
         }else{
              $(pageNav).append("<button type='button' class='btn btn-info' onclick='"+functionName+"("+i+")"+"'>"+i+"</button>");
         }
     }
     if(pageNum!=pages){
         $(pageNav).append("<button type='button' class='btn btn-info' onclick='"+functionName+"("+next+")"+"'>下一页</button>");
     }
    $(pageNav).append("<br/>共"+total+"条记录");
}













///订水操作
function bookWater(){
    var userId=getUrlParam('userId');
    var zone  =getUrlParam('zone');
    var waterNum = $('#orderWater input[name="orderNum"]:checked ').val();
    $.ajax({
        type: "get",
        url: "../water/bookWater?userId="+userId+"&zone="+zone+"&waterNum="+waterNum,
        dataType: "json",
        success : function(data) {
            var msg = eval(data).msg;
            var info = eval(data).info;
            if(msg==1){
                alert(info);
            }else if(msg==2){
                alert("订水失败,你还有未完成的订单!");
            }else if(msg==3){
                alert("请选择订水的桶数!")
            }
        },
        error : function(xhr, status, errMsg) {
            alert("预定饮用水失败,请稍后再试!");
        }
    });
}

///报修操作z
/*
function bookRepair(){
    var userId=getUrlParam('userId');
    var zone  =getUrlParam('zone');
    $.ajax({
        type: "post",
        url: "water/bookWater",
        dataType: "json",
        data: {'userId': userId, 'waterNum': waterNum,'zone':zone},
        success : function(data, textStatus) {
            var status = eval(data).status;
            var info = eval(data).info;
            if(status==1){
                alert("你成功预定"+waterNum+"桶水,请等待配送")
            }else {
                alert(info);
            }
        }
    })
}
*/
///修改密码
function updatePassword(){
    var password= $("input[name='password']").val();
    $.ajax({
        type: "get",
        url: "../user/updatePassword",
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
function modifyPhone(){
    var phone= $("input[name='phone']").val();
    $.ajax({
        type: "get",
        url: "../user/updatePhone",
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

function feedback() {
    
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


/**
 *
 * @param type订单类型
 * @param id
 */
function  feedback(type,id) {
    var url;
    var argumentName;
    var feedback= $("input[name='feedbackId']").val();
    if(type==2){
        url="../water/feedbackWater"
        argumentName='waterId';
    }else if(type==3){
        url="../repair/feedbackRepair";
        argumentName='repairId';
    }
    $.ajax({
        type: "get",
        url: url,
        dataType: "json",
        data: {argumentName: id,'feedback':feedback},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            if(msg==1){
                alert("谢谢你的反馈!");
            }else if(msg==2){
                alert("评价失败!请稍后再试!");
            }
        },
        error : function(xhr, status, errMsg) {
            alert("评价失败!请稍后再试!");
        }
    });
}

function feedbackLoading(type,id) {
    $("#content").empty();
    if(type==2){
        $("#content").append("<input type='radio' name='feedbackId' value='0'>&nbsp;好评<br/><br />  <input type='radio' name='feedbackId' value='1'>&nbsp;配送人员服务态度极差<br/><br /> <input type='radio' name='feedbackId' value='2'>&nbsp;桶装水水质有问题<br /><br /> <input type='radio' name='feedbackId' value='3'>&nbsp;饮用水未送达却被标记为已经送达<br /><br /> <input type='button'class='btn btn-default'  onclick='feedback("+type+id+");' value='提交反馈'/> ");
    }else if(type==3){
        $("#content").append(" <input type='radio' name='feedbackId' value='0'>&nbsp;好评<br/><br /> <input type='radio' name='feedbackId' value='1'>&nbsp;工作人员服务态度极差<br/><br /> <input type='radio' name='feedbackId' value='2'>&nbsp;维修质量不好<br /><br /> <input type='radio' name='feedbackId' value='3'>&nbsp;还未进行维修未送达却被标记为已经维修完成<br /><br /> <input type='button'class='btn btn-default'  onclick='feedback("+type+id+");' value='提交反馈'/> ")
    }
}






