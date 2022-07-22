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

public class LoginPage extends JPanel{

	JLabel username, password, loginTitle, loginLabel;
	JButton loginButton, backButton;
	JTextField tusername;
	JPasswordField tpassword;
	boolean loginError = false;
	String errorText, dataUser;
	File database;
	Scanner databaseR;


	public LoginPage(){

		//LoginPage Panel settings
        this.setBounds(0,0,920,1080);
        this.setBackground(new Color(85,116,166));
        this.setLayout(null);
        this.setVisible(true);
        this.setFocusable(true);

        //LoginTitle
        loginTitle = new JLabel("Login Form");
        loginTitle.setFont(new Font("Arial", Font.BOLD, 40));
        loginTitle.setBounds(330,200,450,45);
        loginTitle.setVisible(true);
		
		//username label and textfield
    	username = new JLabel("Username");
        username.setFont(new Font("Arial", Font.BOLD, 20));
        username.setVisible(true);
		username.setBounds(290,420,150,20);
        tusername = new JTextField();
        tusername.setFont(new Font("Arial", Font.BOLD, 15));
        tusername.setVisible(true);
        tusername.setBounds(440,420,190,20);

        //Password label and field
        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.BOLD, 20));
        password.setVisible(true);
        password.setBounds(290,500,150,20);
        tpassword = new JPasswordField();
        tpassword.setFont(new Font("Arial", Font.BOLD, 15));
        tpassword.setVisible(true);
        tpassword.setBounds(440,500,190,20);

        //Labels
       	loginLabel = new JLabel();
       	loginLabel.setBounds(290,550,300,20);
       	loginLabel.setBackground(new Color(85,116,166));
       	loginLabel.setForeground(Color.red);
       	loginLabel.setVisible(false);

        //BackButton
        backButton = new JButton("Return Main Page");  
		backButton.setBounds(345,715,230,50);
        backButton.setFocusable(false);
        backButton.setVisible(true);
        backButton.setBackground(new Color(246,190,0));

		//LoginButton
		loginButton = new JButton("Login");
		loginButton.setBounds(345,650,230,50);
		loginButton.setFocusable(false);
		loginButton.setVisible(true);
		loginButton.setBackground(new Color(246,190,0));

        this.add(loginLabel);this.add(loginTitle);
		this.add(username);this.add(tusername);
		this.add(password);this.add(tpassword);
		this.add(loginButton);this.add(backButton);
	}


	public boolean loginValidate(){
		loginLabel.setVisible(false);

		try{
			database = new File("database.txt");
			databaseR = new Scanner(database);
			while(databaseR.hasNextLine()){
				String dataR = databaseR.nextLine();
				String[] dataE = dataR.split("\\s");
				String[] loginData = {tusername.getText(), new String(tpassword.getPassword())};
				dataUser = dataE[1];
				if(dataE[1].equals(loginData[0]) && dataE[2].equals(loginData[1])){
					loginError = false;
					return loginError;
				}
			}
			errorText = "*Username or Password is not correct";
			loginLabel.setText(errorText);
			loginLabel.setVisible(true);
			loginError = true;
		}
		catch (Exception e){}
		return loginError;
	}

    public void loginPageVisibility(boolean x){
        this.setVisible(x);
    }
}