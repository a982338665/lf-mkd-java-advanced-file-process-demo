package pers.li.web;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pers.li.util.BarCodeUtils;
import pers.li.util.QRCodeUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : 皇夜_
 * @URL : CSDN 皇夜_
 * @CreateTime : 2024-3-19 17:47:50
 * @Description : 订单表
 * @TableName ：t_s_order
 */
@RestController
@RequestMapping("/barQR")
public class BarQRCodeController {

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public void saveOrModify(String content, int type, int length, int width, HttpServletResponse response) {
		if (type == 1) {
			//一维码
			length = (length == 0 ? 400 : length);
			width = (width == 0 ? 100 : width);
			try {
				BarCodeUtils.generateCode(content, response, length, width);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			//二维码
			length = (length == 0 ? 500 : length);
			width = (width == 0 ? 500 : width);
			try {
				QRCodeUtils.generateQRCode(content, response, length, width);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
