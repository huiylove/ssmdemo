package com.huiy.ssm.demo.front.service.impl;

import org.springframework.stereotype.Component;

import com.huiy.ssm.demo.front.service.EncryptService;
import com.huiy.ssm.demo.util.FrontMD5Util;

@Component("encryptService")
public class EncryptServiceImpl implements EncryptService {

	@Override
	public String frontMd5(String res) {
		return FrontMD5Util.MD5Encode(res);
	}

	@Override
	public String frontMd5(String res, String charset) {
		return FrontMD5Util.MD5Encode(res, charset);
	}
    
}
