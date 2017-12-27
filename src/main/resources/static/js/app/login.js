function doLogin() {
    var account = $("#account").val(),
        password = $("#password").val();
    if (account == "" || password == "")
        return alert("账户或密码不能为空!");
    $.ajax({
        url : "/home/doLogin",
        data:{
            "account": account,
            "password": password
        },
        type : "post",
        dataType : "json",
        error:function(data){
            alert("err");
            console.log(data);
        },
        success : function(data) {
            console.log(data);
            if (data){
                if (data["success"]){
                    location.href = "/home/main";
                }else{
                    alert(data["errorMsg"]);
                }
            }else{
                alert("未知错误,请联系管理员!");
            }
        }
    });
}