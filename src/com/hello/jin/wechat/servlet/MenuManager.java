package com.hello.jin.wechat.servlet;

import com.hello.jin.wechat.pojo.AccessToken;
import com.hello.jin.wechat.pojo.Button;
import com.hello.jin.wechat.pojo.CommonButton;
import com.hello.jin.wechat.pojo.ComplexButton;
import com.hello.jin.wechat.pojo.Menu;
import com.hello.jin.wechat.pojo.ViewButton;
import com.hello.jin.wechat.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 菜单管理器类
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wx8b2f12a8d6b0504e";
		// 第三方用户唯一凭证密钥
		String appSecret = "68fb1e674ff82c2ba49115a741d4a9a3";

		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());

			// 判断菜单创建结果
			if (0 == result)
			{
				log.info("菜单创建成功！");
			    System.out.println("okqqq");
			}
			else
				log.info("菜单创建失败，错误码：" + result);
		}
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("心日记");
		btn11.setType("click");
		btn11.setKey("11");


		ViewButton btn21 = new ViewButton();
		btn21.setName("我的清单");
		btn21.setType("click");
		btn21.setUrl("http://www.baidu.com"); 


		ViewButton btn31 = new ViewButton();
		btn31.setName("求监督");
		btn31.setType("click");
		btn31.setUrl("http://www.baidu.com"); 
		
		ViewButton btn32 = new ViewButton();
		btn31.setName("抱大腿");
		btn31.setType("click");
		btn31.setUrl("http://www.baidu.com"); 

		
		ViewButton btn33 = new ViewButton();  
		btn33.setName("广场");  
		btn33.setType("view");  
		btn33.setUrl("http://www.baidu.com");  
        
		/**
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("生活助手");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13, btn14 });
		*/

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("发现");
		mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33});

		/**
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { btn11, btn21, mainBtn3 });

		return menu;
	}
}