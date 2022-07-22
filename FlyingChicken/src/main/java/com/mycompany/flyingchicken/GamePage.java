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

public class GamePage extends JPanel implements  Runnable{

	JLabel scoreBoard;JButton quitButton;String birdColor, data;
	int xpos=0, ypos=0, score = 0, catCount=0, noCats, velocity=0;
	boolean gameStarted = false, gameOver=false, checked = false;
	boolean level1=true, level2=false, level3=false;
	Thread animator;Action startAction;Bird bird;
	ArrayList<Cat> cats = new ArrayList<Cat>();
	File database; Scanner databaseR; FileWriter databaseW;
	ArrayList<Background> images = new ArrayList<Background>();
	HashMap<Integer,Integer> coordinates = new HashMap<Integer,Integer>();
	private static final long serialVersionUID = 5462243600l;

	public GamePage() {

		setDoubleBuffered(true);
		this.setSize(920,1080);
		this.setLocation(0,0);
		this.setLayout(null);
		this.setFocusable(true);
		this.setVisible(true);

		//Scoreboard
		scoreBoard = new JLabel("Score: "+ score);
		scoreBoard.setOpaque(false);
		scoreBoard.setForeground(new Color(33,30,31));
		scoreBoard.setFont(new Font("Arial", Font.BOLD, 25));
		scoreBoard.setBounds(0,0,200,30);
		scoreBoard.setVisible(true);

		//QuitButton
		quitButton = new JButton("Exit");
		quitButton.setBounds(820,0,100,50);
		quitButton.setBackground(new Color(245, 233, 66));
		quitButton.setFocusable(false);
		quitButton.setVisible(true);

		noCats = ThreadLocalRandom.current().nextInt(13,18);
		bird = new Bird("Pictures/bird.png");
		addCats(noCats);
		addBackground(13);

		animator = new Thread(this);

		startAction = new StartAction();
		bird.createKeyBindings(this);
		this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"),"start");
		this.getActionMap().put("start", startAction);

		this.add(quitButton);
		this.add(scoreBoard);
	}

	@Override
	public void run() {

		while(true) {
			try{
				if (gameStarted && !gameOver && getMaxYpos()){
					animator.wait();
				}
				else if (gameStarted && !gameOver) { 
					playGame();
					repaint();
					Toolkit.getDefaultToolkit().sync();
					Thread.sleep(10);
				}
				else if(!gameStarted && gameOver){
					animator.wait();
				}
			} catch(Exception e) {}			
		}
	}

	//The function used to play game
	public void playGame(){
		for(Background bg: images){
			bg.moveBackground(2+velocity);
		}
		for(Cat cat: cats) {
			cat.moveCat(2+velocity);
		}
		checkCollisionBC();
		if(bird.dropEgg == true)
			bird.moveEgg(velocity);
		checkCollisionEC();
		scoreBoard.setText("Score: "+score);
	}

	//The function to add Backgrounds
	public void addBackground(int count) {
		for (int i=0; i<count; i++) {
			Background bg = new Background();
			bg.setLoc(0, 0+i*1080);
			images.add(bg);
		}
	}

	//The function to get login info
	public void setLogin(String datar){
		data = datar;
	}

	//The function to record score to database
	public void recordScore(){
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
			    	int total = score + Integer.parseInt(dataj[5]);
			    	String after = dataj[0]+" "+dataj[1]+" "+dataj[2]+" "+dataj[3]+" "+dataj[4]+" "+String.valueOf(total);
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

	//The function to add cats to frame
	public void addCats(int count) {

		for(int i=0; i<count; i++) {
			
			int xloc, yloc;			
			Cat cat = new Cat();
			xloc = ThreadLocalRandom.current().nextInt(0,750);
			yloc = 800 + ThreadLocalRandom.current().nextInt(0,3800+velocity*1000);
			while(checkOverlap(xloc,yloc)) {
				xloc = ThreadLocalRandom.current().nextInt(0,750);
				yloc = 1000 + ThreadLocalRandom.current().nextInt(0,3800+velocity*1000);
			}
			Integer u = Integer.valueOf(xloc), p = Integer.valueOf(yloc);
			coordinates.put(u, p);
			cat.setLoc(xloc,yloc);
			cats.add(cat);
		}
	}

	//The function to check collision of eggs and cats
	public void checkCollisionEC() {
		
		Rectangle[] rects = bird.getRectangleEgg();
		for(Rectangle rect: rects) {
			for(Cat cat: cats) {
				Rectangle catR = cat.getRectangle();
				if(rect.intersects(catR) && cat.getLoc()<1080) {
					coordinates.remove(cat.catx);
					cats.remove(cat);
					noCats-=1;
					bird.removeEgg(rect);
					score+=100+100*velocity;
					break;
				}
			}
		}
	}

	//The function to take the max ypos of cats
	public boolean getMaxYpos() {
		int maxi = 0;
	
		for(Cat cat: cats){
			if (cat.caty>maxi)
				maxi = cat.caty;
		}
		if (maxi<bird.birdy){
			if(!checked && level3){
				recordScore();
				checked = true;
			}
			return true;
		}
		return false;
	}

	//The function to check bird and cat collision
	public void checkCollisionBC() {
		
		for(Cat cat: cats) {
			Rectangle birdR = bird.getRectangle();
			Rectangle catR = cat.getRectangle();

			if (birdR.intersects(catR)) {
				gameStarted = false;
				gameOver = true;
				recordScore();
				return;
			}
		}
	}

	//The function to check overlap
	public boolean checkOverlap(int xloc, int yloc) {
		for(Integer i: coordinates.keySet()) {
			int j = i.intValue(), k = coordinates.get(i).intValue();
			double distance = Math.sqrt(Math.pow(Math.abs(j-xloc),2)+Math.pow(Math.abs(k-yloc),2.0));
			if (Math.abs(distance)<400)
				return true;
		}
		return false;
	}

	//The function to check level and adjust values
	public void checkLevel(boolean x){
		if (x){
			if(level1){
				level1 = false;
				level2 = true;
			}
			else if(level2){
				level2 = false;
				level3 = true;
			}
			else if(level3){
				level3 = false;
				level1 = true;
			}
		}
		else{
			level1 = true;
			level2 = false;
			level3 = false;
		}

		if(level1)
			velocity = 0;
		else if(level2)
			velocity = 1;
		else if(level3)
			velocity = 2;
	}

	//The function to update game 
	public void updateGame(){
		bird.birdx = 264;
		int dist = images.get(0).ypos;
		for(Background bg: images)
			bg.ypos = bg.ypos - dist;
		cats.clear();
		coordinates.clear();
		noCats = ThreadLocalRandom.current().nextInt(13,18)+velocity*3;
		if(level1)
			score = 0;	
		addCats(noCats);
		bird.eggs.clear();
	}

	public class StartAction extends AbstractAction{
		@Override
		public void actionPerformed(ActionEvent e) {

			if(gameStarted && !gameOver && getMaxYpos()){

				checkLevel(true);
				updateGame();
			}

			else if (!gameStarted && !gameOver) {
				gameStarted = true;
				checkLevel(false);
				updateGame();
				try{
					animator.start();
				} catch (Exception ex){};
			}

			else if(!gameStarted && gameOver){
				gameStarted = true;
				gameOver = false;
				checkLevel(false);
				updateGame();
			}
		}
	}

	//The function to draw background
	public void paintComponent(Graphics g) {
		
    	super.paintComponent(g);
    	g.setFont(new Font("SansSerif", 1, 50));
    	FontMetrics fm = g.getFontMetrics();
    	for(Background bg: images)
    		bg.drawBackground(g);
    	if(gameStarted && !gameOver && getMaxYpos() && level1){
    		int k = fm.stringWidth("Level 1 Passed");
    		int j = fm.stringWidth("Your Score: "+score);
    		g.drawString("Level 1 Passed",460-k/2,500);
    		g.drawString("Your Score: "+score,460-j/2,580);
    	}
    	if(gameStarted && !gameOver && getMaxYpos() && level2){
    		int k = fm.stringWidth("Level 2 Passed");
    		int j = fm.stringWidth("Your Score: "+score);
    		g.drawString("Level 2 Passed",460-k/2,500);
    		g.drawString("Your Score: "+score,460-j/2,580);
    	}
    	if(gameStarted && !gameOver && getMaxYpos() && level3){
    		int k = fm.stringWidth("You Won!");
    		int j = fm.stringWidth("Your Score: "+score);
    		g.drawString("You Won!",460-k/2,500);
    		g.drawString("Your Score: "+score,460-j/2,580);
    	}

    	else if (gameStarted && !gameOver) {

    		bird.drawBird(g);
	    	for(Cat cat: cats)
	    		cat.drawCat(g);
	    	if(bird.dropEgg==true)
	    		bird.drawEgg(g);
    	}
    	else if(!gameStarted && !gameOver)
    		g.drawString("Press Enter to Start",200,500);
    	else if(!gameStarted && gameOver) {
    		int w = fm.stringWidth("Game Over!");
    		int w2 = fm.stringWidth("Your score: "+score);
    		int w3 = fm.stringWidth("Click Enter to Restart");
    		g.drawString("Game Over!",460-w/2,500);
    		g.drawString("Your Score: "+score,460-w2/2,580);
    		g.drawString("Click Enter to Restart", 460-w3/2,660);
    	}
	}

	public void gamePageVisibility(boolean x) {
		this.setVisible(x);
	}
}