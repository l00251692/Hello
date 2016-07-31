package com.hello.jin.wechat.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hello.jin.wechat.message.MessageUtil;
import com.hello.jin.wechat.message.TextMessage;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * Created by xdp on 2016/1/25.
 * ʹ��@WebServletע������WxServlet,urlPatterns����ָ����WxServlet�ķ���·��
 */
@WebServlet(urlPatterns="/HelloJin")
public class HelloJin extends HttpServlet {

    /**
     * Token���ɿ����߿���������д����������ǩ������Token��ͽӿ�URL�а�����Token���бȶԣ��Ӷ���֤��ȫ�ԣ�
     * ���������ҽ�Token����Ϊgacl
     */
    private final String TOKEN = "gacl";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8"); 
        // ���ú���ҵ���������Ϣ��������Ϣ  
        String respMessage = processRequest(request);  
          
        // ��Ӧ��Ϣ  
        PrintWriter out = response.getWriter();  
        out.print(respMessage);
        out.close();  
        String[] args = null;
		//MenuManager.main(args);
		//System.out.println("OKKKKK");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("��ʼУ��ǩ��");
        /**
         * ����΢�ŷ�������������ʱ���ݹ�����4������
         */
        String signature = request.getParameter("signature");//΢�ż���ǩ��signature����˿�������д��token�����������е�timestamp������nonce������
        String timestamp = request.getParameter("timestamp");//ʱ���
        String nonce = request.getParameter("nonce");//�����
        String echostr = request.getParameter("echostr");//����ַ���
        //����
        String sortString = sort(TOKEN, timestamp, nonce);
        //����
        String mySignature = sha1(sortString);
        //У��ǩ��
        if (mySignature != null && mySignature != "" && mySignature.equals(signature)) {
            System.out.println("ǩ��У��ͨ����");
            //�������ɹ����echostr��΢�ŷ��������յ���������Ż�ȷ�ϼ�����ɡ�
            //response.getWriter().println(echostr);
            response.getWriter().write(echostr);
        } else {
            System.out.println("ǩ��У��ʧ��.");
        }

    }

    /**
     * ���򷽷�
     *
     * @param token
     * @param timestamp
     * @param nonce
     * @return
     */
    public String sort(String token, String timestamp, String nonce) {
        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str);
        }

        return sb.toString();
    }

    /**
     * ���ַ�������sha1����
     *
     * @param str ��Ҫ���ܵ��ַ���
     * @return ���ܺ������
     */
    public String sha1(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // �ֽ�����ת��Ϊ ʮ������ ��
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    
    public static String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try {
            // Ĭ�Ϸ��ص��ı���Ϣ����
            String respContent = "�������쳣�����Ժ��ԣ�";

            // xml�������
            Map<String, String> requestMap = MessageUtil.parseXml(request);

            // ���ͷ��ʺţ�open_id��
            String fromUserName = requestMap.get("FromUserName");
            // �����ʺ�
            String toUserName = requestMap.get("ToUserName");
            // ��Ϣ����
            String msgType = requestMap.get("MsgType");

            // �ظ��ı���Ϣ
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);

            // �ı���Ϣ
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                respContent = "�����͵����ı���Ϣ��";
            }
            // ͼƬ��Ϣ
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "�����͵���ͼƬ��Ϣ��";
            }
            // ����λ����Ϣ
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "�����͵��ǵ���λ����Ϣ��";
            }
            // ������Ϣ
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "�����͵���������Ϣ��";
            }
            // ��Ƶ��Ϣ
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "�����͵�����Ƶ��Ϣ��";
            }
            // �¼�����
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // �¼�����
                String eventType = requestMap.get("Event");
                // ����
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "��л���Ĺ�ע��";
                }
                // ȡ������
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ
                }
                // �Զ���˵�����¼�
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                	// �¼�KEYֵ���봴���Զ���˵�ʱָ����KEYֵ��Ӧ
                	String eventKey = requestMap.get("EventKey");

                	if (eventKey.equals("11")) {
                		respContent = "11�������";
                	} else if (eventKey.equals("21")) {
                		respContent = "21�������";
                	} else if (eventKey.equals("31")) {
                		respContent = "31�������";
                	} else if (eventKey.equals("32")) {
                		respContent = "32�������";
                	} else if (eventKey.equals("33")) {
                		respContent = "33�������";
                	}

                }
            }

            textMessage.setContent(respContent);
            respMessage = MessageUtil.textMessageToXml(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return respMessage;
    }
}