package com.huiy.ssm.demo.front.service;

/**
 * java支持的加密解密
 * <br>
 * 单向加密：MD5、SHA1
 * <br>
 * 双向加密：DES、AES
 * 
 * @author linin
 *
 */
public interface EncryptService {

    public String frontMd5(String res);
    
    public String frontMd5(String res, String charset); 
    
}
