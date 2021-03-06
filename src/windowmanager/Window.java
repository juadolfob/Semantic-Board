package windowmanager;

import java.awt.*;
import javax.swing.*;

public class Window {

	JFrame frame;

	String Thumbnail;

	public Window(Component WindowComponents, String New_Thumbnail, int width, int height) {

		Thumbnail = New_Thumbnail;
		frame = new JFrame(Thumbnail); 
		frame.setSize(new Dimension(width,height));
		frame.add(WindowComponents);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(width, height); 
		frame.setResizable(false); 
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public Window(int width, int height) {
		frame = new JFrame("");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(width, height);
		CenterWindow();
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public Window(Component WindowComponents, String New_Thumbnail) {

		Thumbnail = New_Thumbnail;
		frame = new JFrame(Thumbnail);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(WindowComponents);
		frame.setSize(1920, 1080);
		CenterWindow();
		frame.setVisible(true);
		frame.setResizable(false);

	}

	public Window(Component WindowComponents) {
		frame = new JFrame("");

		frame.add(WindowComponents);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1920, 1080);
		CenterWindow();
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public Window(Component WindowComponents, String New_Thumbnail, int width, int height, boolean Visibility) {

		frame = new JFrame(New_Thumbnail);
		frame.add(WindowComponents);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(width, height);
		CenterWindow();
		frame.setVisible(Visibility);
		frame.setResizable(false);
	}

	public Window(String New_Thumbnail) {

		Thumbnail = New_Thumbnail;
		frame = new JFrame(Thumbnail);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1920, 1080);
		CenterWindow();
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public Window(String New_Thumbnail, boolean Visibility) {
		Thumbnail = New_Thumbnail;
		frame = new JFrame(Thumbnail);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1920, 1080);
		CenterWindow();
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public Window() {

		Thumbnail = "";
		frame = new JFrame(Thumbnail);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1920, 1080);
		CenterWindow();
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public Window(String New_Thumbnail, int width, int height, boolean Visibility) {

		frame = new JFrame(New_Thumbnail);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(width, height);
		CenterWindow();
		frame.setVisible(Visibility);
		frame.setResizable(false);
	}

	public Rectangle getBounds() {
		Rectangle r = frame.getBounds();
		// h = r.height;
		// w = r.width;
		return r;
	}

	public void PrintBounds() {
		System.out.println("height  " + getBounds().height);
		System.out.println("width   " + getBounds().width);
	}

	public void show(Component WindowComponents) {
		clear();
		frame.add(WindowComponents);
		frame.setVisible(true);
	}

	public void clear() {
		frame.getContentPane().removeAll();
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
	}

	public boolean isVisible() {
		return frame.isVisible();
	}

	public void setVisible(boolean Visibility) {
		frame.setVisible(Visibility);
	}

	public void CenterWindow() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
	}

	public void SetWindowLocation() {
	}

	public Rectangle PositioninScreen() {
		return null;
	}

	public void Refresh() {
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
		frame.getContentPane().getComponent(0).revalidate();
		frame.getContentPane().getComponent(0).repaint();
		frame.validate();
		frame.repaint();
	}

}
