package cn.ssmc.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

public class ImgUtil {

	public static Map<String, Object> getImg(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Random r= new Random();
		 int strCount = 4;//产生四个随机字符
		 int lineCount = 20;//干扰线数量
		 int codeY;
		 char[] codeSequence ={'0','1','2','5','9','4','3','6','7','8'};
	     //3.创建内存中的图
	     int width=60;
	     int height=40;
	     BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	     //4.获取画笔
	     Graphics g = img.getGraphics();
		 // 5:设置画布的background
		 g.setColor(Color.WHITE);
		 g.fillRect(0, 0, width, height);
		 // 6：设置字体
		 g.setFont(new Font("宋体", Font.BOLD, 20));
	     //7.生成随机干扰线
		 g.setColor(Color.BLACK);
		 for(int i=1;i<lineCount;i++){
			 int x = r.nextInt(width);
			 int y = r.nextInt(height);
			 int x1 = r.nextInt(12);
			 int y1 = r.nextInt(12);
			 g.drawLine(x, y, x1+x, y1+y);//线条数
		 }
		 //randomCode用于保存随机产生的验证码，以便登录的验证。
		 StringBuffer randomCode = new StringBuffer();
		 int red=0,green=0,blue=0,black=0;
		 //8生成四位随机验证码
		 for(int i =0;i<strCount;i++){
			 //得到验证码
			 String strRand = String.valueOf(codeSequence[r.nextInt(9)]);
			 //产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值将不同
			 red = r.nextInt(255);
			 green = r.nextInt(255);
			 blue = r.nextInt(255);
			 
			 //用随机产生的颜色将验证码绘制到图像中
			 g.setColor(new Color(red,green,blue));
			 g.drawString(strRand,(i+1)*(width/(strCount+1)) ,height-4 );
			 //将产生的四个随机数结合在一起。
			 randomCode.append(strRand);
		 }
		 //将验证码保存到session中，为了以后的验证
		// req.getSession().setAttribute("validateCode", randomCode.toString());
		//让图片生效
		g.dispose();
		
		map.put("img", img);
		map.put("code", randomCode.toString());
		
		//输出图片
		//ImageIO.write(img, "JPEG", resp.getOutputStream());
		return map;
	}
	
}
