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
 * �˵���������
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	public static void main(String[] args) {
		// �������û�Ψһƾ֤
		String appId = "wx8b2f12a8d6b0504e";
		// �������û�Ψһƾ֤��Կ
		String appSecret = "68fb1e674ff82c2ba49115a741d4a9a3";

		// ���ýӿڻ�ȡaccess_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// ���ýӿڴ����˵�
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());

			// �жϲ˵��������
			if (0 == result)
			{
				log.info("�˵������ɹ���");
			    System.out.println("okqqq");
			}
			else
				log.info("�˵�����ʧ�ܣ������룺" + result);
		}
	}

	/**
	 * ��װ�˵�����
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("���ռ�");
		btn11.setType("click");
		btn11.setKey("11");


		ViewButton btn21 = new ViewButton();
		btn21.setName("�ҵ��嵥");
		btn21.setType("click");
		btn21.setUrl("http://www.baidu.com"); 


		ViewButton btn31 = new ViewButton();
		btn31.setName("��ල");
		btn31.setType("click");
		btn31.setUrl("http://www.baidu.com"); 
		
		ViewButton btn32 = new ViewButton();
		btn31.setName("������");
		btn31.setType("click");
		btn31.setUrl("http://www.baidu.com"); 

		
		ViewButton btn33 = new ViewButton();  
		btn33.setName("�㳡");  
		btn33.setType("view");  
		btn33.setUrl("http://www.baidu.com");  
        
		/**
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("��������");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13, btn14 });
		*/

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("����");
		mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33});

		/**
		 * ���ǹ��ں�xiaoqrobotĿǰ�Ĳ˵��ṹ��ÿ��һ���˵����ж����˵���<br>
		 * 
		 * ��ĳ��һ���˵���û�ж����˵��������menu����ζ����أ�<br>
		 * ���磬������һ���˵���ǡ��������顱����ֱ���ǡ���ĬЦ��������ômenuӦ���������壺<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { btn11, btn21, mainBtn3 });

		return menu;
	}
}