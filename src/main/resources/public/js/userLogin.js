function doLogin() {
    let name = $('#name').val().trim();
    let pass = $('#pass').val().trim();
    if(name == null || name == '' || pass == null || pass == ''){
        layer.alert("用户名与密码不能为空");
        return;
    }
    getPublicKey(name,pass);
}
$(document).keydown(function(event){
    if(event.keyCode == 13){
        doLogin();
    }
});
function login(name,rsaPass) {
    $.ajax({
        url:'userLogin.do',
        type:'POST',
        dataType:'JSON',
        data:{
            name,
            pass:rsaPass,
        },
        success:function (resp) {
            if(resp.id == 1 ){
                layer.alert(resp.msg, {
                }, function(){
                    localStorage.setItem("token",resp.data.token);
                    window.location.href = 'page.do?p=' + resp.location;
                });
            }else{
                layer.alert(resp.msg);
            }
        },
        error:function (resp) {
            layer.alert("出错啦！！！");
        }
    });
}
function toReg() {
    window.location.href = 'page.do?p=userReg'
}
function getPublicKey(name,pass) {
    let str = null;
    $.ajax({
        url:'rsaGet.do',
        dataType:'JSON',
        type:'GET',
        success:function (resp) {
            if(resp.id == 1){
                str = rsaEncode(pass,resp.data.rsa);
                login(name,str);
            }else {
                layer.alert("出错啦！！！");
            }
        },
        error:function (resp) {
            layer.alert("出错啦！！！");
        }
    });
    return str;
}
function rsaEncode(str,key) {
    let encrypt = new JSEncrypt();
    encrypt.setPublicKey(key);
    let pass = encrypt.encrypt(str);
    return pass;
}
