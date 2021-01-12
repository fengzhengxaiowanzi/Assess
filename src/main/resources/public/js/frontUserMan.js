let _userinfo = [];
let _limit = 3;
let _page = 1;
let _total = 1;
let _account = '';
let _startTime = '';
let _endTime = '';
let _phone = '';
let _email = '';
let _sexs =[
    {id:1,name:'男'},
    {id:2,name:'女'}
];
let _modId;
function resetSearch() {
    $('#start-time').val('');
    $('#end-time').val('');
    $('#account').val('');
    $('#phone').val('');
    $('#email').val('');
    _email = '';
    _phone = '';
    _endTime = '';
    _startTime = '';
    _account = '';
    getUserDate(1);
}

function getCount(count) {
    if (count != -1) {
        _total = Math.ceil(count / _limit);
        if (count == 0) {
            _total = 1;
        }
    }
    $('#page').html(_page + "/" + _total)
}

function getUserDate(counts) {
    layer.load(2);
    $.ajax({
        url: 'userShow.do',
        type: 'GET',
        dataType: 'JSON',
        data: {
            account: _account,
            count: counts,
            startTime: _startTime,
            endTime: _endTime,
            offset: (_page - 1) * _limit,
            limit: _limit,
            email: _email,
            phone: _phone,
        },
        success: function (resp) {
            layer.closeAll('loading');
            if (resp.id == 1) {
                console.log(resp);
                _userinfo = resp.data.list;
                refTable(resp.data.list);
                getCount(resp.data.count);
            } else {
                layer.alert(resp.msg);
            }
        }
    });
}

function refTable(pageData) {
    let s = `<thead><tr><th>账户</th><th>姓名</th><th>性别</th><th>电话</th><th>邮箱</th><th>地址</th><th>生日</th><th>操作</th></tr></thead><tbody>`;
    for (let i = 0; i < pageData.length; i++) {
        s += `<tr>
                <td>${pageData[i].userAccount}</td>
                <td>${pageData[i].userName}</td>
                <td>${pageData[i].userSex == 1 ? '男' : '女'}</td>
                <td>${pageData[i].userPhone}</td>
                <td>${pageData[i].userEmail}</td>
                 <td>${pageData[i].userAddress}</td>
                 <td>${timestampToTime(pageData[i].userBirth)}</td>
                <td>
                 <button class="btn btn-mini " type="button" onclick="reset(${pageData[i].id})">重置密码</button>      
                  <button class="btn btn-mini " type="button" onclick="del(${pageData[i].id})">删除</button>       
                  <button class="btn btn-mini " type="button" onclick="mod(${i})">修改</button>              
            </tr>`;
    }
    s += '</tbody>';
    $('#table').html(s);
}

function reset(ids) {
    layer.confirm("确定重置密码为123456吗？", {
        btn: ['确定','取消'] //按钮
    }, function () {
        layer.load(2);
        $.ajax({
            url: 'userReset.do',
            type: 'POST',
            dataType: 'JSON',
            data: {
                token:localStorage.getItem("token"),
                id: ids
            },
            success: function (resp) {
                layer.closeAll('loading');
                if(resp.id == 1002){
                    layer.alert(resp.msg,{},function () {
                        window.location.href = 'page.do?p=' + resp.location;
                    });
                }
                layer.alert(resp.msg);
            }
        });
    },function () {
        return;
    });
}

function del(ids) {
    layer.confirm("确定删除用户", {
        btn: ['确定','取消'] //按钮
    }, function () {
        layer.load(2);
        $.ajax({
            url: 'userDel.do',
            type: 'POST',
            dataType: 'JSON',
            data: {
                token:localStorage.getItem("token"),
                id: ids
            },
            success: function (resp) {
                layer.closeAll('loading');
                if(resp.id == 1002){
                    layer.alert(resp.msg,{},function () {
                        window.location.href = 'page.do?p=' + resp.location;
                    });
                }
                layer.alert(resp.msg);
                if(resp.id == 1){
                    _page = 1;
                    getUserDate(1);
                }
            }
        });
    },function () {
        return;
    });
}

function search() {
    let startTimes = $('#start-time').val().trim();
    let endTimes = $('#end-time').val().trim();
    if (startTimes != null && startTimes != '' && endTimes != null && endTimes != '') {
        if (new Date(startTimes) > new Date(endTimes)) {
            layer.alert("开始时间不能晚于结束时间");
            return;
        }
    }
    _startTime = startTimes;
    _endTime = endTimes;
    _account = $('#account').val().trim();
    _phone = $('#phone').val().trim();
    _email = $('#email').val().trim();
    _page = 1;
    getUserDate(1);
}

function last() {
    if (_page === 1) {
        layer.alert("已到首页");
        return;
    }
    _page--;
    getUserDate(0);
}

function next() {
    if (_page === _total) {
        layer.alert("已到末页");
        return;
    }
    _page++;
    getUserDate(0);
}
function timestampToTime(timestamp) {
    let date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    let Y = date.getFullYear() + '-';
    let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    let D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate());
    let strDate = Y + M + D;
    return strDate;

}
function showAdd() {
    $('#add').modal({
        backdrop: 'static',
        position: 'fit',
        moveable: true,
        rememberPos: true,
        scrollInside: true
    });
    let s = ``;
    for(let i = 0;i<_sexs.length;i++){
        s+=`<option value="${_sexs[i].id}"} >${_sexs[i].name}</option>`
    }
    $('#add-sex').html(s);
}
function hideAdd() {
    $('#add-account').val('');
    $('#add-name').val('');
    $('#add-email').val('');
    $('#add-address').val('');
    $('#add-birth').val('');
    $('#add-phone').val('');
    $('#add-email').val('');
    $('#add').modal('hide', 'fit')
}

function showMod() {
    $('#mod').modal({
        backdrop: 'static',
        position: 'fit',
        moveable: true,
        rememberPos: true,
        scrollInside: true
    })

}

function hideMod() {
    $('#mod-account').val().trim();
    $('#mod-name').val().trim();
    $('#mod-email').val().trim();
    $('#mod-phone').val().trim();
    $('#mod-address').val().trim();
    $('#mod-birth').val().trim();
    $('#mod').modal('hide', 'fit')
}

function mod(index) {
    _modId = _userinfo[index].id;
    let user = _userinfo[index];
    $('#mod-account').val(user.userAccount);
    $('#mod-name').val(user.userName);
    $('#mod-email').val(user.userEmail);
    $('#mod-phone').val(user.userPhone);
    $('#mod-address').val(user.userAddress);
    $('#mod-birth').val(timestampToTime(user.userBirth));
    let s = ``;
    for(let i = 0;i<_sexs.length;i++){
        s+=`<option value="${_sexs[i].id}" ${_sexs[i].id == user.userSex ? 'selected':''} >${_sexs[i].name}</option>`
    }
    $('#mod-sex').html(s);
    showMod();
}

function modOk() {
    let account = $('#mod-account').val().trim();
    let name = $('#mod-name').val().trim();
    let birth = $('#mod-birth').val().trim();
    let phone = $('#mod-phone').val().trim();
    let address = $('#mod-address').val().trim();
    let sex = $('#mod-sex').val().trim();
    let email = $('#mod-email').val().trim();
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
    layer.load(2);
    let user = {id:_modId,userName:name,userAccount: account,userSex:sex,userEmail: email,userPhone: phone,userAddress: address,userBirth: birth}
    $.ajax({
        url: 'userMod.do',
        type: 'POST',
        dataType: 'JSON',
        data: {
            token:localStorage.getItem('token'),
            userStr:JSON.stringify(user)
        },
        success: function (resp) {
            layer.closeAll('loading');
            if (resp.id == 1) {
                layer.alert(resp.msg);
                hideMod();
                getUserDate(1);
            }else if(resp.id == 1002){
                layer.alert(resp.msg,{},function () {
                    window.location.href = 'page.do?p=' + resp.location;
                });
            }else {
                layer.alert(resp.msg);
            }
        },
        error:function (resp) {
            layer.closeAll('loading');
            layer.alert('出错啦！！！')
        }
    });
}

function addOk() {
    let account = $('#add-account').val().trim();
    let name = $('#add-name').val().trim();
    let sex = $('#add-sex').val().trim();
    let email = $('#add-email').val().trim();
    let phone = $('#add-phone').val().trim();
    let address = $('#add-address').val().trim();
    let birth = $('#add-birth').val().trim();
    if (address == null || address == '' ||
        account == null || account == '' ||
        email == null || email == ''||
        phone == null || phone == '' ||
        sex == null || sex == ''||
        name == null || name == ''||
        birth == null || birth == ''
    ) {
        layer.alert('新增数据不能为空');
        return;
    }
    let phoneCheck = /^1\d{10}$/
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
    let user = {userName:name,userAccount: account,userSex:sex,userEmail: email,userPhone: phone,userAddress: address,userBirth: birth}
    $.ajax({
        url: 'userAdd.do',
        type: 'POST',
        dataType: 'JSON',
        data: {
            token:localStorage.getItem("token"),
            userStr:JSON.stringify(user)
        },
        success: function (resp) {
            layer.closeAll('loading');
            if (resp.id == 1) {
                layer.alert(resp.msg);
                hideAdd();
                getUserDate(1);
            }else if(resp.id == 1002){
                layer.alert(resp.msg,{},function () {
                    window.location.href = 'page.do?p=' + resp.location;
                });
            }else {
                layer.alert(resp.msg);
            }
        },
        error:function (resp) {
            layer.closeAll('loading');
            layer.alert('出错啦！！！')
        }
    });

}

getUserDate(1);

