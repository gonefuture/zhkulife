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

///修改当前登录用户的手机
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
 * 用于添加送水工作人员
 */
///adminId,adminPassword,adminRole,adminPhone,adminZone,adminName
function addStaff(){
    var adminZone= $("input[name='zone']").val();
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

///监听事件,当用户点击删除按钮时触发
function todelete() {
    console.log("触发监听事件!用户此时点击删除按钮");
    var adminId = $('.todelete').attr("id");
    var func = "deleteStaff(" + adminId + ")";
    console.log(func);
    ////给模态框中的确定按钮添加---点击触发函数
    $('#ensure').attr("onclick", func);
}


/**
 * 删除工作人员
 */
function deleteStaff(adminId) {
    console.log("开始调用删除工作人员操作");
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
        },
        error : function(xhr, status, errMsg) {
            alert("系统异常,请稍后再试!");
        }
    });
}




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
                for(var i in list) {
                    var adminName = list[i].adminName;
                    var adminZone = list[i].adminZone;
                    var adminId = list[i].adminId;
                    if(adminZone==1){
                        adminZone="海珠校区";
                    }else if(adminZone==2){
                        adminZone="白云校区";
                    }
                    var delButton="<button class='todelete btn btn-primary ' data-toggle='modal'onclick='todelete();' data-target='#myModal'id='"+adminId+"'> 删除 </button>";

                    var modifyButton="<a href='staffModify.html'><button class='btn btn-primary' id='"+adminId+"'> 修改 </button></a>";
                    $("#tableBody").append("<tr> <th>"+adminName+"</th><th>"+adminZone+"</th><th>"+delButton+modifyButton+"</th></tr> ");
                }

            }
        },
        error : function(xhr, status, errMsg) {
            alert("系统异常,请稍后再试!");
        }
    });
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







