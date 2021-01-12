function getUser() {
    $.ajax({
        url:'userGet.do',
        dataType:'JSON',
        type:'POST',
        data:{
            token:localStorage.getItem('token')
        },
        success(resp){
            if(resp.id == 1002){
                layui.use('layer', function(){
                    layer.alert(resp.msg, {
                        btn: ['确定'] //按钮
                    }, function(){
                        location.href = `page.do?p=`+resp.location;
                    });
                });

            }else{
                $('#login-user').html(resp.data.user.userAccount);
            }

        },
        error(resp){
            layui.use('layer', function(){
            layer.alert("出错啦");
            })
        }
    });
}
function  changePage(page){
    $('#main-content').prop('src','page.do?p='+page);
}
function loginOut() {
    $.ajax({
        url:'loginOut.do',
        type:'GET',
        dataType:'JSON',
        data:{
            token:localStorage.getItem('token')
        },
        success:function (resp) {
            if(resp.id == 1){
                layui.use('layer', function(){
                    layer.alert(resp.msg,{},function () {
                        localStorage.removeItem("token");
                        window.location = `page.do?p=userLogin`;
                    });
                })
            }else{
                layer.alert(resp.msg,{},function () {
                    layer.alert("出错啦！！！")
                });
            }

        }
    });
}
getUser();
