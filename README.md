==== fileUpload文件上传
<br><br>

1.首先修改 配置文件:config.properties
---
upload-path=http://localhost:8080/fileUpload/ <br>
如果是开发工具中启动，则：upload-path=http://localhost:8080/ <br>

2.jsp示例
---
[html]
<form id="testForm" method="post" enctype="multipart/form-data">
    <input type="hidden" name="folder" value="files/test"> <%--files 是固定的--%>
    <input type="file" name="file">
</form>
<button class="test-submit">上传</button>

[js]
//跨域上传文件方法地址
var uploadPath = ""; 
      
//获取上传地址
$.get("/upload/getUploadPath", function (serverUploadPath) {
      uploadPath = serverUploadPath + "upload/uploadFile";
     })
     
//监听提交按钮            
$(".test-submit").on("click", function () {
         var formElement = document.getElementById("testForm");
         var formData = new FormData(formElement);

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
        
        
        
        
