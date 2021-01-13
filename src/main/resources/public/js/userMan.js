let _publickey = null;
let _sexs =[
    {id:1,name:'男'},
    {id:2,name:'女'}
    ];
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
                layer.alert(resp.msg,{},function () {
                    window.location.href = 'page.do?p=' + resp.location;
                });
            }else if(resp.id == 1){
                let user = resp.data.user;
                $('#name').val(user.userName);
                $('#address').val(user.userAddress);
                $('#email').val(user.userEmail);
                $('#phone').val(user.userPhone);
                $('#birth').val(timestampToTime(user.userBirth));
                $('#account').val(user.userAccount);
                let s = ``;
                for(let i = 0;i<_sexs.length;i++){
                   s+=`<option value="${_sexs[i].id}" ${_sexs[i].id == user.userSex ? 'selected':''} >${_sexs[i].name}</option>`
                }
                $('#sex').html(s);
            }
        },
        error(resp){
            layer.alert("出错啦");
        }
    });
}
function showAdd() {
    $('#add').modal({
        backdrop: 'static',
        position: 'fit',
        moveable: true,
        rememberPos: true,
        scrollInside: true
    });
}

function hideAdd() {
    $('#new-pass').val('');
    $('#new-rpass').val('');
    $('#old-pass').val('');
    $('#add').modal('hide', 'fit');
}
function saveModPass() {
    let newPass = $('#new-pass').val().trim();
    let newrPass = $('#new-rpass').val().trim();
    let oldPass = $('#old-pass').val().trim();
    if(newPass == null || newPass == ''|| newrPass == null || newrPass == ''|| oldPass == ''||oldPass == null || newrPass == null||newrPass == ''){
        layer.alert("修改数据不能呢为空");
        return;
    }
    if(newPass != newrPass){
        layer.alert("两次输入的修改密码不一致");
        return;
    }
    const accountCheck = /^[a-zA-Z\d]+$/
    if(!accountCheck.test(newPass) || newPass.length > 20 ||newPass.length <6){
        layer.alert('用户密码只能为6-20位数字字母组合');
        return;
    }
    if(_publickey == null){
        getPublicKey(oldPass,newPass);
    }else {
        oldPass = rsaEncode(oldPass,_publickey);
        newPass = rsaEncode(newPass,_publickey);
        savePass(oldPass,newPass);
    }
}
function savePass(oldPass,newPass) {
    $.ajax({
        url:'userPassMod.do',
        type:'POST',
        dataType: 'JSON',
        data:{
            token:localStorage.getItem("token"),
            oldPass,newPass
        },
        success(resp) {
            if(resp.id == 1){
                localStorage.removeItem("token");
                hideAdd();
                layer.alert(resp.msg,{},function () {
                    window.location.href='page.do?p=userLogin'
                });

            }else if(resp.id == 1002){
                layer.alert(resp.msg,{},function () {
                    window.location.href = 'page.do?p=' + resp.location;
                });
            }else{
                layer.alert(resp.msg)
            }
        },
        error(resp) {
            layer.alert("出错啦！！！")
        }
    });
}
function mod() {
    $('.panel-body input').removeAttr('readonly');
    $('#sex').removeAttr('disabled');
    $('#mod-button').html('确定');
    $('#mod-button').attr('onclick','modOk()');
}

function modOk() {
    let account = $('#account').val().trim();
    let name = $('#name').val().trim();
    let birth = $('#birth').val().trim();
    let phone = $('#phone').val().trim();
    let address = $('#address').val().trim();
    let sex = $('#sex').val().trim();
    let email = $('#email').val().trim();
    if (name == null || name == '' ||
        birth == null || birth == '' ||
        account == null || account == '' ||
        phone == null || phone == '' ||
        address == null || address == ''||
        sex == null || sex == ''||
        email == null || email == ''
    ){
        layer.alert("修改数据不能为空");
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
    if(!address.length > 30){
        layer.alert('用户地址最长为30位');
        return;
    }
    const emailCheck = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    if(!emailCheck.test(email)){
        layer.alert('邮箱格式不正确');
        return;
    }
    let date = new Date();
    let nowDate = date.getFullYear()+'-'+((date.getMonth()+1) <= 9 ? '0'+(date.getMonth()+1):date.getMonth())+'-'+(date.getDate() <= 9 ? '0'+ date.getDate():date.getDate());
    if(new Date(birth) - new Date(nowDate)> 0){
        layer.alert('出生年月不能早于今天');
        return;
    }
    let user = {userName:name,userAccount: account,userSex:sex,userEmail: email,userPhone: phone,userAddress: address,userBirth: birth}
    $.ajax({
        url: 'userInfoMod.do',
        type: 'POST',
        dataType: 'JSON',
        data: {
            token:localStorage.getItem("token"),
            user:JSON.stringify(user)
        },
        success: function (resp) {
            if (resp.id == 1) {
                location.reload();
            }else if(resp.id == 1002){
                layer.alert(resp.msg,{},function () {
                    window.location.href = 'page.do?p=' + resp.location;
                });
            }else {
                layer.alert(resp.msg);
            }
        },
        error:function (resp) {
            layer.alert('出错啦！！！')
        }
    });


}
function timestampToTime(timestamp) {
    let date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    let Y = date.getFullYear() + '-';
    let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    let D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate());
    let strDate = Y + M + D;
    return strDate;

}

function getPublicKey(oldPass,newPass) {
    $.ajax({
        url:'rsaGet.do',
        dataType:'JSON',
        type:'GET',
        success:function (resp) {
            if(resp.id == 1){
                _publickey = oldPass,resp.data.rsa;
                oldPass = rsaEncode(oldPass,resp.data.rsa);
                newPass = rsaEncode(newPass,resp.data.rsa);
                savePass(oldPass,newPass);
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
getUser();

