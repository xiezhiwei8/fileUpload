fileUpload
====
文件上传
<br><br>

# 1.首先修改 配置文件:`config.properties`
如果是部署在tomcat中
```
upload-path=http://localhost:8080/fileUpload/
```
如果是开发工具中启动，则：
```
upload-path=http://localhost:8080/
```
# 2.jsp示例
## **[html]**
folder是文件夹，该参数要传给服务器。value值中files不可更改，test可以是自定义目录；也可以多级目录，比如："files/我的文件夹/我的二级文件夹"。
```
<form id="testForm" method="post" enctype="multipart/form-data">
    <input type="hidden" name="folder" value="files/test">
    <input type="file" name="file">
</form>
<button class="test-submit">上传</button>
```
## **[js]**
引入jQuery
```
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
```   

        
        
