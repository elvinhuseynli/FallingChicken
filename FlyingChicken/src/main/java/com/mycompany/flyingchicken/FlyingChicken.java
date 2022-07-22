/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.flyingchicken;

import java.io.*;
import java.time.*;
import java.lang.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author elvin
 */

public class FlyingChicken extends JFrame implements ActionListener{

	JButton firstLoginButton, firstRegisterButton, firstQuitButton;
	RegisterPage registerPage;
	LoginPage loginPage;
	StartPage startPage;
	private static final long serialVersionUID = 5462223600l;


	public FlyingChicken(){

		//FirstRegisterButton
		firstRegisterButton = new JButton("Register Account");
		firstRegisterButton.setBounds(360,445,200,50);
		firstRegisterButton.setFocusable(false);
		firstRegisterButton.addActionListener(this);
		firstRegisterButton.setBackground(Color.green);
		firstRegisterButton.setForeground(Color.white);

		//FirstLoginButton
		firstLoginButton = new JButton("Login Account");
		firstLoginButton.setBounds(360,520,200,50);
		firstLoginButton.setFocusable(false);
		firstLoginButton.addActionListener(this);
		firstLoginButton.setBackground(Color.green);
		firstLoginButton.setForeground(Color.white);

		//FirstQuitButton
		firstQuitButton = new JButton("Quit the Game");
		firstQuitButton.setBounds(385,595,150,50);
		firstQuitButton.setFocusable(false);
		firstQuitButton.addActionListener(this);
		firstQuitButton.setBackground(Color.green);
		firstQuitButton.setForeground(Color.white);

		loginPage = new LoginPage();
		registerPage = new RegisterPage();
		startPage = new StartPage();

		loginPage.loginPageVisibility(false);
		registerPage.registerPageVisibility(false);
		startPage.startPageVisibility(false);
		startPage.gamePage.gamePageVisibility(false);
		startPage.shopPage.shopPageVisibility(false);

        loginPage.backButton.addActionListener(this);
		loginPage.loginButton.addActionListener(this);
        registerPage.registerButton.addActionListener(this);
        registerPage.backButton.addActionListener(this);

		//Frame Settings
		Image img = new ImageIcon("Pictures/Flying_Chicken_Icon.png").getImage();
		this.setIconImage(img);
    	this.setSize(920,1080);
    	this.setTitle("Flying Chicken");
		this.getContentPane().setBackground(new Color(14, 194, 176));
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setResizable(false);
    	this.setFocusable(true);
    	this.setLayout(null);
    	this.setVisible(true);
    	this.add(loginPage);this.add(registerPage);
    	this.add(startPage);this.add(startPage.shopPage);
    	this.add(firstQuitButton);this.add(firstRegisterButton);
    	this.add(firstLoginButton);this.add(startPage.gamePage);

	}

	public void mainPanel(){
		this.getContentPane().setBackground(new Color(14, 194, 176));
		firstRegisterButton.setVisible(true);
		firstQuitButton.setVisible(true);	
		firstLoginButton.setVisible(true);
		loginPage.loginPageVisibility(false);
		registerPage.registerPageVisibility(false);
		loginPage.loginLabel.setVisible(false);
		registerPage.label1.setVisible(false);
		registerPage.label2.setVisible(false);
		registerPage.label3.setVisible(false);
		registerPage.label4.setVisible(false);
		registerPage.label5.setVisible(false);	
	}

	public void loginPanel(){

		loginPage.tpassword.setText("");
		loginPage.tusername.setText("");
		firstRegisterButton.setVisible(false);
		firstQuitButton.setVisible(false);	
		firstLoginButton.setVisible(false);
		loginPage.loginPageVisibility(true);
		registerPage.registerPageVisibility(false);
		
	}

	public void registerPanel(){

		loginPage.loginPageVisibility(false);
		registerPage.registerPageVisibility(true);
		registerPage.tpassword.setText("");
		registerPage.tusername.setText("");	
		firstRegisterButton.setVisible(false);
		firstQuitButton.setVisible(false);	
		firstLoginButton.setVisible(false);
	}

	public void startPage(){

		this.getContentPane().setBackground(new Color(128,198,209));
		loginPage.loginPageVisibility(false);
		startPage.startPageVisibility(true);
	}

	public void quitFunction(){
		String[] resp = {"Resume", "Quit"};
		int k = JOptionPane.showOptionDialog(this, "Are you sure you want to quit?","Quit Panel",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, null, resp, 0);
		if (k==1){
			this.setVisible(false);
			this.dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e){
		// if(e.getSource() == gamePage.quitButton){
		// 	quitFunction();
		// }
		if(e.getSource() == firstQuitButton){
			quitFunction();
		}
		if(e.getSource() == firstRegisterButton){
			registerPanel();
		}
		if(e.getSource() == firstLoginButton){
			loginPanel();
		}
		if(e.getSource() == registerPage.registerButton){
			if(registerPage.registerValidate() == false){
				registerPage.registerData();
				loginPanel();
			}
		}
		if(e.getSource() == loginPage.loginButton){
			if(loginPage.loginValidate() == false){
				startPage();
				startPage.setLoginData(loginPage.dataUser);
				startPage.checkMoney();
			}
		}
		if(e.getSource() == loginPage.backButton || e.getSource() == registerPage.backButton){
			mainPanel();
		}
	}

	public static void main(String[] args){

		FlyingChicken game = new FlyingChicken();
	}
}
