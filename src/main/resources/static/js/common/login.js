function login() {
    $.ajax({
        url: "http://" + window.location.host + "/user/login",
        type: "post",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({
                "username": $("#username").val(),
                "password": $("#password").val()
            }
        ),
        success: function (res) {
            if (res.success === true) {
                $("#error_tips").text("登陆成功");
                window.localStorage.setItem("token", res.data);
            } else {
                $("#error_tips").text(res.msg);
            }
        }
    })
}