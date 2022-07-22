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

public class Cat extends JPanel{

	Image catImage;
	int catx, caty, width=140, height=164;

	public Cat(){

		catImage = new ImageIcon("Pictures/cat.png").getImage();
	}

	//The function to move each cat by incremented value
	public void moveCat(int y){
		caty = caty - y;
	}

	//The function to set location for each cat
	public void setLoc(int xpos, int ypos){
		catx = xpos;
		caty = ypos;
	}

	//The function used to get "bounding box"
	public Rectangle getRectangle(){
		Rectangle rect = new Rectangle(catx, caty, width, height);
		return rect;
	}

	//The function to get the current location of Cat object
	public int getLoc(){
		return caty;
	}

	//The function to draw cat Image
	public void drawCat(Graphics g){
		g.drawImage(catImage, catx, caty, null);
	}
}