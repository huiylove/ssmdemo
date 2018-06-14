package com.huiy.ssm.demo.rest.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huiy.ssm.demo.persist.po.UserTPO;
import com.huiy.ssm.demo.usert.service.UserTService;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2017年7月20日
 * @version 1.0
 * 创建Rest端点
 *
 */

//@RestController   ——默认使用消息转换器
@Controller
@RequestMapping("/rest")
public class RestController {

    @Resource  
    private UserTService usertService; 
    
//    @ResponseBody //@ResponseBody注解告诉Spring跳过正常的模型/视图流程，并使用消息转换器；将返回的对象作为资源返回给客户端
//    @RequestMapping(method=RequestMethod.GET,produces="application/json",consumes="application/json")
//    public List<UserTPO> getUsers(@ModelAttribute("userTPO") UserTPO po,@RequestBody UserTPO upo){
//    	return usertService.queryList(po);
//    }
    
    @ResponseBody
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public UserTPO queryUser(@PathVariable("id") String id){
    	return usertService.queryById(id);
    }
    

//    @RequestMapping(value="/${id}",method=RequestMethod.GET)
//    public ResponseEntity<UserTPO> queryUserS(@PathVariable String id){
//    	UserTPO upo = usertService.queryById(id);
//    	HttpStatus status = upo==null?HttpStatus.NOT_FOUND:HttpStatus.OK;
//    	return new ResponseEntity<UserTPO>(upo,status);
//    }
//	
}
