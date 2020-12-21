//设置cookie
function setCookie(name,value,day){
    var date = new Date();
    date.setDate(date.getDate() + day);
    window.document.cookie = encrypt(name) + '=' + encrypt(value) + ';expires='+ date;
    console.log(window.document.cookie);
};
//获取cookie
function getCookie(name){
    var reg = RegExp( encrypt(name)+'=([^;]+)');
    var arr = document.cookie.match(reg);
    if(arr){
        return decrypt(arr[1]);
    }else{
        return '';
    }
};
//删除cookie
function delCookie(name){
    setCookie(name,null,-1);
};

/*window.onload = function(){
    vm.username=getCookie("username");
    vm.password=getCookie("password");
    vm.checked=getCookie("savepassword")==1;
};*/
var vm = new Vue({
    el : '#login',
    data : {
        username : '',
        password : '',
        error : false,
        errorMsg : '',
        chLanguage:false,
        international:false,//是否国际化
        sysName:"",//系统名称
        checked:false,
    },
    beforeCreate : function() {
        if (self != top) {
            top.location.href = self.location.href;
        }
    },

    created:function(){
        this.getInternational();
        this.geSysName();
    },
    methods : {
        clang:function(la){
            this.chLanguage=false;
            localStorage.setItem('locale',la);
            i18n.locale=la;
        },
        getInternational: function () {
            $.ajax({
                type : "POST",
                url:"sys/international?_" + $.now(),
                dataType : "json",
                success:function(r){
                    if(r.msg=="true"){
                        vm.international = true;
                    }else{
                        vm.international = false;
                        localStorage.setItem('locale',"zh");

                    }
                }
            });
        },
        geSysName: function () {
            $.ajax({
                type : "POST",
                url:"sys/sysName?_" + $.now(),
                dataType : "json",
                success:function(r){
                    vm.sysName = r.msg;
                }
            });
        },
        login : function(event) {
            if (vm.username == '') {
                vm.error = true;
                vm.errorMsg = '用户名为空';
                return false;
            }
            if (vm.password == '') {
                vm.error = true;
                vm.errorMsg = '密码为空';
                return false;
            }
            var data = "username=" + vm.username + "&password="
                + vm.password;

            if(vm.checked){
                setCookie('username',vm.username,7); //保存帐号到cookie，有效期7天
                setCookie('password',vm.password,7); //保存密码到cookie，有效期7天
                setCookie("savepassword",1,7);
                console.log("vm.username:"+vm.username + "vm.password:"+vm.password + "vm.savepassword:" + 1);

            }else{
                delCookie('username'); //保存帐号到cookie，有效期7天
                delCookie('password'); //保存密码到cookie，有效期7天
                delCookie("savepassword");
            }

            $.ajax({
                type : "POST",
                url : "sys/login",
                data : data,
                dataType : "json",
                success : function(result) {
                    if (result.code == 0) {//登录成功
                        localStorage.setItem("token", result.token);
                        parent.location.href = 'index_tab.html';
                    } else {
                        vm.error = true;
                        vm.errorMsg = result.msg;
                    }
                }
            });
        }
    }
});
