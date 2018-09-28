package com.xzw.controller.upload;

import com.xzw.model.UploadMessage;
import com.xzw.model.UploadMessageData;
import com.xzw.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * @Author: 谢志伟
 * @Description:
 * @Data: Created in 2018/9/26 14:55
 * @ModifiedBy:
 */

@RequestMapping("/upload")
@Controller
public class UploadController {

    @Value("#{configProperties['upload-path']}")
    private String upload_path;


    /**
     * @Author: XieZhiWei
     * @Description:上传文件
     * @param: file:文件
     * @param: folder:上传文件夹
     * @return: UploadMessage
     * @CreateData:2018/9/26 11:44
     **/
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public UploadMessage uploadFile(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
        //获取自定义文件夹
        String folder = request.getParameter("folder");
        try {
            //对folder进行解码
            folder = java.net.URLDecoder.decode(folder, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //自定义文件夹后加上 时间文件夹
        folder = folder + "/" + RandomUtil.GetDataTimeStr();
        //文件夹路径用'/'拆分为数组
        String[] folderArray = folder.split("/");
        //项目真实路径
        String path = request.getServletContext().getRealPath("/");
        //拼装上传真实路径
        for (String item : folderArray) {
            path += item + "\\";
        }
        //获取文件大小
        Long fileSize = file.getSize();
        //获取文件名称
        String fileName = file.getOriginalFilename();
        //获取文件格式，并以日期重新命名
        String newFilename = RandomUtil.getRandomFileName() + fileName.substring(fileName.lastIndexOf("."));
        //创建文件
        File targetFile = new File(path, newFilename);
        if (!targetFile.exists()) {
            //如果目录不存在则新建目录
            targetFile.mkdirs();
        }
        try {
            //写入文件
            file.transferTo(targetFile);
        } catch (Exception e) {
            return reMsg(1, "上传失败", "", fileSize);
        }
        return reMsg(0, "上传成功", upload_path + folder + "/" + newFilename, fileSize);
    }

    /**
     * @Author: XieZhiWei
     * @Description:删除文件
     * @param:[request, response]
     * @return:boolean
     * @CreateData:2018/9/26 12:00
     **/
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteFile(HttpServletRequest request) {
        String filePath = request.getParameter("filePath");
        String newFilePath = filePath.replace(upload_path, "");
        String localFilePath = request.getSession().getServletContext().getRealPath(newFilePath);
        File file = new File(localFilePath);
        if (file.exists()) {//判断是否存在
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }


    /**
     * @Author: XieZhiWei
     * @Description:获取文件上传地址
     * @return: upload_path：上传地址
     * @CreateData:2018/9/26 12:00
     **/
    @RequestMapping("/getUploadPath")
    @ResponseBody
    public String getUploadPath() {
        return upload_path;
    }


    /**
     * @Author: XieZhiWei
     * @Description:返回msg拼装
     * @param: status：状态
     * @param: msg：消息
     * @param: uploadPath：文件地址
     * @return: json
     * @CreateData:2018/9/26 12:01
     **/
    public UploadMessage reMsg(int status, String msg, String uploadPath, Long fileSize) {
        return new UploadMessage(status, msg, new UploadMessageData(uploadPath, fileSize / 1024 + " KB"));
    }
}
