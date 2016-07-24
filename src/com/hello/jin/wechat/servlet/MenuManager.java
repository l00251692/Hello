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
		btn11.setName("����Ԥ��");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn12 = new CommonButton();
		btn12.setName("������ѯ");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("�ܱ�����");
		btn13.setType("click");
		btn13.setKey("13");

		CommonButton btn14 = new CommonButton();
		btn14.setName("��ʷ�ϵĽ���");
		btn14.setType("click");
		btn14.setKey("14");

		CommonButton btn21 = new CommonButton();
		btn21.setName("�����㲥");
		btn21.setType("click");
		btn21.setKey("21");

		CommonButton btn22 = new CommonButton();
		btn22.setName("������Ϸ");
		btn22.setType("click");
		btn22.setKey("22");

		CommonButton btn23 = new CommonButton();
		btn23.setName("��Ů��̨");
		btn23.setType("click");
		btn23.setKey("23");

		CommonButton btn24 = new CommonButton();
		btn24.setName("����ʶ��");
		btn24.setType("click");
		btn24.setKey("24");

		CommonButton btn25 = new CommonButton();
		btn25.setName("�������");
		btn25.setType("click");
		btn25.setKey("25");

		CommonButton btn31 = new CommonButton();
		btn31.setName("Q��Ȧ");
		btn31.setType("click");
		btn31.setKey("31");

		CommonButton btn32 = new CommonButton();
		btn32.setName("��Ӱ���а�");
		btn32.setType("click");
		btn32.setKey("32");

		CommonButton btn33 = new CommonButton();
		btn33.setName("��ĬЦ��");
		btn33.setType("click");
		btn33.setKey("33");
		
		ViewButton btn34 = new ViewButton();  
		btn34.setName("ʹ�ð���");  
		btn34.setType("view");  
		btn34.setUrl("http://www.baidu.com");  
        
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("��������");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13, btn14 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("������վ");
		mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23, btn24, btn25 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("��������");
		mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33 ,btn34});

		/**
		 * ���ǹ��ں�xiaoqrobotĿǰ�Ĳ˵��ṹ��ÿ��һ���˵����ж����˵���<br>
		 * 
		 * ��ĳ��һ���˵���û�ж����˵��������menu����ζ����أ�<br>
		 * ���磬������һ���˵���ǡ��������顱����ֱ���ǡ���ĬЦ��������ômenuӦ���������壺<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}
}