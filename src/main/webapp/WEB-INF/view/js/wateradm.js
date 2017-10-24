/**
 * Created by Administrator on 2017/9/3 0003.
 */

/**
 * 加载属于当前登录用户所管理的送水工作人员
 */
function loadWorker() {
    $.ajax({
        type: "get",
        url: "../admin/findRole",
        dataType: "json",
        success : function(data, textStatus) {
            var list = eval(data).list;
            var total= eval(data).total;
            if(total==0){
                $("#tableBody").append("<tr>暂时还没有工作人员哦!</tr>");
            }else{
                var ids=new Array();
                for(var i in list) {
                var adminName = list[i].adminName;
                    var adminZone = list[i].adminZone;
                    var adminId = list[i].adminId;
                    if(adminZone==1){
                        adminZone="海珠校区";
                    }else if(adminZone==2){
                        adminZone="白云校区";
                    }
                    ids[i]=adminId;
                    console.log("获取的ID: "+ids[i]);
                    $("#tableBody").append("<tr> <th>"+adminName+"</th><th>"+adminZone+"</th><th id="+adminId+2+">加载中...</th><th id="+adminId+3+">加载中...</th></tr> ");
                }
                ////加载当前工作人员正在配送的桶数
                for(var i in ids){
                    var locat="#"+ids[i]+2;
                    loadNumOfWater(ids[i],2,locat);

                }
                ////加载当前工作人员已经完成的桶数
                for(var i in ids){
                    var locat="#"+ids[i]+3;
                    console.log("工作人员id: "+ids[i]);
                    loadNumOfWater(ids[i],3,locat);
                }

            }
        },
        error : function(xhr, status, errMsg) {
            alert("系统异常,请稍后再试!");
        }
    });
}


/**
 * 加载某个送水工作人员所送的水的数目
 * @param adminId  唯一标识一个工作人员
 * @param state     订单所处的状态
 * @param locat     标识页面某个标签的位置
 */
function loadNumOfWater(adminId,state,locat) {
    console.log("loadNumOfWater工作人员id: "+adminId);
    var url="../water/list";
    $.ajax({
        type:"get",
        url:url,
        data: {'adminId': adminId,'waterState':state},
        dataType : "json",
        success : function(data, textStatus){
            var total= eval(data).total;
            $(locat).empty();
            $(locat).append(total+" 桶");
    },
        error : function(xhr, status, errMsg) {
            alert("系统出现异常!请稍后再试");
        }
    });
}



///修改当前登录用户的密码
function updatePassword(){
    var password= $("input[name='password']").val();
    $.ajax({
        type: "get",
        url: "../admin/updatePassword",
        dataType: "json",
        data: {'adminPassword': password},
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

///修改当前登录用户的手机
function updatePhone(){
    var phone= $("input[name='phone']").val();
    $.ajax({
        type: "get",
        url: "../admin/updatePhone",
        dataType: "json",
        data: {'adminPhone': phone},
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
 * 用于添加送水工作人员
 */
///adminId,adminPassword,adminRole,adminPhone,adminZone,adminName
function addStaff(){
    var adminZone= $("select[name='adminZone']").val();
    console.log("adminZone:"+adminZone);
    var adminId= $("input[name='adminId']").val();
    var adminName= $("input[name='adminName']").val();
    var adminPassword= $("input[name='adminPassword']").val();
    var adminPhone= $("input[name='adminPhone']").val();
    $.ajax({
        type: "get",
        url: "../admin/addRole",
        dataType: "json",
        data: {'adminZone': adminZone,"adminId":adminId,"adminName":adminName,"adminPassword":adminPassword,"adminPhone":adminPhone},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            console.log(msg);///查看后台返回的信息
            var info = eval(data).info;
            alert(info);
        },
        error : function(xhr, status, errMsg) {
            alert("系统异常,请稍后再试!");
        }
    });
}


/**
 * 管理员修改送水人员的信息
 */
function staffManage() {
    var adminZone= $("input[name='zone']").val();
    var adminName= $("input[name='adminName']").val();
    var adminPassword= $("input[name='adminPassword']").val();
    var adminPhone= $("input[name='adminPhone']").val();
    $.ajax({
        type: "get",
        url: "../admin/addRole",
        dataType: "json",
        data: {'adminZone': adminZone,"adminName":adminName,"adminPassword":adminPassword,"adminPhone":adminPhone},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            console.log(msg);///查看后台返回的信息
            var info = eval(data).info;
            alert(info);
        },
        error : function(xhr, status, errMsg) {
            alert("系统异常,请稍后再试!");
        }
    });
}



/**
 * 删除工作人员
 */
function deleteStaff(adminId) {
    console.log("开始调用删除工作人员操作,该工作人员id为:"+adminId);
    $.ajax({
        type: "get",
        url: "../admin/removeRole",
        dataType: "json",
        data: {'adminId': adminId},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            console.log(msg);///查看后台返回的信息
            var info = eval(data).info;
            alert(info);
            /////重新刷新页面
            loadStafflist();
        },
        error : function(xhr, status, errMsg) {
            alert("系统异常,请稍后再试!");
        }
    });
}
/***
 * 在"人员管理"页面加载工作人员列表
 */
function loadStafflist() {
    $.ajax({
        type: "get",
        url: "../admin/findRole",
        dataType: "json",
        success : function(data, textStatus) {
            var list = eval(data).list;
            var total= eval(data).total;
            if(total==0){
                $("#tableBody").append("<tr>暂时还没有工作人员哦!</tr>");
            }else{
                ////清空内容区域,接下来进行填充数据
                var waterAdmZone=getCookie("waterAdmZone");
                $("#tableBody").empty();
                for(var i in list) {
                    var adminName = list[i].adminName;
                    var adminZone = list[i].adminZone;
                    var adminId = list[i].adminId;
                    if(waterAdmZone==adminZone){////显示与当前工作人员相同校区的工人
                        if(adminZone==1){
                            adminZone="海珠校区";
                        }else if(adminZone==2){
                            adminZone="白云校区";
                        }
                        var delButton= "<button type='button' class='am-btn am-btn-warning' onclick='pressDelete("+adminId+");'>删除</button>";
                        var modifyButton="<a href='staffModify.html'><button class='btn btn-primary' id='"+adminId+"'> 修改 </button></a>";
                        $("#tableBody").append("<tr> <th>"+adminName+"</th><th>"+adminZone+"</th><th>"+delButton+modifyButton+"</th></tr> ");
                    }
                }

            }
        },
        error : function(xhr, status, errMsg) {
            alert("系统异常,请稍后再试!");
        }
    });
}

/**
 *"用户点击"人员管理"页面中的删除button时调用该函数
 * @param id 工作人员的id
 */
function pressDelete(id) {
    console.log("用户点击的id:" + id);
    var flag=isOrNot();
    console.log("flag的值:"+flag);
   if(flag==1){///返回为1的时候确定删除
       deleteStaff(id);
   }
}

function isOrNot() {
    $('#my-confirm').modal({
        relatedTarget: this,
        onConfirm: function (options,id) {
            var flag=1;
            return flag;
        },
        onCancel: function () {
            flag=2;
            return flag;
        }
    });
    // console.log("jdjsfsd"+flag);
    // return flag;
}




/**
 * 修改工作人员信息
 */
function staffModify() {
    console.log("调用修改工作人员信息方法");
    var adminZone= $("select[name='adminZone']").val();
    var adminName= $("input[name='adminName']").val();
    var adminPassword= $("input[name='adminPassword']").val();
    var adminPhone= $("input[name='adminPhone']").val();
    $.ajax({
        type: "get",
        url: "../admin/modifyRole",
        dataType: "json",
        data: {'adminZone': adminZone,"adminName":adminName,"adminPassword":adminPassword,"adminPhone":adminPhone},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            console.log(msg);///查看后台返回的信息
            var info = eval(data).info;
            alert(info);
        },
        error : function(xhr, status, errMsg) {
            alert("系统异常,请稍后再试!");
        }
    });
}




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



