function back() {
    window.location.href = 'page.do?p=userLogin';
}
function reg() {
    let name  = $('#name').val().trim();
    let pass = $('#pass').val().trim();
    let account = $('#account').val().trim();
    let birth = $('#birth').val().trim();
    let sex = $('#sex').val().trim();
    let address = $('#address').val().trim();
    let email = $('#email').val().trim();
    let phone = $('#phone').val().trim();
    let rPass = $('#rpass').val().trim();
    if(name == null || name == ''||
    pass == null || pass == ''||
    account == null || account == ''||
    birth == null || birth == ''||
    sex == null || sex == ''||
    address == null || address == ''||
    email == null || email == ''||
    phone == null|| phone == ''){
        layer.alert("注册数据不能为空");
        return;
    }
    if(pass != rPass){
        layer.alert("两次输入的密码不一致");
        return;
    }
    const phoneCheck = /^1\d{10}$/
    if(!phoneCheck.test(phone)){
        layer.alert("手机号必须为11位数字");
        return;
    }
    const accountCheck = /^[a-zA-Z\d]+$/
    if(!accountCheck.test(account) || account.length > 20||account.length < 5){
        layer.alert('用户名只能为5-20位的字母和数字组合');
        return;
    }
    if(!accountCheck.test(pass) || pass.length > 20 ||pass.length <6){
        layer.alert('用户密码只能为6-20位数字字母组合');
        return;
    }
    if(!address.length > 30){
        layer.alert('用户地址最长为30位');
        return;
    }
    const emailCheck = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    if(!emailCheck.test(email)){
        layer.alert('邮箱格式不正确');
        return;
    }
    let user = {userName:name,userPass:pass,userAccount:account,userSex:sex,userEmail:email,userPhone:phone,userAddress:address,userBirth:new Date(birth)}
    getPublicKey(user);
}
function regSave(user) {

    $.ajax({
        url:'userReg.do',
        type:'POST',
        dataType:'JSON',
        data:user,
        success:function (resp) {

            if(resp.id == 1){
                layer.alert(resp.msg,{},function () {
                    window.location.href="page.do?p=userLogin"
                });
            }else{
                layer.alert(resp.msg);
            }
        },
        error:function (resp) {
            layer.alert("出错啦！！！")
        }
    });
}
function getPublicKey(user) {
    $.ajax({
        url:'rsaGet.do',
        dataType:'JSON',
        type:'GET',
        success:function (resp) {
            if(resp.id == 1){
                user.userPass = rsaEncode(user.userPass,resp.data.rsa);
                regSave(user);
            }else {
                layer.alert("出错啦！！！");
            }
        },
        error:function (resp) {
            layer.alert("出错啦！！！");
        }
    });
}
function rsaEncode(str,key) {
    let encrypt = new JSEncrypt();
    encrypt.setPublicKey(key);
    let pass = encrypt.encrypt(str);
    return pass;
}
