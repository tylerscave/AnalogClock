package assignment2;

/**
 *COPYRIGHT (C) 2015 Tyler Jones. All Rights Reserved.
 * The ClockIcon class creates the clock icon and defines the movement
 * of the clock based on the current time accessed from Calender. This
 * class was designed to work with ClockTester class
 * Solves CS151-05 assignment #2 Exercise 4.18
 * @author Tyler Jones
 * @version 1.01 10/1/2015
*/
import java.awt.*;
import java.awt.geom.*;
import java.util.Calendar;
import javax.swing.Icon;

public class ClockIcon implements Icon {
   
   public final int windowSize = 500;
   public final double theta = -0.1047; // theta 
   public final int hypotenous = 150; // hypotenous(heigth) of clock's hand
   private int xCenter = windowSize/2; // set the center of circle
   private int yCenter = windowSize/2; // set the center of circle
   private int clockSize = hypotenous * 2;
   
   private double thetaNumber = 0.0; // theta for numbers of clock
   private int xNumber, yNumber; // x and y position of Clock numbers
   private int perpNumber, baseNumber; // perpendicular and base of clock numbers

   private int xSecound, ySecound; // x and y position of Second's hand
   private int perpSecond, baseSecond; // perpendicular and base of Second's hand

   private int xMinute, yMinute; // x and y position of minute's hand
   private int perpMinute, baseMinute; // perpendicular and base of Minute's hand

   private int xHour, yHour; // x and y position of hour's hand
   private int perpHour, baseHour; // perpendicular and base of hour's hand
   
   /**
    * getIconWidth()
    * @return clocksize the size of this clock
    */
   public int getIconWidth() {
      return clockSize;
   }

   /**
    * getIconHeight()
    * @return clocksize the size of this clock
    */
   public int getIconHeight() {
      return clockSize;
   }
  
   /**
    * paintIcon() does all of the graphics for the clock icon. it draws the 
    * clock, the arms on the clock, and the numbers on the clock
    */
   public void paintIcon(Component c, Graphics g, int x, int y) {
      int num = 0;
      Graphics2D g2 = (Graphics2D) g;
      
      // make the clock circle
      Ellipse2D.Double colorCircle = new Ellipse2D.Double(
            (windowSize-clockSize)/2, (windowSize-clockSize)/2, (clockSize), (clockSize));
      g2.setStroke(new BasicStroke(2));
      g2.draw(colorCircle);
      g2.setColor(Color.CYAN);
      g2.fill(colorCircle);
   
      // put numbers onto the clock
      for (int i = 0; i < 60; i++) {       
         if (i % 5 == 0) {
            num++;
            if (num > 12) {
               num = 1;
            }
            thetaNumber = thetaNumber - 0.523598776;
            perpNumber = (int) (Math.sin(thetaNumber) * (hypotenous - 10));
            baseNumber = (int) (Math.cos(thetaNumber) * (hypotenous - 10));
            xNumber = xCenter - perpNumber;
            yNumber = yCenter - baseNumber;
            g2.setColor(Color.BLACK);
            g2.drawString("" + num, xNumber - 3, yNumber + 5);// drawing numbers
         }
      }

      //draw arms onto the clock
      g2.setColor(Color.BLACK);
      g2.setStroke(new BasicStroke(3));
      g2.drawLine(xCenter, yCenter, xMinute, yMinute); // drawing minute's hand
      g2.drawLine(xCenter, yCenter, xHour, yHour); // drawing hour's hand

      g2.setStroke(new BasicStroke(1));
      g2.setColor(Color.RED);
      g2.drawLine(xCenter, yCenter, xSecound, ySecound); // drawing second's hand
   }

   /**
    * moveArms() is responsible for defining the animation of the clock based
    * on the current time accessed from the Calander Class
    */
   public void moveArms() {
      Calendar now = Calendar.getInstance(); 

      // for seconds hand
      double thetaSecond = theta * now.get(Calendar.SECOND);
      perpSecond = (int) (Math.sin(thetaSecond) * (hypotenous - 20));
      baseSecond = (int) (Math.cos(thetaSecond) * (hypotenous - 20));
      xSecound = xCenter - perpSecond;
      ySecound = yCenter - baseSecond;

      // for minutes hand
      double thetaMinute = theta * now.get(Calendar.MINUTE);
      perpMinute = (int) (Math.sin(thetaMinute) * (hypotenous - 35));
      baseMinute = (int) (Math.cos(thetaMinute) * (hypotenous - 35));
      xMinute = xCenter - perpMinute;
      yMinute = yCenter - baseMinute;

      // for hour's hand
      double thetaHour = theta * now.get(Calendar.HOUR) * 5;
      if (now.get(Calendar.MINUTE) >= 12 && now.get(Calendar.MINUTE) < 24) {
         thetaHour = thetaHour - 0.1047;
      } else if (now.get(Calendar.MINUTE) >= 24
            && now.get(Calendar.MINUTE) < 36) {
         thetaHour = thetaHour - (2 * 0.1047);
      } else if (now.get(Calendar.MINUTE) >= 36
            && now.get(Calendar.MINUTE) < 48) {
         thetaHour = thetaHour - (3 * 0.1047);
      } else if (now.get(Calendar.MINUTE) >= 48
            && now.get(Calendar.MINUTE) < 60) {
         thetaHour = thetaHour - (4 * 0.1047);
      }
      perpHour = (int) (Math.sin(thetaHour) * (hypotenous - 70));
      baseHour = (int) (Math.cos(thetaHour) * (hypotenous - 70));
      xHour = xCenter - perpHour;
      yHour = yCenter - baseHour;
   }  
}
