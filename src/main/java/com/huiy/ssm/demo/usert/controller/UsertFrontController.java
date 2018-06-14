package com.huiy.ssm.demo.usert.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.huiy.ssm.demo.front.service.ConvertorService;
import com.huiy.ssm.demo.front.service.EncryptService;
import com.huiy.ssm.demo.front.xmlModelMappding.request.UserReqMapping;
import com.huiy.ssm.demo.front.xmlModelMappding.response.UserResMapping;
import com.huiy.ssm.demo.persist.po.UserTPO;
import com.huiy.ssm.demo.usert.service.UserTService;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2016年12月13日
 * @version 1.0
 *
 */


@Controller  
@RequestMapping("/user")  
public class UsertFrontController {  
	
    @Resource  
    private UserTService usertService;  
    
    @Resource(name = "convertorService")
	private ConvertorService ConvertorService;
    

	@Resource(name="encryptService")
	private EncryptService encryptService;
      
    @RequestMapping("/userInfo")  
    public void toIndex(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="reqData", required=true) String reqData){  
    	try {
    		
	    	if (StringUtils.isBlank(reqData)) {
				//接收报文异常，返回失败
				responseTo(response, "0001", "接收报文异常");
			}
			
//			//验证加密是否正确
//			StringBuilder md5Sb = new StringBuilder();
//			md5Sb.append(reqData).append("|").append("192.168.42.25").append("|").append("11111");
//			String cryptStr = md5Sb.toString();
//			String encodedStr = encryptService.frontMd5(cryptStr,"UTF-8");
//			if(!encodedStr.equals(md5)){
//				//报文加密错误，返回失败
//				responseTo(response, "0002", "报文加密错误");
//			}
			
//			//把报文转换成对象
//	    	UserReqMapping userrm = new UserReqMapping();
//	    	userrm.setAction("user_detail");
//	    	userrm.setId("1");
//	        String reqD = ConvertorService.convertToXml(userrm); 
//			write(response,reqD);
		    UserReqMapping user = ConvertorService.converyToJavaBean(reqData, UserReqMapping.class);
		    String action = user.getAction();
		    if("user_detail".equals(action)){
		    	UserTPO userpo = this.usertService.queryById(user.getId());
				UserResMapping resMapping = ConvertorService.copyProperties(userpo, UserResMapping.class);
				resMapping.setRcd("0000");
				resMapping.setrDesc("用户信息查询成功");
				String resXml = ConvertorService.convertToXml(resMapping);
				write(response,resXml);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
    }  
    
    @RequestMapping("/queryUser")  
    public ModelAndView queryUser(HttpServletRequest request,@ModelAttribute("userTPO")UserTPO po){ 
    	ModelAndView mv = new ModelAndView("/usert/userView");
		try {
			UserTPO utp = usertService.findUserBy(po);
			mv.addObject("user", utp);
		} catch (Exception e) {
			e.printStackTrace();
		}  
        return mv;  
    }
     
    public void responseTo(HttpServletResponse response,String rcd,String rDesc){
    	String resp = constructRsp(rcd, rDesc);
		try {
			write(response,resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    private String constructRsp(String rcd, String rDesc) {
		UserResMapping resMapping = new UserResMapping();
		resMapping.setRcd(rcd);
		resMapping.setrDesc(rDesc);
		String resXml = ConvertorService.convertToXml(resMapping);
		return resXml;
	}

    
    protected void write(HttpServletResponse response,String message) throws Exception {
    	
    	//响应正文的MIME类型和字符编码
//		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getOutputStream().write(message.getBytes("UTF-8"));
//		response.getWriter().write(message);
//		response.getWriter().close();
		response.getOutputStream().flush();
	}
    
}  