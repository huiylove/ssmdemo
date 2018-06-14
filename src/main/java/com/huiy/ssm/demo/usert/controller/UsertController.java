package com.huiy.ssm.demo.usert.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huiy.ssm.demo.persist.po.UserTPO;
import com.huiy.ssm.demo.usert.service.UserTService;
import com.huiy.ssm.frame.persist.vo.PaginationSupport;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2016年12月13日
 * @version 1.0
 * SpringMVC
 * 假如方法的参数是普通的字符串，只要字符串名字有和请求参数中的key完全匹配的，Spring MVC就会将完全匹配的自动赋值
 * 假如方法的参数是实体类，只要实体类中的参数有和请求参数中的key完全匹配的，Spring MVC就会将完全匹配的自动赋值
 */


@Controller
@RequestMapping("/user")  
public class UsertController {  
	
    @Resource  
    private UserTService usertService;  
      
    @RequestMapping("/showUser")  
    public String toIndex(HttpServletRequest request,Model model,@RequestParam(value="id",required=false) String id){  
//        UserTPO user = this.usertService.queryById(request.getParameter("id"));  
    	UserTPO user = this.usertService.queryById(id);
        model.addAttribute("user", user);  
        return "showUser";  
    }  
    
    @RequestMapping("/showUsers")  
    public ModelAndView showUsers(HttpServletRequest request,UserTPO po){ 
    	ModelAndView mav = new ModelAndView("/usert/userList");
        return mav;  
    }  
    
    @RequestMapping("/userView")  
    public ModelAndView userView(HttpServletRequest request,UserTPO po){  
    	ModelAndView mv = new ModelAndView("/usert/userView");
        UserTPO user = this.usertService.queryById(po.getId());  
        mv.addObject("user", user);  
        return mv;  
    }  
    
    @ResponseBody
    @RequestMapping("/getAll")  
    public JSONObject getAll(HttpServletRequest request,UserTPO po){
    	String page = (StringUtils.isEmpty(request.getParameter("page")))?"1":request.getParameter("page");
    	String rows = (StringUtils.isEmpty(request.getParameter("rows")))?"2":request.getParameter("rows");
    	PaginationSupport<UserTPO> users = this.usertService.queryPage(po,Integer.valueOf(page),Integer.valueOf(rows));
    	Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map  
    	jsonMap.put("total",users.getPaginator().getTotalCount());//total键 存放总记录数，必须的  
    	jsonMap.put("rows",(List<UserTPO>)users.getList());//rows键 存放每页记录 list  
    	return JSONObject.fromObject(jsonMap);//格式化result   
    }  
    
}  