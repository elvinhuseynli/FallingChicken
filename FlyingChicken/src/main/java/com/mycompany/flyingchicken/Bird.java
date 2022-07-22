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

public class Bird extends JPanel{

	Image birdImage;
	int birdx, birdy, width=392, height=160;
	ArrayList<Egg> eggs  = new ArrayList<Egg>();
	ArrayList<Rectangle> rectangles;
	boolean dropEgg = false;
	Action leftAction, rightAction, dropAction;

	public Bird(String image){

		birdImage = new ImageIcon(image).getImage();
		birdx = 264;birdy = 230;
	}

	public void setBirdColor(String color){

		birdImage = new ImageIcon(color).getImage();
	}

	//The function to draw Bird
	public void drawBird(Graphics g){
		g.drawImage(birdImage, birdx, birdy, null);
	}

	//The function to add eggs as the key pressed
	public void addEgg(int eggx, int eggy){

		Egg egg = new Egg();

		egg.setLoc(eggx, eggy);
		eggs.add(egg);
	}

	//The function to remove egg from array
	public void removeEgg(Rectangle rect){
		for(Egg egg:eggs){
			int x = (int) rect.getX();
			int y = (int) rect.getY();
			if (x == egg.eggx && y == egg.eggy){
				eggs.remove(egg);
				break;
			}
		}
	}

	//The function to draw eggs
	public void drawEgg(Graphics g){
		for (Egg egg: eggs){
			egg.draw(g);
		}
	}

	//The function used to get "bounding box" for eggs
	public Rectangle[] getRectangleEgg(){

		rectangles = new ArrayList<Rectangle>();
		for(Egg egg: eggs){			
			rectangles.add(egg.getRectangle());
		}
		Rectangle[] rect = new Rectangle[rectangles.size()];
		rectangles.toArray(rect);
		return rect;
	}

	//The function used to get "bounding box"
	public Rectangle getRectangle(){
		Rectangle rect = new Rectangle(birdx, birdy, width, height);
		return rect;
	}

	//The function to invoke all egg objects
	public void moveEgg(int y){

		for(Egg egg: eggs){
			egg.eggMove(3+y);
		}	
	}

	//The function to create keybindings
	public void createKeyBindings(JPanel p) {
	    InputMap im = p.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
	    ActionMap am = p.getActionMap();
	    leftAction = new LeftAction();
	    rightAction = new RightAction();
	    dropAction = new DropAction();
	    im.put(KeyStroke.getKeyStroke("LEFT"), "left");
	    im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
	    im.put(KeyStroke.getKeyStroke("SPACE"), "space");
	    am.put("left", leftAction);
	    am.put("right", rightAction);
	    am.put("space", dropAction);
	}

	//Keybinding for leftAction
	public class LeftAction extends AbstractAction{
		@Override
		public void actionPerformed(ActionEvent e){

			if(birdx>-190)
				birdx = birdx-20;
			repaint();
		}
	}

	//Keybinding for rightAction
	public class RightAction extends AbstractAction{
		@Override
		public void actionPerformed(ActionEvent e){

			if(birdx<700)
				birdx = birdx+20;
			repaint();
		}
	}

	//Keybinding for dropAction
	public class DropAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e){

			addEgg(birdx+173, 350);
			dropEgg = true;
			repaint();
		}
	}
}