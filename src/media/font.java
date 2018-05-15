package media;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class font {
	public Font textt = new Font("Serif", Font.BOLD, 12);
	public Font font = new Font("Courier New", Font.PLAIN, 30);
	public Font ExpressionboxText;

	public font() {
		try {
			ExpressionboxText = Font.createFont(Font.TRUETYPE_FONT, new File("lib/Fonts/consola.ttf")).deriveFont(40f);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			System.out.println("ERROR Getting FONT");
		}
	}
}
