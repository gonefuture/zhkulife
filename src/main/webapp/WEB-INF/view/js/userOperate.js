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
        ////清空提示模态框里面的内容
        $("#alert-info").empty();
        ////向模态框添加服务器返回的信息
        $("#alert-info").append("请先输入账号!");
        /////调用函数,显示模态框
        alertInfo();
        return;
    }
    if(user_password==null||user_password==""){
        ////清空提示模态框里面的内容
        $("#alert-info").empty();
        ////向模态框添加服务器返回的信息
        $("#alert-info").append("请先输入密码!");
        /////调用函数,显示模态框
        alertInfo();
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
                ////清空提示模态框里面的内容
                $("#alert-info").empty();
                ////向模态框添加服务器返回的信息
                $("#alert-info").append(info);
                /////调用函数,显示模态框
                alertInfo();
            }else if(msg==3){
                window.location.href="user/modify.html";
                ////清空提示模态框里面的内容
                $("#alert-info").empty();
                ////向模态框添加服务器返回的信息
                $("#alert-info").append(info+"请先进行修改！");
                /////调用函数,显示模态框
                alertInfo();
            }
        },
        error : function(xhr, status, errMsg) {
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("系统异常，请稍候再试!");
            /////调用函数,显示模态框
            alertInfo();
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

    $.ajax({
        type:"get",
        url:"../user/waterList",
        dataType : "json",
        data: {'pageNum': pageNum},
        success : function(data, textStatus){
            $("#waterOrderList").empty();
            $("#waterOrderpageNav").empty();
            list = eval(data).list;
            total= eval(data).total;
            if(total==0){
                $("#waterOrderList").append("<br/><br/>你还没有的订单哦!");
            }else {
                for (var i in list) {
                    var waterId = list[i].waterId;
                    var waterNum = list[i].waterNum;
                    var waterState = list[i].waterState;
                    var adminPhone = list[i].adminPhone;
                    ///waterTime为订单的下单的时间
                    var waterTime = list[i].waterTime;
                    waterTime=waterTime.substring(0,16);
                    ////operateTime为最近一次对该订单进行操作的时间
                    var operateTime=list[i].operateTime;
                    var now= new Date();
                    operateTime=new Date(operateTime);
                    ///求上一次对该订单操作的时间和当前时间的间隔
                    var gap=now.getTime()-operateTime.getTime();
                    /////求得具体的间隔天数
                    gap=gap/(24*60*60*1000);
                    var feedback1="";
                    var feedback2="";
                    if(waterState==1&&gap>=2) {////状态为1的订单超过两天未受理
                        feedback1 = "<br/>该订单已经超过两天未处理,你可以进行投诉<br/>"+"<button onclick="+"pressWaterFeedback("+1+",'"+waterId+"'"+")>投诉</button>";
                    }else if(waterState==2&&gap>=2){///状态为2的订单超过两天未受理
                        feedback2= "<br/>该订单已经超过两天未处理,你可以进行投诉<br/>"+"<button onclick="+"pressWaterFeedback("+2+",'"+waterId+"'"+")>投诉</button>";
                    }
                    /////stateDescription用于描述订单的状态
                    var stateDescription="";
                    if(waterState==1){
                        stateDescription="<br/>订单状态:&nbsp&nbsp尚未处理";
                    }else if(waterState==2){
                        stateDescription="<br/>订单状态:&nbsp&nbsp已受理";
                    }else if(waterState==3){
                        stateDescription="<br/>订单状态:&nbsp&nbsp配送中";
                    }else if(waterState==4){
                        stateDescription="<br/>订单状态:&nbsp&nbsp已完成";
                    }
                    if(waterState==4){///如果订单状态为4,则添加一个评价按钮
                        $("#waterOrderList").append("订单号:&nbsp&nbsp" + waterId + "<br/>数量:&nbsp&nbsp"
                            + waterNum + "桶<br/>工作人员手机:&nbsp&nbsp"+adminPhone+"<br/>下单时间:&nbsp&nbsp"
                            + waterTime + stateDescription+"<br/><a href='feedback.html?id="+waterId+"&type=2"+"'><button>评价</button></a><hr/>");
                    }else if(waterState==3){///如果订单状态为3,则添加一个确认送达按钮
                        $("#waterOrderList").append("订单号:&nbsp&nbsp" + waterId + "<br/>数量:&nbsp&nbsp"
                            + waterNum + "桶<br/>工作人员手机:&nbsp&nbsp"+adminPhone+"<br/>下单时间:&nbsp&nbsp"
                            + waterTime + stateDescription+"<br/><a ><button onclick="+"pressDetermineTheDeliveryWater("+"'"+waterId+"'"+"); >确认送达</button></a><hr/>");
                    }else if(waterState==2){
                        $("#waterOrderList").append("订单号:&nbsp&nbsp" + waterId + "<br/>数量:&nbsp&nbsp"
                            + waterNum + "桶<br/>工作人员手机:&nbsp&nbsp"+adminPhone
                            +"<br/>下单时间:&nbsp&nbsp" +waterTime+stateDescription +feedback2+"<hr/>");
                    }else if(waterState==1){
                        $("#waterOrderList").append("订单号:&nbsp&nbsp" + waterId + "<br/>数量:&nbsp&nbsp"
                            + waterNum + "桶<br/>下单时间:&nbsp&nbsp" +waterTime +stateDescription+feedback1+"<hr/>");
                    }

                }
                showPage(eval(data).pages,eval(data).pageNum,2,total);
            }
        },
        error : function(xhr, status, errMsg) {
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("查找订单失败!请稍后再试");
            /////调用函数,显示模态框
            alertInfo();
        }
    });
}






function showRepair(pageNum) {
    $.ajax({
        type:"get",
        url:"../user/repairList",
        dataType : "json",
        data: {'pageNum': pageNum},
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
                    console.log("repairDetail:  "+list[i].repairDetial);
                    var repairState = list[i].repairState;
                    ////repairTime为下单时间
                    var repairTime=list[i].repairTime;
                    repairTime=repairTime.substring(0,16);
                    ////获取图片名
                    var repairPic = list[i].repairPic;
                    console.log("repairPic:"+repairPic);
                    var img="";
                    ///避免null值不能判断的情况,统一转化为String类型进行比较
                    var repairPic=new String(repairPic);
                    if(repairPic=="null"){
                        img="用户未上传";
                    }else {
                        repairPic="/zhkulife/img/repair/"+repairPic;
                        img="<br/><img src="+repairPic+" width='90%' height='200'>";
                    }

                    ////operateTime为最近一次对该订单进行操作的时间
                    var operateTime=list[i].operateTime;
                    var now= new Date();
                    operateTime=new Date(operateTime);
                    ///求上一次对该订单操作的时间和当前时间的间隔
                    var gap=now.getTime()-operateTime.getTime();
                    /////求得具体的间隔天数
                    gap=gap/(24*60*60*1000);
                    var feedback1="";
                    var feedback2="";
                    if(repairState==1&&gap>=2) {////状态为1的订单超过两天未受理
                        feedback1 = "<p>该订单已经超过两天未处理,你可以进行投诉</p><br/>"+"<button class='btn btn-default' onclick="+"pressRepairFeedback("+1+",'"+repairId+"'"+")>投诉</button>";
                    }else if(repairState==2&&gap>=2){///状态为2的订单超过两天未受理
                        feedback2= "<p>该订单已经超过两天未处理,你可以进行投诉</p><br/>"+"<button class='btn btn-default' onclick="+"pressRepairFeedback("+2+",'"+repairId+"'"+")>投诉</button>";
                    }
                    /////stateDescription用于描述订单的状态
                    var stateDescription="";
                    if(repairState==1){
                        stateDescription="<br/>订单状态:&nbsp&nbsp尚未处理";
                    }else if(repairState==2){
                        stateDescription="<br/>订单状态:&nbsp&nbsp已受理";
                    }else if(repairState==3){
                        stateDescription="<br/>订单状态:&nbsp&nbsp配送中";
                    }else if(repairState==4){
                        stateDescription="<br/>订单状态:&nbsp&nbsp已完成";
                    }
                    if(repairState==1){
                        $("#repairOrderList").append("订单号:"+repairId+"<br/>订单时间:"+repairTime+stateDescription
                            +"<br/>故障信息:"+repairDetail+"<br/>图片详情:"+img+"<br/>"+feedback1+"<hr/>");
                    }else if(repairState==2){
                        $("#repairOrderList").append("订单号:"+repairId+"<br/>工作人员手机:"+adminPhone
                            +"<br/>订单时间:"+repairTime+stateDescription+"<br/>故障信息:"+repairDetail+"<br/>图片详情:"+img+"<br/>"+feedback2+"<hr/>");
                    }else if(repairState==3){
                        $("#repairOrderList").append("订单号:"+repairId+"<br/>工作人员手机:"
                            +adminPhone+"<br/>订单时间:"+repairTime+stateDescription+"<br/>故障信息:"+repairDetail
                            +"<br/>图片详情:"+img+"<br/><a ><button class='btn btn-default'onclick="+"pressDetermineTheDeliveryRepair("+"'"+repairId+"'"+"); >确认送达</button></a><hr/>");
                    }else if(repairState==4){
                        $("#repairOrderList").append("订单号:"+repairId+"<br/>工作人员手机:"
                            +adminPhone+"<br/>订单时间:"+repairTime+stateDescription+"<br/>故障信息:"+repairDetail
                            +"<br/>图片详情:"+img+"<br/><a href='feedback.html?id="+repairId+"&type=3"+"'><button class='btn btn-default'>评价</button></a><hr/>");
                    }
                }
                showPage(eval(data).pages,eval(data).pageNum,3,total);
            }
        },
        error : function(xhr, status, errMsg) {
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("查找订单失败!请稍后再试");
            /////调用函数,显示模态框
            alertInfo();
        }
    });
}


/**
 *  用于订单显示时分页
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




/**
 *"用户点击"首页"页面中的提交订水时调用该函数
 */
function pressBookWater() {
    $('#bookWater').modal({
        relatedTarget: this,
        ///用户点击确定,则调用订水函数
        onConfirm: function (options,id) {
            bookWater();
        },
        ///用户点击取消则不进行任何操作
        onCancel: function () {

        }
    });
}


/**
 *"提交订单时显示从服务器返回的信息
 */
function alertInfo() {
    $('#my-alert').modal();
}




///订水操作
function bookWater(){
    console.log("提交订水订单!");
    var waterNum = $('#orderWater input[name="orderNum"]:checked ').val();
    if(waterNum==null||waterNum==0){
        ////清空提示模态框里面的内容
        $("#alert-info").empty();
        ////向模态框添加服务器返回的信息
        $("#alert-info").append("请先选择订水的桶数!");
        /////调用函数,显示模态框
        alertInfo();
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
            if(msg==600){
                ////清空提示模态框里面的内容
                $("#alert-info").empty();
                ////向模态框添加服务器返回的信息
                $("#alert-info").append(info);
                /////调用函数,显示模态框
                alertInfo();
                window.location.href="../userlog.html";
                return ;
            }else{
                if(msg==6){
                    ////清空提示模态框里面的内容
                    $("#alert-info").empty();
                    ////向模态框添加服务器返回的信息
                    $("#alert-info").append(info);
                    /////调用函数,显示模态框
                    alertInfo();
                }else if(msg==5){
                    ////清空提示模态框里面的内容
                    $("#alert-info").empty();
                    ////向模态框添加服务器返回的信息
                    $("#alert-info").append("订水失败，在上一订单处于未完成的状态时，你不能进行订水操作。如果桶装水已经送达,请先确认送达。如果订单长时间无人处理，你可以去订单查看页面进行投诉");
                    /////调用函数,显示模态框
                    alertInfo();
                }else if(msg==4){
                    ////清空提示模态框里面的内容
                    $("#alert-info").empty();
                    ////向模态框添加服务器返回的信息
                    $("#alert-info").append("订水失败,请您先修改登录密码和预留手机号码");
                    $("#alert-info").append("<br/><a href='modifyPswAndPhone.html'><button>前往修改页面</button></a>")
                    /////调用函数,显示模态框
                    alertInfo();
                }else if(msg==3){
                    ////清空提示模态框里面的内容
                    $("#alert-info").empty();
                    ////向模态框添加服务器返回的信息
                    $("#alert-info").append("订水失败,请先修改登录密码");
                    $("#alert-info").append("<br/><a href='modify.html?modifyType=2'><button>前往修改页面</button></a>")
                    /////调用函数,显示模态框
                    alertInfo();
                }else if(msg==2){
                    ////清空提示模态框里面的内容
                    $("#alert-info").empty();
                    ////向模态框添加服务器返回的信息
                    $("#alert-info").append("订水失败,请修改宿舍的联系手机号码");
                    $("#alert-info").append("<br/><a href='modify.html?modifyType=1'><button>前往修改页面</button></a>")
                    /////调用函数,显示模态框
                    alertInfo();
                }

            }

        },
        error : function(xhr, status, errMsg) {
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("系统异常,请稍后再试!");
            /////调用函数,显示模态框
            alertInfo();
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
    console.log("提交报修订单");
    var repairDetial= $("#repairDetial").val();
    if(repairDetial==""||repairDetial=="null"){
        ////清空提示模态框里面的内容
        $("#alert-info").empty();
        ////向模态框添加服务器返回的信息
        $("#alert-info").append("请先您输入描述再进行提交");
        /////调用函数,显示模态框
        alertInfo();
    }else{
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
                if(msg==600){
                    ////清空提示模态框里面的内容
                    $("#alert-info").empty();
                    ////向模态框添加服务器返回的信息
                    $("#alert-info").append(info);
                    /////调用函数,显示模态框
                    alertInfo();
                    window.location.href="../userlog.html";
                    return ;
                }else{
                    ////清空提示模态框里面的内容
                    $("#alert-info").empty();
                    ////向模态框添加服务器返回的信息
                    $("#alert-info").append(info);
                    /////调用函数,显示模态框
                    alertInfo();
                }
            },
            error : function(xhr, status, errMsg) {
                ////清空提示模态框里面的内容
                $("#alert-info").empty();
                ////向模态框添加服务器返回的信息
                $("#alert-info").append("系统异常,请稍后再试!");
                /////调用函数,显示模态框
                alertInfo();
            }
        };
        $("#fileinfo").ajaxSubmit(options);
    }
}




/**
 *"用户点击"修改"页面中的提交修改密码时调用该函数
 */
function pressUpdatePassword() {
    $('#toUpdatePassword').modal({
        relatedTarget: this,
        ///用户点击确定,则调用修改密码函数
        onConfirm: function (options,id) {
            updatePassword();
        },
        ///用户点击取消则不进行任何操作
        onCancel: function () {

        }
    });
}

///修改密码
function updatePassword(){
    var password= $("input[name='password']").val();
    if(password=="123456"||password=="654321"||password=="asdfgh"){
        ////清空提示模态框里面的内容
        $("#alert-info").empty();
        ////向模态框添加服务器返回的信息
        $("#alert-info").append("密码过于简单,请重新输入");
        /////调用函数,显示模态框
        alertInfo();
        return;
    }
    $.ajax({
        type: "get",
        url: "../user/updatePassword",
        dataType: "json",
        data: {'password': password},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            if(msg==1){
                ////清空提示模态框里面的内容
                $("#alert-info").empty();
                ////向模态框添加服务器返回的信息
                $("#alert-info").append("修改成功!");
                /////调用函数,显示模态框
                alertInfo();
            }else if(msg==2){
                ////清空提示模态框里面的内容
                $("#alert-info").empty();
                ////向模态框添加服务器返回的信息
                $("#alert-info").append("修改密码失败!请稍后再试!");
                /////调用函数,显示模态框
                alertInfo();
            }
        },
        error : function(xhr, status, errMsg) {
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("系统异常,请稍后再试!");
            /////调用函数,显示模态框
            alertInfo();
        }
    });
}



/**
 *"用户点击"修改"页面中的提交修改手机时调用该函数
 */
function pressModifyPhone() {
    $('#toModifyPhone').modal({
        relatedTarget: this,
        ///用户点击确定,则调用修改密码函数
        onConfirm: function (options,id) {
            modifyPhone();
        },
        ///用户点击取消则不进行任何操作
        onCancel: function () {

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
                ////清空提示模态框里面的内容
                $("#alert-info").empty();
                ////向模态框添加服务器返回的信息
                $("#alert-info").append("修改成功!");
                /////调用函数,显示模态框
                alertInfo();
            }else if(msg==2){
                ////清空提示模态框里面的内容
                $("#alert-info").empty();
                ////向模态框添加服务器返回的信息
                $("#alert-info").append("修改手机失败!请稍后再试!");
                /////调用函数,显示模态框
                alertInfo();
            }
        },
        error : function(xhr, status, errMsg) {
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("系统异常,请稍后再试!");
            /////调用函数,显示模态框
            alertInfo();
        }
    });
}


/**
 * 修改密码和手机号码,用于用户在还未修改初始信息时进行下单操作时,修改手机号码和密码
 */
function modifyPswAndPhone() {
    var phone= $("input[name='phone']").val();
    var password= $("input[name='password']").val();
    if(password=="123456"||password=="654321"||password=="asdfgh"){
        ////清空提示模态框里面的内容
        $("#alert-info").empty();
        ////向模态框添加服务器返回的信息
        $("#alert-info").append("密码过于简单,请重新输入");
        /////调用函数,显示模态框
        alertInfo();
        return ;
    }
    $.ajax({
        type: "get",
        url: "../user/updateData",
        dataType: "json",
        data: {'phone': phone,'password':password},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            var info=eval(data).info;
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append(info);
            /////调用函数,显示模态框
            alertInfo();
        },
        error : function(xhr, status, errMsg) {
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("系统异常,请稍后再试!");
            /////调用函数,显示模态框
            alertInfo();
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
 * 用户在查看订水订单页面点击按钮"确认送达",则调用该函数
 * @param orderId 订单ID
 */
function pressDetermineTheDeliveryWater(orderId) {
    $("#completeWaterId").empty();
    $("#completeWaterId").append(orderId);
    $('#pressDetermineTheDeliveryWater').modal({
        relatedTarget: this,
        ///用户点击确定,则调用订水函数
        onConfirm: function (options,id) {
            determineTheDeliveryWater();
        },
        ///用户点击取消则不进行任何操作
        onCancel: function () {

        }
    });
}


/*
用户确认送水订单已经送达
 */
function determineTheDeliveryWater() {
    ////从模态框的<span id="orderId"></span>中获取orderId
    var   orderId=$("#completeWaterId").text();
    console.log("orderId="+orderId);
    $.ajax({
        type: "get",
        url: "../user/finishWater",
        dataType: "json",
        data: {'waterId':orderId},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            var info= eval(data).info;
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append(info);
            /////调用函数,显示模态框
            alertInfo();
            //////确认成功之后重新刷新页面数据
            showWater(1);
        },
        error : function(xhr, status, errMsg) {
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("系统异常,请稍后再试!");
            /////调用函数,显示模态框
            alertInfo();
        }
    });
}

/**
 *
 * @param orderId 订单ID
 */

function pressDetermineTheDeliveryRepair(orderId) {
    $("#completeRepairOrderid").empty();
    $("#completeRepairOrderid").append(orderId);
    $('#pressDetermineTheDeliveryRepair').modal({
        relatedTarget: this,
        ///用户点击确定,则调用函数,确认订单已经完成
        onConfirm: function (options,id) {
            determineTheDeliveryRepair();
        },
        ///用户点击取消则不进行任何操作
        onCancel: function () {

        }
    });
}

/*
 用户确认送水订单已经送达
 */
function determineTheDeliveryRepair() {
    ////从模态框的<span id="orderId"></span>中获取orderId
    var   orderId=$("#completeRepairOrderid").text();
    console.log("啦啦啦啦绿绿"+orderId);
    console.log("orderId="+orderId);
    $.ajax({
        type: "get",
        url: "../user/finishRepair",
        dataType: "json",
        data: {'repairId':orderId},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            var info= eval(data).info;
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append(info);
            /////调用函数,显示模态框
            alertInfo();
            //////确认成功之后重新刷新页面数据
            showRepair(1);
        },
        error : function(xhr, status, errMsg) {
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("系统异常,请稍后再试!");
            /////调用函数,显示模态框
            alertInfo();
        }
    });
}








/**
 * 用户在查看订单页面点击按钮"投诉"或在评价页面提交反馈,则调用该函数
 * @param state为订单状态
 * @param orderId 该参数的来源来自于加载订单时放在button中的onclick事件触发的pressFeeddback()函数
 */
function pressWaterFeedback(state,orderId) {
    $("#feedbackWaterOrderId").empty();
    $("#feedbackWaterOrderId").append(orderId);
    $('#toWaterFeedback').modal({
        relatedTarget: this,
        ///用户点击确定,则调用订水函数
        onConfirm: function (options,id) {
            waterFeedback(state);
        },
        ///用户点击取消则不进行任何操作
        onCancel: function () {

        }
    });
}


/**
 * 对订水订水订单状态为1或者订单状态为2的订单
 */
function waterFeedback(state) {
    var orderId=0;
    var feedback=0;
    if(state==1){
        ////从模态框的<span id="orderId"></span>中获取orderId
        orderId=$("#feedbackWaterOrderId").text();
        console.log(orderId);
        ////状态为1的订单只能反馈1
        feedback=1;
    }else if(state==2){
        orderId=$("#feedbackWaterOrderId").text();
        console.log(orderId);
        ///订单状态为2只能反馈2
        feedback=2;
    }else if(state==3){
        orderId=$("#feedbackWaterOrderId").text();
        console.log(orderId);
        ///订单状态为3的反馈从页面的输入中获取
        feedback= $("input[name='feedbackId']:checked").val();
    }
    console.log("orderId:   "+orderId);
    $.ajax({
        type: "get",
        url: "../user/feedbackWater",
        dataType: "json",
        data: {'id': orderId,'feedback':feedback},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            var info= eval(data).info;
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("反馈成功");
            /////调用函数,显示模态框
            alertInfo();
        },
        error : function(xhr, status, errMsg) {
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("系统异常,请稍后再试!");
            /////调用函数,显示模态框
            alertInfo();
        }
    });
}


/**
 * 用户点击订单反馈按钮调用该函数
 * @param state  订单所处的状态
 * @param orderId   订单ID
 */

function pressRepairFeedback(state,orderId) {
    $("#feedbackRepairOrderId").empty();
    $("#feedbackRepairOrderId").append(orderId);
    $('#toRepairFeedback').modal({
        relatedTarget: this,
        ///用户点击确定,则调用订水函数
        onConfirm: function (options,id) {
            repairFeedback(state);
        },
        ///用户点击取消则不进行任何操作
        onCancel: function () {

        }
    });
}

/**
 *
 * 用户在模态窗口点击确认,则调用该函数进行保修订单的反馈
 * @param state 订单的状态
 */

function repairFeedback(state) {
    var orderId=0;
    var feedback=0;
    if(state==1){
        ////从模态框的<span id="orderId"></span>中获取orderId
        orderId=$("#feedbackRepairOrderId").text();
        console.log("orderId="+orderId);
        ////状态为1的订单只能反馈1
        feedback=1;
    }else if(state==2){
        orderId=$("#feedbackRepairOrderId").text();
        console.log("orderId="+orderId);
        ///订单状态为2只能反馈2
        feedback=2;
    }else if(state==3){
        ///订单状态为3的反馈从页面的输入中获取
        orderId=$("#feedbackRepairOrderId").text();
        console.log("orderId="+orderId);
        feedback= $("input[name='feedbackId']:checked").val();
    }
    $.ajax({
        type: "get",
        url: "../user/feedbackRepair",
        dataType: "json",
        data: {'id': orderId,'feedback':feedback},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            var info= eval(data).info;
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append(info);
            /////调用函数,显示模态框
            alertInfo();
        },
        error : function(xhr, status, errMsg) {
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("系统异常,请稍后再试!");
            /////调用函数,显示模态框
            alertInfo();
        }
    });
}




/**
 *
 * @param type订单类型
 * @param id

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
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append(info);
            /////调用函数,显示模态框
            alertInfo();
        },
        error : function(xhr, status, errMsg) {
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("系统异常,请稍后再试!");
            /////调用函数,显示模态框
            alertInfo();
        }
    });
}

 */



function feedbackLoading() {
    var id=getUrlParam("id");
    ///type用来确定订单的类型,是订水订单还是维修订单
    var type=getUrlParam("type");
    $("#content").empty();
    if(type==2){////2表示为订水订单
        $("#content").append(
            "<input type='radio' name='feedbackId' value='3'>&nbsp;桶装水水质有问题<br /><br />" +
            "<input type='radio' name='feedbackId' value='4'>&nbsp;配送人员服务态度极差<br/><br />" +
            "<input type='radio' name='feedbackId' value='5'>&nbsp;好评<br/><br />" +
            "<button onclick="+"pressWaterFeedback("+3+",'"+id+"'"+")>提交反馈</button>");
    }else if(type==3){//3表示为维修订单
        $("#content").append(
            "<input type='radio' name='feedbackId' value='3'>&nbsp;维修质量不好<br /><br /> "+
            "<input type='radio' name='feedbackId' value='4'>&nbsp;工作人员服务态度极差<br/><br /> " +
            "<input type='radio' name='feedbackId' value='5'>&nbsp;好评<br/><br /> "+
            "<button onclick="+"pressRepairFeedback("+3+",'"+id+"'"+")>提交反馈</button>");
    }
}






