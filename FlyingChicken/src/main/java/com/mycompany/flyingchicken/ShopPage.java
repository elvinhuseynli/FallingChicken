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

public class ShopPage extends JPanel implements ActionListener{

	String def, blue, orange, green, yellow, red, cyan, purple, selected, text="Selected";
	JButton backButton, selectButton; JLabel shopLabel, moneyLabel;int money=0;
	ImageIcon defIcon, blueIcon, orangeIcon, greenIcon, yellowIcon, redIcon, cyanIcon, purpleIcon;
	Image shopPage; ButtonGroup buttonGroup; String data;
	File database; Scanner databaseR;FileWriter databaseW;
	JRadioButton defRadioButton, blueRadioButton, orangeRadioButton, greenRadioButton, yellowRadioButton, redRadioButton, cyanRadioButton, purpleRadioButton;

	public ShopPage(){

		//ShopPage Panel settings
        this.setBounds(0,0,920,1080);
        this.setBackground(new Color(85,116,166));
        this.setLayout(null);
        this.setVisible(true);
        this.setFocusable(true);

        shopPage = new ImageIcon("Pictures/shopPage.jpg").getImage();

		def = "Pictures/bird.png";
		blue = "Pictures/birdBlue.png";
		orange = "Pictures/birdOrange.png";
		green = "Pictures/birdGreen.png";
		yellow = "Pictures/birdYellow.png";
		red = "Pictures/birdRed.png";
		cyan = "Pictures/birdCyan.png";
		purple = "Pictures/birdPurple.png";

        selected = def;	

		defIcon = new ImageIcon(def);
		blueIcon = new ImageIcon(blue);
		orangeIcon = new ImageIcon(orange);
		greenIcon = new ImageIcon(green);
		yellowIcon = new ImageIcon(yellow);
		redIcon = new ImageIcon(red);
		cyanIcon = new ImageIcon(cyan);
		purpleIcon = new ImageIcon(purple);
		
		defRadioButton = new JRadioButton();
		defRadioButton.setIcon(defIcon);
		defRadioButton.setSize(392, 160);
		defRadioButton.setLocation(45,62);
		defRadioButton.setVisible(true);
		defRadioButton.setOpaque(false);
		defRadioButton.addActionListener(this);

		blueRadioButton = new JRadioButton();
		blueRadioButton.setIcon(blueIcon);
		blueRadioButton.setSize(392,160);
		blueRadioButton.setLocation(483,62);
		blueRadioButton.setVisible(true);
		blueRadioButton.setOpaque(false);
		blueRadioButton.addActionListener(this);
			
		greenRadioButton = new JRadioButton();
		greenRadioButton.setIcon(greenIcon);
		greenRadioButton.setSize(392,160);
		greenRadioButton.setLocation(45,278);
		greenRadioButton.setVisible(true);
		greenRadioButton.setOpaque(false);
		greenRadioButton.addActionListener(this);
			
		redRadioButton = new JRadioButton();
		redRadioButton.setIcon(redIcon);
		redRadioButton.setSize(392,160);
		redRadioButton.setLocation(483,278);
		redRadioButton.setVisible(true);
		redRadioButton.setOpaque(false);
		redRadioButton.addActionListener(this);
			
		orangeRadioButton = new JRadioButton();
		orangeRadioButton.setIcon(orangeIcon);
		orangeRadioButton.setSize(392,160);
		orangeRadioButton.setLocation(45,494);
		orangeRadioButton.setVisible(true);
		orangeRadioButton.setOpaque(false);
		orangeRadioButton.addActionListener(this);
			
		cyanRadioButton = new JRadioButton();
		cyanRadioButton.setIcon(cyanIcon);
		cyanRadioButton.setSize(392,160);
		cyanRadioButton.setLocation(483,494);
		cyanRadioButton.setVisible(true);
		cyanRadioButton.setOpaque(false);
		cyanRadioButton.addActionListener(this);
			
		yellowRadioButton = new JRadioButton();
		yellowRadioButton.setIcon(yellowIcon);
		yellowRadioButton.setSize(392,160);
		yellowRadioButton.setLocation(45,710);
		yellowRadioButton.setVisible(true);
		yellowRadioButton.setOpaque(false);
		yellowRadioButton.addActionListener(this);
			
		purpleRadioButton = new JRadioButton();
		purpleRadioButton.setIcon(purpleIcon);
		purpleRadioButton.setSize(392,160);
		purpleRadioButton.setLocation(483,710);
		purpleRadioButton.setVisible(true);
		purpleRadioButton.setOpaque(false);
		purpleRadioButton.addActionListener(this);

		buttonGroup = new ButtonGroup();
		buttonGroup.add(purpleRadioButton);
		buttonGroup.add(yellowRadioButton);
		buttonGroup.add(defRadioButton);
		buttonGroup.add(orangeRadioButton);
		buttonGroup.add(cyanRadioButton);
		buttonGroup.add(redRadioButton);
		buttonGroup.add(blueRadioButton);
		buttonGroup.add(greenRadioButton);

		//Money label
		moneyLabel = new JLabel("Points: "+money);
		moneyLabel.setOpaque(false);
		moneyLabel.setForeground(new Color(245, 233, 66));
		moneyLabel.setFont(new Font("Arial", Font.BOLD, 25));
		moneyLabel.setBounds(0,0,300,30);
		moneyLabel.setVisible(true);

		//Shop label
		shopLabel = new JLabel("SHOP");
		shopLabel.setOpaque(false);
		shopLabel.setForeground(new Color(245, 233, 66));
		shopLabel.setFont(new Font("Arial", Font.BOLD, 45));
		shopLabel.setBounds(390,0,200,50);
		shopLabel.setVisible(true);

        //BackButton
        backButton = new JButton("Return Back");       
        backButton.setBounds(345,945,230,50);
        backButton.setFocusable(false);
        backButton.setVisible(true);
        backButton.setBackground(Color.green);

        //BackButton
        selectButton = new JButton(text);       
        selectButton.setBounds(345,880,230,50);
        selectButton.setFocusable(false);
        selectButton.setVisible(true);
        selectButton.setBackground(Color.green);
        selectButton.addActionListener(this);

        this.add(shopLabel);this.add(moneyLabel);
        this.add(selectButton);this.add(backButton);
		this.add(defRadioButton);this.add(blueRadioButton);
		this.add(greenRadioButton);this.add(orangeRadioButton);
		this.add(redRadioButton);this.add(yellowRadioButton);
		this.add(purpleRadioButton);this.add(cyanRadioButton);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(shopPage, 0, 0, null);
	}

	//The function used to get Icon for bird
	public String getBirdIcon(){
		return selected;
	}

	//The function to get money data from shop
	public int getMoney(){
		return money;
	}

	//The function to get login info
	public void setLogin(String datar){
		data = datar;
	}

	//The function to check balance
	public void checkBalance(){
		try{
	    	database = new File("database.txt");
			databaseR = new Scanner(database);
			while(databaseR.hasNextLine()){
				String dataR = databaseR.nextLine();
				String[] dataj = dataR.split("\\s");
				if (data.equals(dataj[1])){
					money = Integer.parseInt(dataj[5]);
					moneyLabel.setText("Points: "+money);
					break;
				}
			}
		} catch (Exception e){}
	}

	//The function to rewrite database;
	public void rewriteDB(){
		try{
        	ArrayList<String> fileContent = new ArrayList<String>();
        	database = new File("database.txt");
			databaseR = new Scanner(database);
			databaseW = new FileWriter("database.txt", true);
			while(databaseR.hasNextLine()){
				String dataR = databaseR.nextLine();
				fileContent.add(dataR);
			}

			for (int i = 0; i < fileContent.size(); i++) {
				String[] dataj = fileContent.get(i).split("\\s");
			    if (dataj[1].equals(data)) {
			    	String after = dataj[0]+" "+dataj[1]+" "+dataj[2]+" "+dataj[3]+" "+dataj[4]+" "+String.valueOf(money);
			        fileContent.set(i, after);
        			break;
    			}
			}
			database.delete();
			databaseW = new FileWriter("database.txt", true);
			database = new File("database.txt");
			for (int i = 0; i < fileContent.size(); i++) {
				databaseW.write(fileContent.get(i)+"\n");
			}
			databaseW.close();
        } catch(Exception e){}
	}

	//The function to check which radiobutton is selected
	public String checkSelection(){
		text = "1000 coins";

		if (blueRadioButton.isSelected()){
			selectButton.setText(text);
			return blue;
		}
		if (greenRadioButton.isSelected()){
			selectButton.setText(text);
			return green;
		}
		if (orangeRadioButton.isSelected()){
			selectButton.setText(text);
			return orange;
		}
		if (redRadioButton.isSelected()){
			selectButton.setText(text);
			return red;
		}
		if (yellowRadioButton.isSelected()){
			selectButton.setText(text);
			return yellow;
		}
		if (cyanRadioButton.isSelected()){
			selectButton.setText(text);
			return cyan;
		}
		if (purpleRadioButton.isSelected()){
			selectButton.setText(text);
			return purple;
		}
		return def;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		selected = checkSelection();
		if (e.getSource() == selectButton){
			if(money >= 1000){
				money = money - 1000;
				selectButton.setText("Selected");
				rewriteDB();
				moneyLabel.setText("Points: "+money);
			}
		}
		else{
			selected = def;
		}
	}

	public void shopPageVisibility(boolean x){
		this.setVisible(x);
	}
}