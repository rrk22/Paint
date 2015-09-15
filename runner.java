import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class runner extends Panel
{
	public runner ()
	{
			Frame f = new Frame();
  f.addWindowListener(new java.awt.event.WindowAdapter() {
       public void windowClosing(java.awt.event.WindowEvent e) {
       System.exit(0);
       };
     });

  finalpaint ut = new finalpaint();
  ut.setSize(1100,700); // same size as defined in the HTML APPLET
  f.add(ut);
  f.pack();
  ut.init();
  f.setSize(1100,700 + 20); // add 20, seems enough for the Frame title,
  f.show();
  }
	}
