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

public class StartPage extends JPanel implements ActionListener{

	JButton startButton, shopButton;
	JLabel moneyBoard;
	int money=0;
	String color, dataK;
	Image startPage;
	GamePage gamePage; 
	ShopPage shopPage;
	File database; Scanner databaseR;

	public StartPage(){

		//StartPage Panel settings
		this.setBounds(0,0,920,1080);
    	this.setBackground(new Color(85,116,166));
    	this.setLayout(null);
    	this.setVisible(true);
    	this.setFocusable(true);

    	startPage = new ImageIcon("Pictures/startPage.jpg").getImage();

		//StartButton
		startButton = new JButton("Start Game");
		startButton.setBounds(360,495,200,50);
		startButton.setFocusable(false);
		startButton.setVisible(true);
		startButton.setFont(new Font("Arial",Font.BOLD, 20));
		startButton.addActionListener(this);

		gamePage = new GamePage();
		shopPage = new ShopPage();
		shopPage.backButton.addActionListener(this);
		gamePage.quitButton.addActionListener(this);

		//ShopButton
		shopButton = new JButton("Shop");
		shopButton.setBounds(380,560,160,50);
		shopButton.setFocusable(false);
		shopButton.setVisible(true);
		shopButton.setFont(new Font("Arial",Font.BOLD, 20));
		shopButton.addActionListener(this);

		//Money label
		moneyBoard = new JLabel("Points: "+ money);
		moneyBoard.setOpaque(false);
		moneyBoard.setForeground(new Color(33,30,31));
		moneyBoard.setFont(new Font("Arial", Font.BOLD, 25));
		moneyBoard.setBounds(0,0,300,30);
		moneyBoard.setVisible(true);

		this.add(startButton);
		this.add(shopButton);
		this.add(moneyBoard);
    }

    //The function to get data from login
    public void setLoginData(String data){
    	dataK = data;
    }

    //The function to check money in database
    public void checkMoney(){
    	try{
	    	database = new File("database.txt");
			databaseR = new Scanner(database);
			while(databaseR.hasNextLine()){
				String dataR = databaseR.nextLine();
				String[] dataj = dataR.split("\\s");
				if (dataK.equals(dataj[1])){
					money = Integer.parseInt(dataj[5]);
					moneyBoard.setText("Points: "+money);
					break;
				}
			}
		} catch (Exception e){}
    }

    //The function to sustain main page
    public void startPanel(){
    	this.setVisible(true);
    	startButton.setVisible(true);
    	shopButton.setVisible(true);
    	shopPage.shopPageVisibility(false);
    	gamePage.gamePageVisibility(false);
    }

    //The function to start gamepage
    public void gamePanel(){
    	this.setVisible(false);
    	startButton.setVisible(false);
    	shopButton.setVisible(false);
    	gamePage.gamePageVisibility(true);
    }

    //The function to shop
    public void shopPanel(){
    	this.setVisible(false);
    	startButton.setVisible(false);
    	shopButton.setVisible(false);
    	shopPage.shopPageVisibility(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
    	if (e.getSource() == startButton){
			gamePage.bird.setBirdColor(shopPage.getBirdIcon());
    		gamePanel();
    		gamePage.setLogin(dataK);
    	}
    	if (e.getSource() == gamePage.quitButton){
    		checkMoney();
    		moneyBoard.setText("Points: "+money);
    		gamePage.gameStarted = false;
    		gamePage.gameOver = false;
    		gamePage.checked = false;
    		gamePage.checkLevel(false);
    		gamePage.updateGame();
    		startPanel();
    	}
    	if (e.getSource() == shopButton){
    		shopPanel();
    		shopPage.setLogin(dataK);
    		shopPage.checkBalance();
    	}
    	if(e.getSource() == shopPage.backButton){
    		checkMoney();
    		moneyBoard.setText("Points: "+money);
    		startPanel();
    	}
    }

    public void paintComponent(Graphics g){
    	g.drawImage(startPage, 0, 0, null);
    }

    public void startPageVisibility(boolean x){
    	this.setVisible(x);
    }
}