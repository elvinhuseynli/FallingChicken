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

public class RegisterPage extends JPanel{

	JLabel name, username, age, gender, registerTitle, password;
    JButton registerButton, backButton;
    JLabel label1, label2, label3, label4, label5;
	JTextField tname, tusername, tage;
	JPasswordField tpassword;
	JRadioButton male, female;
	JCheckBox term;
	ButtonGroup group;
    File database; Scanner databaseR;
    FileWriter databaseW;
    boolean registerError = false;
	String errorText1, errorText2, errorText3, errorText4, errorText5;

    
	public RegisterPage(){

        //RegisterPage Panel settings
        this.setBounds(0,0,920,1080);
        this.setBackground(new Color(85,116,166));
        this.setLayout(null);
        this.setVisible(true);
        this.setFocusable(true);

		//Title for Reg. Form
		registerTitle = new JLabel("Registration Form");
        registerTitle.setFont(new Font("Arial", Font.BOLD, 40));
        registerTitle.setBounds(270,95,450,45);
        registerTitle.setVisible(true);

    	//Name label and textfield
    	name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.BOLD, 20));
        name.setBounds(290,200,150,20);
        name.setVisible(true);
        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.BOLD, 15));
        tname.setBounds(440,200,190,20);
        tname.setVisible(true);

		//username label and textfield
    	username = new JLabel("Username");
        username.setFont(new Font("Arial", Font.BOLD, 20));
        username.setVisible(true);
        username.setBounds(290,250,150,20);
        tusername = new JTextField();
        tusername.setFont(new Font("Arial", Font.BOLD, 15));
        tusername.setVisible(true);
        tusername.setBounds(440,250,190,20);

		//Gender Selection
		gender = new JLabel("Gender");
        gender.setFont(new Font("Arial", Font.BOLD, 20));
        gender.setBounds(290,300,150,20);
        gender.setVisible(true);
        male = new JRadioButton("Male");
        male.setFont(new Font("Arial", Font.BOLD, 15));
        male.setSelected(true);
        male.setBounds(440,300,70,20);
        male.setBackground(new Color(85,116,166));
        male.setVisible(true);
        male.setFocusable(false);
        female = new JRadioButton("Female");
        female.setFont(new Font("Arial", Font.BOLD, 15));
        female.setSelected(false);
        female.setBounds(530,300,120,20);
        female.setBackground(new Color(85,116,166));
        female.setVisible(true);
        female.setFocusable(false);
        group = new ButtonGroup();
        group.add(male);
        group.add(female);

        //Age label and textfield
        age = new JLabel("Age");
        age.setFont(new Font("Arial", Font.BOLD, 20));
        age.setBounds(290,350,150,20);
        age.setVisible(true);
        tage = new JTextField();
        tage.setFont(new Font("Arial", Font.BOLD, 15));
        tage.setBounds(440,350,190,20);
        tage.setVisible(true);

        //Password label and field
        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.BOLD, 20));
        password.setVisible(true);
        password.setBounds(290,400,150,20);
        tpassword = new JPasswordField();
        tpassword.setFont(new Font("Arial", Font.BOLD, 15));
        tpassword.setVisible(true);
        tpassword.setBounds(440,400,190,20);        

        //Terms
        term = new JCheckBox("Accept terms and conditions");
        term.setFont(new Font("Arial", Font.BOLD, 20));
        term.setBounds(290,545,450,25);
        term.setBackground(new Color(85,116,166));
        term.setFocusable(false);
        term.setVisible(true);

       	//Labels
       	label1 = new JLabel();
       	label1.setBounds(630,200,270,15);
       	label1.setBackground(new Color(85,116,166));
       	label1.setForeground(Color.red);
       	label1.setVisible(false);
       	label2 = new JLabel();
       	label2.setBounds(630,250,270,15);
       	label2.setBackground(new Color(85,116,166));
       	label2.setForeground(Color.red);
       	label2.setVisible(false);
       	label3 = new JLabel();
       	label3.setBounds(630,350,270,15);
       	label3.setBackground(new Color(85,116,166));
       	label3.setForeground(Color.red);
       	label3.setVisible(false);
       	label4 = new JLabel();
       	label4.setBounds(630,400,270,15);
       	label4.setBackground(new Color(85,116,166));
       	label4.setForeground(Color.red);
       	label4.setVisible(false);
       	label5 = new JLabel();
       	label5.setBounds(290,580,450,15);
       	label5.setBackground(new Color(85,116,166));
       	label5.setForeground(Color.red);
       	label5.setVisible(false);

        //RegisterButton
        registerButton = new JButton("Complete Registration");
        registerButton.setBounds(345,815,230,50);
        registerButton.setFocusable(false);
        registerButton.setVisible(true);
        registerButton.setBackground(new Color(246,190,0));

        //BackButton
        backButton = new JButton("Return Main Page");       
        backButton.setBounds(345,880,230,50);
        backButton.setFocusable(false);
        backButton.setVisible(true);
        backButton.setBackground(new Color(246,190,0));

        //Adding labels
       	this.add(registerTitle);this.add(name);this.add(tname);
		this.add(username);this.add(tusername);this.add(gender);this.add(age);
		this.add(tage);this.add(male);this.add(female);this.add(term);
		this.add(password);this.add(tpassword);this.add(label1);this.add(label2);
		this.add(label3);this.add(label4);this.add(label5);
        this.add(registerButton);this.add(backButton);

	}

    //The method to add registered data to database
    public void registerData(){
        String result = (male.isSelected()==true) ? "male" : "female";
        try{
            databaseW = new FileWriter("database.txt", true);
            String data = tname.getText()+" "+tusername.getText()+" "+ new String(tpassword.getPassword())+" "+tage.getText()+" "+result +" 0"+"\n";
            databaseW.append(data);
            databaseW.close();
        }
        catch(IOException e){}
    }

    //The function to check if username exists
    public boolean checkUsername(String text){
        try{
            database = new File("database.txt");
            databaseR = new Scanner(database);
            while(databaseR.hasNextLine()){
                String dataR = databaseR.nextLine();
                String[] dataE = dataR.split("\\s");
                if(dataE[1].equals(text))
                    return true;
            }
        } catch(Exception e){}

        return false;
    }

    //The method to check if the requirements are afforded    
    public boolean registerValidate(){
        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        label5.setVisible(false);   

        try{
            if (tname.getText().length() == 0){
                errorText1 = "*Name field should be filled up";
                label1.setText(errorText1);
                label1.setVisible(true);
                registerError = true;
            }
            if (tusername.getText().length() == 0){
                errorText2 = "*Username field should be filled up";
                label2.setText(errorText2);
                label2.setVisible(true);
                registerError = true;
            }
            if (tage.getText().length() == 0){
                errorText3 = "*Age field should be filled up";
                label3.setText(errorText3);
                label3.setVisible(true);
                registerError = true;
            }       
            if (tpassword.getPassword().length < 6){
                errorText4 = "*Password should be greater than 5";
                label4.setText(errorText4);
                label4.setVisible(true);
                registerError = true;
            }
            if (term.isSelected() == false){
                errorText5 = "*You should accepts terms and conditions";
                label5.setText(errorText5);
                label5.setVisible(true);
                registerError = true;
            }
            if(checkUsername(tusername.getText()) == true){
                errorText2 = "*This username is already taken";
                label2.setText(errorText2);
                label2.setVisible(true);
                registerError = true;
            }
            if (Integer.parseInt(tage.getText()) < 13){
                errorText3 = "*Age constraint is 13";
                label3.setText(errorText3);
                label3.setVisible(true);
                registerError = true;
            }
        }
        catch (Exception e){}
        return registerError;
    }

    public void registerPageVisibility(boolean x){
        this.setVisible(x);
    }
}