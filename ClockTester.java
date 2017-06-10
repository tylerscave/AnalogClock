package assignment2;

/**
 * The ClockTester class creates the gui components for the clock icon
 * It creates a JFrame and puts the clock icon on it as a label. It then
 * creates a new thread for the clock animation to run in
 * COPYRIGHT (C) 2015 Tyler Jones. All Rights Reserved. 
 * Solves CS151-05 assignment #2 Exercise 4.18
 * 
 * @author Tyler Jones
 * @version 1.01 10/1/2015
 */
import java.awt.*;
import javax.swing.*;

public class ClockTester extends JFrame {

   public final int FRAME_SIZE = 500;
   final static ClockIcon icon = new ClockIcon();
   final static JLabel label = new JLabel(icon);

   /**
    * Constructor for the ClockTester Class. Creates the JFrame
    */
   public ClockTester() {
      super("Analog Clock");
      this.setSize(FRAME_SIZE, FRAME_SIZE);
      this.setResizable(false);
      this.setLayout(new GridLayout());
      this.setVisible(true);
      this.add(label);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public static void main(String[] args) {
      // create the JFrame and populate it
      new ClockTester();
      
      /**
       * Anonymous innerclass to create a new thread for the 
       * clock animation
       */
      Runnable r = new Runnable() {
         public void run() {
            while (true) {
               icon.moveArms();
               label.repaint();
               try {
                  Thread.sleep(1000);
               } catch (InterruptedException e) {}
            }
         }
      };
      Thread t = new Thread(r);
      t.start();
   }
}
