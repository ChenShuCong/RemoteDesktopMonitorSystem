package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

import service.LoginService;

/*
 * �û���¼����
 * @author Kami
 */
public class LoginUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JLabel imageLogo;
	private JTextField username;
	private JPasswordField password;
	private JRadioButton user;
	private JRadioButton admin;
	private JCheckBox rememberPass;
	private JCheckBox autoLogin;
	private JButton loginButton;
	private JButton resetButton;
	
	//���ҵ���߼�ʵ��
	LoginService service = new LoginService();
	
	public LoginUI() {
		initUI();
	}


	private void initUI() {
		//���ñ���
		this.setTitle("��¼����");
		
		//����LOGO
		URL image = LoginUI.class.getClassLoader().getResource("header.jpg");
		imageLogo = new JLabel(new ImageIcon(image));
		this.add(imageLogo, BorderLayout.NORTH);
		
		//����ͷ��
		URL image2 = LoginUI.class.getClassLoader().getResource("icon.jpg");
		JLabel icon = new JLabel(new ImageIcon(image2));
		icon.setBounds(55, 100, 70, 70);
		this.add(icon);
		
		//�û���������  
        JPanel jp = new JPanel();  
      
        JPanel jpAccount = new JPanel();  
        jpAccount.add(new JLabel("                �ʺ�"));  
        username = new JTextField(12);  
        jpAccount.add(username);  
        jp.add(jpAccount);  
        jp.add(Box.createHorizontalStrut(1000)); 
        JPanel jpPass = new JPanel();  
        jpPass.add(new JLabel("                ����"));  
        password = new JPasswordField(12);  
        jpPass.add(password);  
        jp.add(jpPass);  
        
        jp.add(Box.createHorizontalStrut(1000));
        
        //��¼�û�����
        JPanel jpType = new JPanel();
        user = new JRadioButton("�û�");
        admin = new JRadioButton("����Ա");
        user.setSelected(true);
        jpType.add(user);
        jpType.add(admin);
        jp.add(jpType);
        
        
        //��¼����  
        JPanel jpstatus = new JPanel(); 
        rememberPass = new JCheckBox("��ס����");
        rememberPass.setSelected(true);
        autoLogin = new JCheckBox("�Զ���¼");
        jpstatus.add(rememberPass);  
        jpstatus.add(autoLogin);  
        jp.add(jpstatus);  
        
        //������ɫ
        jpAccount.setBackground(new Color(255, 255, 220));
        jpPass.setBackground(new Color(255, 255, 220));
        jpType.setBackground(new Color(255, 255, 220));
        jpstatus.setBackground(new Color(255, 255, 220));
        jp.setBackground(new Color(255, 255, 220));
        

        //��¼��ť  
        loginButton = new JButton("��¼");
        resetButton = new JButton("����");
        jp.add(Box.createHorizontalStrut(1000));
        jp.add(loginButton);
        jp.add(resetButton);
       
        this.add(jp);  
     
        //��Ӽ�����
        loginButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		//�ж��û����������Ƿ�ƥ��
        		if(service.checkUser(username, password, user)) {
        			//��¼�ɹ������Ƿ�ѡ��ס����
        			service.rememberPass(username, password, rememberPass);
        			
        			//��¼�ɹ������Ƿ�ѡ�Զ���¼
        			service.autoLogin(autoLogin);
        			
        			//��¼�ɹ��رյ�¼����
        			setVisible(false);
        		}
        	}
        });
        
        resetButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		//����û���������
        		service.clear(username, password);
        	}
        });
        
        //��ѡ���ѡ��
        user.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		user.setSelected(true);
        		admin.setSelected(false);
        	}
        });
        
        //��ѡ���ѡ��
        admin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		user.setSelected(false);
        		admin.setSelected(true);
        	}
        });
        
        //���ô�������
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 290);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
       
        //�ж��ϴε�¼�Ƿ��ס�����ѡ���Զ���¼
        if(!service.lastRememberPass(username, password, autoLogin, user)) {
        	this.setVisible(true);
        }
        
    }  
	
	
	public static void main(String[] args) {
		new LoginUI();
	}
	
}
