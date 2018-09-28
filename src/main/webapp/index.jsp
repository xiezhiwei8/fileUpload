<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<h2>文件上传测试</h2>
<form id="testForm" method="post" enctype="multipart/form-data">
    <input type="hidden" name="folder" value="files/test"> <%--files 是固定的--%>
    <input type="file" name="file">
</form>
<button class="test-submit">上传</button>
<script type="text/javascript" src="/script/jquery/3.3.1/jquery.min.js"></script>
<script>

    var uploadPath = ""; //跨域上传文件方法地址

    $(function () {
        t.getUploadUrl();
        t.submitListen()
    });
    var t = {
        //获取上传地址
        getUploadUrl: function () {
            $.get("/upload/getUploadPath", function (serverUploadPath) {
                uploadPath = serverUploadPath + "upload/uploadFile";
            })
        },

        //监听提交按钮
        submitListen: function () {

            $(".test-submit").on("click", function () {
                var formElement = document.getElementById("testForm");
                var formData = new FormData(formElement);

                /*ajax方法*/
                $.ajax({
                    type: "post",
                    url: uploadPath,
                    data: formData,
                    contentType: false,//必须   避免JQuery操作，从而失去分界符
                    processData: false,//必须   不转换数据
                    success: function (res) {
                        console.log(res);
                    },
                    error: function (e) {
                        console.log(e)
                    }
                });

            });

        }
    }
</script>
</body>
</html>
