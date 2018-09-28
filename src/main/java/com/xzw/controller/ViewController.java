package com.xzw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//WEB-INF下JSP页面实现跳转控制器
@RequestMapping("view")
@Controller
public class ViewController {

    //二级Controller请求 ( routeName 路由名称 ,actionName 选择器名称)
    @RequestMapping(value = "{Route}/{Action}", method = RequestMethod.GET)
    public ModelAndView toPage(@PathVariable("Route") String routeName, @PathVariable("Action") String actionName, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv;
        HttpSession session = request.getSession();
        String uid =(String) session.getAttribute("uid");//登录时存一个uid
        String prveUri = (String) session.getAttribute("prveuri");
        mv = IspPermissions(session, uid, routeName, actionName, prveUri, response);
        return mv;
    }

    //一级Controller请求 (actionName 选择器名称)
    @RequestMapping(value = "{Action}", method = RequestMethod.GET)
    public ModelAndView toPage(@PathVariable("Action") String actionName, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv;
        HttpSession session = request.getSession();
        String uid =(String) session.getAttribute("uid");
        String fromUrl = (String) session.getAttribute("fromurl");
        return IspPermissions(session, uid, "", actionName, fromUrl, response);
    }

    //判断用户访问权限
    private ModelAndView IspPermissions(HttpSession session, String uid, String routeName, String actionName, String prveUri, HttpServletResponse response) {
        ModelAndView mv;
        String viewUri;

        //1：组装View的Url
        if (routeName == "") {
            viewUri = actionName;
        } else {
            viewUri = routeName + "/" + actionName;
        }

        //2:判断是否登录 true:去目标页、false：去登录
        if (uid != null) { //开发暂时调整！=
            //重定向到登录页
            mv = new ModelAndView("/login/login");   //注意此处因为有sitemesh包装器，如果不采用重定向登录页会被目标页的包装器包装
            session.setAttribute("prveuri", viewUri);//记录请求页，登陆成功后跳转到该页面。
        } else {
            //转向目标页
            mv = new ModelAndView(viewUri);
            session.setAttribute("prveuri", viewUri);
        }
        return mv;
    }
}
