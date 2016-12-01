/**
 * 项目名：student
 * 修改历史：
 * 作者： MZ
 * 创建时间： 2016年1月6日-下午4:56:01
 */
package com.up.student.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.up.student.AppConstants;
import com.up.student.DAO;
import com.up.student.base.BaseDAO;
import com.up.student.dao.StudentDAO;
import com.up.student.model.Student;

/**
 * 模块说明： 添加学生
 * 
 */
public class AddView extends JFrame {

	private static final long serialVersionUID = -1984182788841566838L;

	private JPanel jPanelCenter, jPanelSouth;
	private JButton addButton, exitButton;
	private JTextField name, sno, department, hometown, mark, email, tel, sex;

	public AddView() {
		init();
	}

	private void init() {
		setTitle(AppConstants.ADDVIEW_TITLE);
		// center panel
		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(9, 2));
		jPanelCenter.add(new JLabel(AppConstants.STUDENT_NAME));
		name = new JTextField();
		jPanelCenter.add(name);
		jPanelCenter.add(new JLabel(AppConstants.STUDENT_SNO));
		sno = new JTextField();
		jPanelCenter.add(sno);
		jPanelCenter.add(new JLabel(AppConstants.STUDENT_SEX));
		sex = new JTextField();
		jPanelCenter.add(sex);
		jPanelCenter.add(new JLabel(AppConstants.STUDENT_DEPARTMETN));
		department = new JTextField();
		jPanelCenter.add(department);
		jPanelCenter.add(new JLabel(AppConstants.STUDENT_HOMETOWN));
		hometown = new JTextField();
		jPanelCenter.add(hometown);
		jPanelCenter.add(new JLabel(AppConstants.STUDENT_MARK));
		mark = new JTextField();
		jPanelCenter.add(mark);
		jPanelCenter.add(new JLabel(AppConstants.STUDENT_EMAIL));
		email = new JTextField();
		jPanelCenter.add(email);
		jPanelCenter.add(new JLabel(AppConstants.STUDENT_TEL));
		tel = new JTextField();
		jPanelCenter.add(tel);
		jPanelCenter.add(new JLabel("-------------------------------------------------"));
		jPanelCenter.add(new JLabel("-------------------------------------------------"));

		// south panel
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		addButton = new JButton(AppConstants.ADDVIEW_ADDBUTTON);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (check()) {
					Student stu = new Student();
					buildStudent(stu);
					boolean isSuccess = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO)).add(stu);
					if (isSuccess) {
						setEmpty();
						if (MainView.currPageNum < 0 || MainView.currPageNum > 99) {
							MainView.currPageNum = 1;
						}
						String[][] result = ((StudentDAO) BaseDAO.getAbilityDAO(DAO.StudentDAO))
								.list(MainView.currPageNum);
						MainView.initJTable(MainView.jTable, result);
					}
				}
			}
		});
		jPanelSouth.add(addButton);
		exitButton = new JButton(AppConstants.EXITBUTTON);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jPanelSouth.add(exitButton);

		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelSouth, BorderLayout.SOUTH);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(470, 200, 400, 270);
		setResizable(false);
		setVisible(true);
	}

	private boolean check() {
		boolean result = false;
		if ("".equals(name.getText()) || "".equals(sno.getText()) || "".equals(department.getText())
				|| "".equals(sex.getText()) || "".equals(mark.getText()) || "".equals(tel.getText())
				|| "".equals(email.getText()) || "".equals(hometown.getText())) {
			return result;
		} else {
			result = true;
		}
		return result;
	}

	private void buildStudent(Student stu) {
		stu.setDepartment(department.getText());
		stu.setEmail(email.getText());
		stu.setHomeTown(hometown.getText());
		stu.setMark(mark.getText());
		stu.setName(name.getText());
		stu.setSno(sno.getText());
		stu.setTel(tel.getText());
		stu.setSex(sex.getText());
	}

	private void setEmpty() {
		name.setText("");
		sno.setText("");
		department.setText("");
		sex.setText("");
		email.setText("");
		hometown.setText("");
		tel.setText("");
		mark.setText("");
	}
}
