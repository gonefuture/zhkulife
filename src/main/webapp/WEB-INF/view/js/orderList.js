/**
 * Created by Administrator on 2017/8/4 0004.
 */
    function showOrder(){
        $.ajax({
            type:"get",
            url:"water/list?pageNum=1&pageSize=5",
            dataType : "json",
            success : function(data, textStatus){
                var list = eval(data).list;
                for(var i in list){
                    var waterId=list[i].waterId;
                    var waterNum=list[i].waterNum;
                    var waterTime = list[i].waterTime;
                    var time = new Date( waterTime ) ;
                    var orderTime = time.toLocaleString();
                    $("#tableBody").append(" <tr><td>"+waterId+"</td> <td>"+waterNum+"</td><td>"+orderTime+"</td><td><a href=''><button type='button' class='btn btn-info'><b>接单</b></button></a></td></tr>");
                }

            },
            error : function(xhr, status, errMsg) {
                alert("查找订单失败!请稍后再试");
            }
        });
    }
