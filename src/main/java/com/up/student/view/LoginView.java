/**
 * 项目名：student
 * 修改历史：
 * 作者： MZ
 * 创建时间： 2016年1月6日-上午9:43:48
 */
package com.up.student.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.up.student.AppConstants;
import com.up.student.DAO;
import com.up.student.base.BaseDAO;
import com.up.student.dao.AdminDAO;

/**
 * 模块说明： 登录界面
 * 
 */
public class LoginView extends JFrame {

	private static final long serialVersionUID = -5278598737087831336L;
	private JPanel jPanelCenter, jPanelSouth;
	private JTextField username;
	private JPasswordField password;
	private JButton loginButton, resetButton;

	public LoginView() {
		init();
	}

	private void init() {
		this.setTitle("Login");

		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(3, 2));
		jPanelCenter.add(new JLabel(AppConstants.LOGIN_USERNAME));
		username = new JTextField();
		jPanelCenter.add(username);
		jPanelCenter.add(new JLabel(AppConstants.LOGIN_PASSWORD));
		password = new JPasswordField();
		// enter key listener
		password.addKeyListener(new LoginListener());
		jPanelCenter.add(password);
		jPanelCenter.add(new JLabel("----------------------------------------------"));
		jPanelCenter.add(new JLabel("----------------------------------------------"));

		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		loginButton = new JButton(AppConstants.LOGIN);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				check();
			}
		});
		jPanelSouth.add(loginButton);
		resetButton = new JButton(AppConstants.RESET);
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				username.setText("");
				password.setText("");
			}
		});
		jPanelSouth.add(resetButton);

		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelSouth, BorderLayout.SOUTH);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(450, 250, 375, 140);
		this.setResizable(false);
		this.setVisible(true);
	}

	private class LoginListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				check();
			}
		}
	}

	private void check() {
		AdminDAO adminDAO = (AdminDAO) BaseDAO.getAbilityDAO(DAO.AdminDAO);
		if (adminDAO.queryForLogin(username.getText(), String.valueOf(password.getPassword()))) {
			dispose();
			new MainView();
		} else {
			username.setText("");
			password.setText("");
		}
	}

}
