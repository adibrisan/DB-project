import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import java.awt.Component;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;

public class LoginBox extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginBox frame = new LoginBox();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public LoginBox() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 558);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel close = new JLabel("X");
		close.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		close.setForeground(new Color(0, 0, 0));
		close.setHorizontalAlignment(SwingConstants.CENTER);
		close.setFont(new Font("Tahoma", Font.BOLD, 20));
		close.setBounds(0, 0, 74, 48);
		contentPane.add(close);
		
		user = new JTextField();
		user.setBounds(146, 244, 181, 32);
		contentPane.add(user);
		user.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = user.getText();
				String p = pass.getText();
				tabel t = new tabel();
				
				if(id.equals("admin") && p.equals("admin")) {
					JOptionPane.showMessageDialog(null,"you are sucessfully logged!");
					t.setVisible(true);
					dispose();
				}
				else
					JOptionPane.showMessageDialog(null,"invalid username or password.","Message" ,JOptionPane.OK_OPTION);
				
			}

			
		});
		btnNewButton.setBackground(new Color(245, 245, 220));
		btnNewButton.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, UIManager.getColor("CheckBox.foreground")));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(185, 380, 97, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginBox.class.getResource("/login.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(67, 244, 94, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(LoginBox.class.getResource("/password.png")));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(56, 320, 109, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 46));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(135, 0, 222, 66);
		contentPane.add(lblNewLabel_2);
		
		JLabel circle = new JLabel("");
		circle.setIcon(new ImageIcon(LoginBox.class.getResource("/car.png")));
		circle.setBounds(174, 94, 267, 137);
		contentPane.add(circle);
		
		pass = new JPasswordField();
		pass.setBounds(146, 320, 181, 32);
		contentPane.add(pass);
		
		
		
		setUndecorated(true);
	}
}
