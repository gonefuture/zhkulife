/**
 * Created by Administrator on 2017/8/8 0008.
 */


/**
 * 用户进行登录操作
 */
function login(){
    var user_id= $("input[name='user_id']").val();
    var user_password= $("input[name='user_password']").val();
    if(user_id==null||user_id==""){
        alert("请先输入账号!");
        return;
    }
    if(user_password==null||user_password==""){
        alert("请先输入密码!");
        return;
    }
    console.log(user_id+" "+user_password);
    $.ajax({
        type: "post",
        url: "user/login",
        dataType: "json",
        data: {'userId': user_id, 'userPassword': user_password},
        success: function (data, textStatus) {
            var msg = eval(data).msg;
            var info=eval(data).info;
            if(msg==1){
                window.location.href="user/index.html";
            }else if(msg==2){
                alert(info);
            }else if(msg==3){
                window.location.href="user/modify.html";
                alert(info+"请先进行修改！")
            }
        },
        error : function(xhr, status, errMsg) {
            alert("系统异常，请稍候再试!");
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
    if(state==1){
        $("#state").empty();
        $("#state").append("已预订的订单");
    }else if(state==2){
        $("#state").empty();
        $("#state").append("正在配送的订单");
    }else if(state==3){
        $("#state").empty();
        $("#state").append("已完成的订单");
    }
    $.ajax({
        type:"get",
        url:"../user/waterList",
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
                    var adminPhone = list[i].adminPhone;
                    var waterTime = list[i].waterTime;
                    waterTime=waterTime.substring(0,16);
                    var html="";
                    if(state==3){///如果订单状态为3,则添加一个评价按钮
                        $("#waterOrderList").append("订单号:&nbsp&nbsp" + waterId + "<br/>数量:&nbsp&nbsp" + waterNum + "桶<br/>工作人员手机:&nbsp&nbsp"+adminPhone+"<br/>下单时间:&nbsp&nbsp" + waterTime + "<br/><a href='feedback.html?id="+waterId+"&type=2"+"'><button>评价</button></a><hr/>");
                    }else if(state==2){
                        $("#waterOrderList").append("订单号:&nbsp&nbsp" + waterId + "<br/>数量:&nbsp&nbsp" + waterNum + "桶<br/>工作人员手机:&nbsp&nbsp"+adminPhone+"<br/>下单时间:&nbsp&nbsp" +waterTime + "<hr/>");
                    }else if(state==1){
                        $("#waterOrderList").append("订单号:&nbsp&nbsp" + waterId + "<br/>数量:&nbsp&nbsp" + waterNum + "桶<br/>下单时间:&nbsp&nbsp" +waterTime + "<hr/>");
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
    if(state==1){
        $("#state").empty();
        $("#state").append("已预订的订单");
    }else if(state==2){
        $("#state").empty();
        $("#state").append("正在处理的订单");
    }else if(state==3){
        $("#state").empty();
        $("#state").append("已完成的订单");
    }
    $.ajax({
        type:"get",
        url:"../user/repairList",
        dataType : "json",
        data: {'pageNum': pageNum, 'repairState': state},
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
                    var adminPhone=list[i].adminPhone;
                    var repairDetail=list[i].repairDetial;///此处的单词有误!但不想改!!!!
                    var operateTime=list[i].operateTime;
                    var repairPic = list[i].repairPic;
                    var picSrc="/zhkulife/img/repair/"+repairPic;
                    operateTime=operateTime.substring(0,16);
                    if(state==1){
                        $("#repairOrderList").append("订单号:"+repairId+"<br/>订单时间:"+operateTime+"<br/>故障信息:"+repairDetail+"<br/>图片详情:<br/><img src="+picSrc+" width='200' height='120'"+"><br/>"+"<hr/>");
                    }else if(state==2){
                        $("#repairOrderList").append("订单号:"+repairId+"<br/>工作人员手机:"+adminPhone+"<br/>订单时间:"+operateTime+"<br/>故障信息:"+repairDetail+"<br/>图片详情:<br/><img src="+picSrc+" width='200' height='120'"+"><br/>"+"<hr/>");
                    }else if(state==3){
                        $("#repairOrderList").append("订单号:"+repairId+"<br/>工作人员手机:"+adminPhone+"<br/>订单时间:"+operateTime+"<br/>故障信息:"+repairDetail+"<br/>图片详情:<br/><img src="+picSrc+" width='200' height='120'"+"><br/>"+"<br/><a href='feedback.html?id="+repairId+"&type=3"+"'><button>评价</button></a><hr/>");
                    }
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
    var waterNum = $('#orderWater input[name="orderNum"]:checked ').val();
    if(waterNum==null||waterNum==0){
        alert("请先选择订水的桶数!");
        return;
    }
    $.ajax({
        type: "get",
        url: "../user/bookWater",
        dataType: "json",
        data: {'waterNum': waterNum},
        success : function(data) {
            var msg = eval(data).msg;
            var info = eval(data).info;
            alert(info);
        },
        error : function(xhr, status, errMsg) {
            alert("系统异常,请稍后再试!");
        }
    });
}

///报修操作z
/*
function bookRepair(){
    var repairDetail = $('#orderRepair textarea[name="repairDetail"] ').val();
    console.log(repairDetail);
    $.ajax({
        type: "post",
        url: "../user/bookRepair",
        dataType: "json",
        data: {'repairDetial': repairDetail},//此处json数据detail拼错了,但将错就错,不然要改数据库
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            var info = eval(data).info;
            if(msg==1){
                alert("提交成功!")
            }else if(msg==2){
                alert(info);
            }
        },
        error : function(xhr, status, errMsg) {
            alert("系统异常,请稍后再试!");
        }
    });
}


function bookRepair(){
    var fd = new FormData(document.querySelector("form"));
    console.log(fd);
    $.ajax({
        type: "post",
        url: "../user/bookRepair",
        data: fd,
        processData: false,  // 不处理数据
        contentType: false ,  // 不设置内容类型
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            var info = eval(data).info;
            alert(info);
        },
        error : function(xhr, status, errMsg) {
            alert("系统异常,请稍后再试!");
        }
    });
}
 */

function bookRepair(){
    // 上传设置
    var options = {
        // 规定把请求发送到那个URL
        url: "../user/bookRepair",
        // 请求方式
        type: "post",
        // 服务器响应的数据类型
        dataType: "json",
        // 请求成功时执行的回调函数
        success: function(data, status, xhr) {
            var msg = eval(data).msg;
            var info = eval(data).info;
            alert(info);
        },
        error : function(xhr, status, errMsg) {
            alert("系统异常,请稍后再试!");
        }
    };
    $("#fileinfo").ajaxSubmit(options);
}



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
    var type=getUrlParam("type");
    var id=getUrlParam("id");
    var url;
    var idName;
    var feedback= $("input[name='feedbackId']:checked").val();
    if(type==2){
        url="../user/feedbackWater"
    }else if(type==3){
        url="../user/feedbackRepair";
    }
    $.ajax({
        type: "get",
        url: url,
        dataType: "json",
        data: {'id': id,'feedback':feedback},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            var info= eval(data).info;
            alert(info);
        },
        error : function(xhr, status, errMsg) {
            alert("系统异常,请稍后再试!");
        }
    });
}

function feedbackLoading() {
    var type=getUrlParam("type");
    $("#content").empty();
    if(type==2){
        $("#content").append("<input type='radio' name='feedbackId' value='0'>&nbsp;好评<br/><br />  <input type='radio' name='feedbackId' value='1'>&nbsp;配送人员服务态度极差<br/><br /> <input type='radio' name='feedbackId' value='2'>&nbsp;桶装水水质有问题<br /><br /> <input type='radio' name='feedbackId' value='3'>&nbsp;饮用水未送达却被标记为已经送达<br /><br /> <input type='button'class='btn btn-default'  onclick='feedback("+");' value='提交反馈'/> ");
    }else if(type==3){
        $("#content").append(" <input type='radio' name='feedbackId' value='0'>&nbsp;好评<br/><br /> <input type='radio' name='feedbackId' value='1'>&nbsp;工作人员服务态度极差<br/><br /> <input type='radio' name='feedbackId' value='2'>&nbsp;维修质量不好<br /><br /> <input type='radio' name='feedbackId' value='3'>&nbsp;还未进行维修未送达却被标记为已经维修完成<br /><br /> <input type='button'class='btn btn-default'  onclick='feedback("+");' value='提交反馈'/> ")
    }
}






