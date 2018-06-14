package com.huiy.ssm.demo.javamail;

import java.util.List;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2017年8月2日
 * @version 1.0
 *
 *
 */
public class MailServiceImpl {
	
	private Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

	@Resource(name = "mailSender")
	public JavaMailSender mailSender;
	
	@Resource(name = "taskExecutor")
	public TaskExecutor taskExecutor;// 注入Spring封装的异步执行器  
	
	
	public void sendMail(List<EmailVO> email) throws Exception {  
	        if (email.size() > 5) {// 收件人大于5封时，采用异步发送 
	        	for(EmailVO mail:email){
	        		sendMailByAsynchronousMode(mail); 
	        	}
	         } else {
	        	 for(EmailVO mail:email){
	        		 sendMailBySynchronizationMode(mail); 
		        }
	        }  
	} 
	
	
	public void sendMailByAsynchronousMode(final EmailVO email) {  
        taskExecutor.execute(new Runnable() {  
            public void run() {  
                try {  
                    sendMailBySynchronizationMode(email);  
                } catch (Exception e) {  
                    log.error("MailService",e);  
                }  
            }  
        });  
	}
	
	public void sendMailBySynchronizationMode(EmailVO email)  
            throws Exception {  
	MimeMessage mime = mailSender.createMimeMessage(); 
    MimeMessageHelper helper = new MimeMessageHelper(mime, true, "utf-8");  
    helper.setFrom(email.getFromAddress());// 发件人  
    helper.setTo(email.getToAddress());// 收件人  
    helper.setReplyTo(email.getFromAddress());// 回复到  
    if(email.getCcList() != null && email.getCcList().length>0){
        helper.setCc(email.getCcList());//抄送
    }
    helper.setSubject(email.getSubject());// 邮件主题  
    helper.setText(email.getContent(), true);// true表示设定html格式  
    mailSender.send(mime);  
  } 

}
