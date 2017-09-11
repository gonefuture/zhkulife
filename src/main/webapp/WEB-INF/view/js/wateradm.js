/**
 * Created by Administrator on 2017/9/3 0003.
 */


function loadWorker() {
    $.ajax({
        type: "get",
        url: "../admin/findAdmin",
        dataType: "json",
        success : function(data, textStatus) {
            var list = eval(data).list;
            var total= eval(data).total;
            if(total==0){
                $("#tableBody").append("<tr>暂时还没有工作人员哦!</tr>");
            }else{
                var ids=new Array()
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
                    var adminId = list[i].adminId;
                    $("#tableBody").append("<tr> <th>"+adminName+"</th><th>"+adminZone+"</th><th id="+adminId+2+">加载中...</th><th id="+adminId+3+">加载中...</th></tr> ");
                }
                ////加载当前工作人员正在配送的桶数
                for(var i in ids){
                    var num=loadNumOfWater(ids[i],2);
                    var locat="#"+adminId+2;
                    $(locat).empty();
                    $(locat).append(num+"&nbsp&nbsp桶");
                }
                ////加载当前工作人员已经完成的桶数
                for(var i in ids){
                    var num=loadNumOfWater(ids[i],3);
                    var locat="#"+adminId+3;
                    $(locat).empty();
                    $(locat).append(num+"&nbsp&nbsp桶");
                }
            }
        },
        error : function(xhr, status, errMsg) {
            alert("修改密码失败,请稍后再试!");
        }
    });
}

function loadNumOfWater(adminId,state) {

}