function register() {
    $.ajax({
        // TODO 取消注释
        // url: "http://" + window.location.host + "/user/register",
        url: "http://localhost:8090/user/register",
        type: "post",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({
            "username": $("#username").val(),
            "password": $("#password").val(),
            "nickname": $("#nickname").val(),
            "phone": $("#phone").val(),
            "role": $('input[type="radio"][name="role"]:checked').val()
        }),
        success: function (res) {
            if (res.success === true) {
                $("#error_tips").text("注册成功");
                window.localStorage.setItem("token", res.data);
            } else {}
                $("#error_tips").text(res.msg);
        }
    })
}