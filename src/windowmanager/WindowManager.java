package windowmanager;

import main.Final; 
public class WindowManager {
	static public Window Main; 
	public WindowManager(){
		Main=new Window(new WindowComponent().menu,"SHOGI",Final.width,Final.height);
	} 
	
 
}
