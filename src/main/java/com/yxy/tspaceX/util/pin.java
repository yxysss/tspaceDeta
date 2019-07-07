package com.yxy.tspaceX.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.servlet.http.HttpSession;

public class pin {

	/* 该方法主要作用是获得随机生成的颜色 */
	private static Color getRandColor(int s, int e) {
		Random random = new Random();
		if (s > 255)
			s = 255;
		if (e > 255)
			e = 255;
		int r, g, b;
		r = s + random.nextInt(e - s); // 随机生成RGB颜色中的r值
		g = s + random.nextInt(e - s); // 随机生成RGB颜色中的g值
		b = s + random.nextInt(e - s); // 随机生成RGB颜色中的b值
		return new Color(r, g, b);
	}
	
	private static Random r = new Random();
	
	// 随机字符集合中不包括0和o，O，1和l，因为这些不易区分 
	private static String codes = "23456789abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYXZ";  
	private static char randomChar() {  
        int index = r.nextInt(codes.length());  
        return codes.charAt(index);  
	}
	
	public static BufferedImage getVerifyImage(int width, int height, HttpSession session, String attr) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Graphics2D g2d = (Graphics2D)g;
		
		Random random = new Random();
		Font mfont = new Font("楷体", Font.BOLD, 20);
		g.setColor(getRandColor(200,250));
		g.fillRect(0, 0, width, height);
		g.setFont(mfont);
		g.setColor(getRandColor(180,200));
		
		for (int i = 0; i < 100; i ++) {
			int x = random.nextInt(width-1);
			int y = random.nextInt(height-1);
			int x1 = random.nextInt(6)+1;
			int y1 = random.nextInt(12)+1;
			BasicStroke bs = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
			Line2D line0 = new Line2D.Double(x, y, x+x1, y+y1);
			g2d.setStroke(bs);
			g2d.draw(line0);
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 4; i ++) {
			String sTemp = String.valueOf(randomChar());
			sb.append(sTemp);
			Color color = new Color(20+random.nextInt(110), 20+random.nextInt(110), random.nextInt(110));
			g.setColor(color);
			
			Graphics2D g2d_word = (Graphics2D)g;
			AffineTransform trans = new AffineTransform();
			trans.rotate((45)*3.14/180, 15*i+8, 7);
			
			float scaleSize = random.nextFloat() + 0.8f;
			if (scaleSize > 1f) {
				scaleSize = 1f;
			}
			trans.scale(scaleSize, scaleSize);
			g2d_word.setTransform(trans);
			g.drawString(sTemp, 25*i+30, 28);
		}
		
		session.setAttribute(attr, sb.toString());
		// System.out.println(sb.toString());
		g.dispose();
		return image;
	}
	
}
