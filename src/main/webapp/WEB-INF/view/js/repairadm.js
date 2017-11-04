/**
 * Created by Administrator on 2017/9/11 0011.
 */
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
 * 用于添加维修工作人员
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