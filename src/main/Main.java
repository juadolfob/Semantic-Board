package main;
import java.io.IOException; 
import javax.swing.SwingUtilities;  
import windowmanager.*; 

 class Main extends Final{
	 public static void main(String[] args) throws   IOException {   
	 
         
//-------GUI
         
	        SwingUtilities.invokeLater(new Runnable() {  
				public void run() {
					new WindowManager();
	            } 
	        });   
}
}