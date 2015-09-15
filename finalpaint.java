// finalpaint.java
// This program is my 2nd recreation of Paint.
// In this program, mouselistener is used


//the following statements import tools needed to run this applet
import java.awt.*;
import java.awt.event.*;
import java.applet.*;


//this paint program is called finalpaint
public class finalpaint extends Panel implements MouseListener, MouseMotionListener //this forms the applet, as well as gives the user to access mouselistener
{


///////////////////////////////////////////////////////////////////////////////////////////////
//Welcome to Raheel's finalpaint Program!//////////////////////////////////////////////////////
//the following program statements are non-method intializers//////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

 //image initializer
///////////////////////////////////////////////////////////////////////////////////////////////
//the following colors don't exist within Javadocs, so they must be created using RGB//////////
///////////////////////////////////////////////////////////////////////////////////////////////

Color brown = new Color(205,133,63);    //(int R, int G, int B)
Color purple = new Color(153,50,204);
Color pink = new Color(255,105,180);
Color red = Color.red;
Color Colorselection, fillColor;

///////////////////////////////////////////////////////////////////////////////////////////////
//the following variables represent integers that dont change value////////////////////////////
 private final static int   //the key word is final, meaning that these variables dont change//

 			 //colors
             Red = 0,
             Green = 1,
             Blue = 2,
             Black = 3,
             Brown = 4,
             Yellow = 6,
             Orange = 7,
             Purple = 8,
             Gray = 9,
             Pink = 10,
             Eraser = 11,
             Clear = 12,

             //tools
             pen = 13,
             brush1 = 14,
             brush2 = 15,
             brush3 = 16,
             roller1 = 17,
             roller2 = 18,
             roller3 = 19,
             seizure = 20,
             drawline = 21,
             drawcirc = 22,
             fillColorOption = 23,
             previousTool = 24;

//the following variables represent integers that change in value in future methods////
private static int xCoord, yCoord, loopcoordX;


 /* The following variables are used when the user is sketching a
    curve while dragging a mouse. */


 private int prevX, prevY;
 private boolean dragging, clicking, paintinside;      // This is set to true while the user is drawing.
 private Graphics graphicsForDrawing;  // A graphics context for the applet
  int currentColor = Black;
  int currentTool = pen;                                     // that is used to draw the user's curve.

//the following method is a **void** method
	//the following method adds mouselistener
 	public void init()
 	{

 		addMouseListener(this); addMouseMotionListener(this);
 	}



	public void paint(Graphics g)
	{
 		int width = getSize().width;
 		int height = getSize().height;

 	///////////////////////////////////////////
 	/*////////*/ drawVisuals(); /*///////////*/                //this methods creates the visual aspects of paint
 	///////////////////////////////////////////
	} // end paint()









 	public void mousePressed (MouseEvent evt) //when the mouse is pressed (release isn't neccesary), the following programs statments will occur
   	{

      	int x = evt.getX();   // x-coordinate where the user clicked.
      	int y = evt.getY();   // y-coordinate where the user clicked.
      	int width = getSize().width;
      	xCoord = x;
      	yCoord = y;
      	prevX = x;
      	prevY = y;

        Graphics g = getGraphics();        // sets up Graphics class
        setupColorOptions();               // calls color switch case

		if (x>110 && y>10 && y<450)       //dragging is true while the mouse is clicking inside the drawing board
			{ dragging = true;}



    //if color is clicked, a certain color will be selected
    if(x>10 && x<50 && y>10 && y<110)
    	{currentColor = Red;}
    else if (x>10 && x<100 && y>10 && y<110)
    	{currentColor = Green; }
    else if (x>10 && x<50 && y>120 && y<220)
    	{currentColor = Blue; }
    else if (x>10 && x<100 && y>120 && y<220)
    	{currentColor = Black; }
    else if (x>10 && x<50 && y>230 && y<330)
    	{currentColor = Brown; }
    else if (x>10 && x<100 && y>230 && y<330)
    	{currentColor = Yellow; }
    else if (x>10 && x<50 && y>340 && y<440)
    	{currentColor = Orange; }
    else if (x>10 && x<100 && y>340 && y<440)
    	{currentColor = Purple; }
    else if (x>10 && x<50 && y>450 && y<550)
    	{currentColor = Gray; }
    else if (x>10 && x<100 && y>450 && y<550)
    	{currentColor = Pink; }
    else if (x>10 && x<50 && y>560 && y<590)
    	{currentColor = Eraser; }
    else if (x>10 && x<100 && y>560 && y<590)
    	{repaint(); }

    //if tool is clicked, tool will be selected
    if (x>110 && x<180 && y>460 && y<590)
    	{currentTool = pen;}
    else if (x>190 && x<270 && y>460 && y<590)
    	{currentTool = brush1;}
    else if (x>280 && x<320 && y>460 && y<550)
    	{currentTool = brush2;}
    else if (x>280 && x<320 && y>550 && y<590)
    	{currentTool = brush3;}
    else if (x>340 && x<480 && y>460 && y<590)
    	{currentTool = roller1;}
    else if (x>480 && x<570 && y>460 && y<540)
    	{currentTool = roller2;}
    else if (x>480 && x<570 && y>540 && y<590)
    	{currentTool = roller3;}
    else if (x>680 && x<740 && y>460 && y<540)
    	{currentTool = seizure;}
    else if (x>760 && x<820 && y>460 && y<540)
    	{currentTool = drawline;}
    else if (x>840 && x<900 && y>460 && y<540)
    	{currentTool = drawcirc;}


    else if (x>600 && y>460 && x<660 && y<540 && clicking == false && dragging == false)           // in this case, if fill is clicked, the screen will be filled with the currentcolor
    {

    	fillColor = Colorselection;
        g.setColor(fillColor);
        g.fillRect(111,11,width,439);
    }

 } // end mousePressed()

 public void mouseReleased(MouseEvent evt)      //when the mouse is released, the following actions will stop the mouse from doing things that it does while dragging or clicking
 	{
   		int x = evt.getX();   // x-coordinate where the user clicked.
   		int y = evt.getY();   // y-coordinate where the user clicked.

   		clicking = false;               //when the mouse is release, the program statements from mouseClick are stopped
   		dragging = false;               //when the mouse is release, the program statements from mouseDragged are stopped
   		graphicsForDrawing.dispose();
   		graphicsForDrawing = null;
 	}

 public void mouseDragged(MouseEvent evt)     //the following program statements will occur when the mouse is dragged
 	{
     	int x = evt.getX();                   // x-coordinate of mouse when dragged.
     	int y = evt.getY();                   // y=coordinate of mouse when dragged.
     	int width = getSize().width;          // width
  	 	int height = getSize().height;        // height
    	int dragX = evt.getX();               // x-coordinate of mouse when dragged.
    	int dragY = evt.getY();               // y=coordinate of mouse when dragged.

  		Graphics g = getGraphics();
  		if (x>110 && y>10 && y<450)                                     //dragging is only true when the current tool is used inside the drawing board
  			{dragging = true;}

        if (dragging == true && prevX>110 && prevY>10 && prevY<450 && x>110 && y>10)     //if dragging is true and a tool is selected, that tool will be used
     		{
       			if (currentTool == pen  && y<450)            //pen
       				{
  						g.setColor(Colorselection);
       					g.drawLine(prevX,prevY,x,y);
       					prevX = x;
       					prevY = y;
  					}

  				else if (currentTool == brush1  && y<400)    //big brush
  					{
  						g.setColor(Colorselection);
  						g.fillRect(x,y,50,50);
  					}

  				else if (currentTool == brush2 && y<420) 	 //medium brush
  					{
  						g.setColor(Colorselection);
  						g.fillRect(x,y,30,30);
  					}

  				else if (currentTool == brush3  && y <435)   //small brush
  					{
  						g.setColor(Colorselection);
  						g.fillRect(x,y,15,15);
  					}

  				else if (currentTool == roller1  && y<400)   //big roller
  					{
  						g.setColor(Colorselection);
     					g.fillOval(x,y,50,50);
  					}

  				else if (currentTool == roller2  && y<420)   //medium roller
  					{
  						g.setColor(Colorselection);
  	    				g.fillOval(x,y,30,30);
  					}

  				else if (currentTool == roller3  && y <435)  //small roller
  					{
  						g.setColor(Colorselection);
        				g.fillOval(x,y,15,15);
  					}

  				else if (currentTool == drawline & y<450)		 //3D lines
        			{
						int red1 = Expo.random(0,255);
						int green1 = Expo.random(0,255);
						int blue1 = Expo.random(0,255);
						Expo.setColor(g,red1,green1,blue1);
						int random = Expo.random(0,10100);
						g.drawLine(x,y,x + y,y + 0);
        			}

        		else if (currentTool == drawcirc && y<375)		 //random circles
        			{
						int red = Expo.random(0,255);
						int green = Expo.random(0,255);
						int blue = Expo.random(0,255);
						int widetall = Expo.random(25,75);
						Expo.setColor(g,red,green,blue);
						g.fillOval(x,y,widetall,widetall);
        			}

        		else if (currentTool == seizure && y<225)		 //seizure rectangle (upper half of drawing board)
        			{
        				g.setColor(Colorselection);
						int red1 = Expo.random(0,255);
						int green1 = Expo.random(0,255);
						int blue1 = Expo.random(0,255);
						Expo.setColor(g,red1,green1,blue1);
						int random = Expo.random(0,10100);
						g.fillRect(x,y,x + y,y);

        		    }
        		 else if (currentTool == seizure && y>225 && y<450)		 //seizure rectangle (lower half of drawing board)
        		    {
        				g.setColor(Colorselection);
						int red1 = Expo.random(0,255);
						int green1 = Expo.random(0,255);
						int blue1 = Expo.random(0,255);
						Expo.setColor(g,red1,green1,blue1);
						int random = Expo.random(0,10100);
						g.fillRect(x,y-(y-225),x + y,y-(y-225));

        		    }
   }


      		if (dragging == false)                           // if nothing is dragged, or the mouse is dragged out of bounds, nothing will occur
      		{return;}

 } // end mouseDragged.

 	public void mouseClicked (MouseEvent evt)                  //when the mouse is pressed (release is neccesary in this method), the following programs statments will occur
 	{

 		///mouseClicked methods initializers//////////////////////////////////////////
    	int x = evt.getX();                           // x-coordinate of mouse.///////
    	int y = evt.getY();                           // y=coordinate of mouse.///////
    	int width = getSize().width;                  //width of applet///////////////
    	int height = getSize().height;                //height of applet//////////////
    	Graphics g = getGraphics();                   //retrieves graphics class//////
    	//////////////////////////////////////////////////////////////////////////////

    	if (x>110 && y>10 && y<450)  //clicking is only true when the current tool is used inside the drawing board
     	{ clicking = true;}


		if (clicking == true)        //if clicking is true and a tool is selected, that tool will be used
		{
       			if (currentTool == pen  && y<450)            //pen
       				{
  						g.setColor(Colorselection);
       					g.drawLine(prevX,prevY,x,y);
       					prevX = x;
       					prevY = y;
  					}

  				else if (currentTool == brush1  && y<400)    //big brush
  					{
  						g.setColor(Colorselection);
  						g.fillRect(x,y,50,50);
  					}

  				else if (currentTool == brush2 && y<420) 	 //medium brush
  					{
  						g.setColor(Colorselection);
  						g.fillRect(x,y,30,30);
  					}

  				else if (currentTool == brush3  && y <435)   //small brush
  					{
  						g.setColor(Colorselection);
  						g.fillRect(x,y,15,15);
  					}

  				else if (currentTool == roller1  && y<400)   //big roller
  					{
  						g.setColor(Colorselection);
     						g.fillOval(x,y,50,50);
  					}

  				else if (currentTool == roller2  && y<420)   //medium roller
  					{
  						g.setColor(Colorselection);
  	    					g.fillOval(x,y,30,30);
  					}

  				else if (currentTool == roller3  && y <435)  //small roller
  					{
  						g.setColor(Colorselection);
        					g.fillOval(x,y,15,15);
  					}

  				else if (currentTool == drawline & y<450)		 //3D lines
        				{
						int red1 = Expo.random(0,255);
						int green1 = Expo.random(0,255);
						int blue1 = Expo.random(0,255);
						Expo.setColor(g,red1,green1,blue1);
						int random = Expo.random(0,10100);
						g.drawLine(x,y,x + y,y + 0);
        				}

        			else if (currentTool == drawcirc && y<375)		 //random circles
        				{
						int red = Expo.random(0,255);
						int green = Expo.random(0,255);
						int blue = Expo.random(0,255);
						int widetall = Expo.random(25,75);
						Expo.setColor(g,red,green,blue);
						g.fillOval(x,y,widetall,widetall);
        				}

        			else if (currentTool == seizure && y<225)		 //seizure rectangle (upper half of drawing board)
        				{
        					g.setColor(Colorselection);
						int red1 = Expo.random(0,255);
						int green1 = Expo.random(0,255);
						int blue1 = Expo.random(0,255);
						Expo.setColor(g,red1,green1,blue1);
						int random = Expo.random(0,10100);
						g.fillRect(x,y,x + y,y);

        		    		}

        		 	else if (currentTool == seizure && y>225 && y<450)		 //seizure rectangle (lower half of drawing board)
        		    		{
        					g.setColor(Colorselection);
						int red1 = Expo.random(0,255);
						int green1 = Expo.random(0,255);
						int blue1 = Expo.random(0,255);
						Expo.setColor(g,red1,green1,blue1);
						int random = Expo.random(0,10100);
						g.fillRect(x,y-(y-225),x + y,y-(y-225));

        		    		}

  		}

    		if (clicking == false)                                            // if nothing is clicked, nothing will occur
    		{return;}

 	}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//the following methods were placed at the bottom of the program, because they don't need debugging //////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	private void remakePalletBorders () //remakes black borders for colors
	 {
  		Graphics g = getGraphics();
  		g.setColor(Color.black);
  		g.drawRect(10,10,40,100);
  		g.drawRect(60,10,40,100);
  		g.drawRect(10,120,40,100);
  		g.drawRect(60,120,40,100);
  		g.drawRect(10,230,40,100);
  		g.drawRect(60,230,40,100);
  		g.drawRect(10,340,40,100);
  		g.drawRect(60,340,40,100);
  		g.drawRect(10,450,40,100);
  		g.drawRect(60,450,40,100);
  		g.drawRect(10,560,40,30);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  private void setupColorOptions () //called upon in the mouse pressed method
  	{
   		Graphics g = getGraphics();
    	switch (currentColor)             //if this switchcase (currentcolor) is called, if a certain color is chosen, its borders will be remade, and it will be the current color
     	{
        	case Red:
        	remakePalletBorders();
        	g.setColor(Color.white);
     		g.drawRect(10,10,40,100);
        	g.setColor(Color.red);
        	Colorselection = Color.red;
        	break;

        	case Green:
        	remakePalletBorders();
       		g.setColor(Color.white);
       		g.drawRect(60,10,40,100);
        	g.setColor(Color.green);
        	Colorselection = Color.green;
        	break;

        	case Blue:
        	remakePalletBorders();
        	g.setColor(Color.white);
        	g.drawRect(10,120,40,100);
        	g.setColor(Color.blue);
        	Colorselection = Color.blue;
        	break;

        	case Black:
        	remakePalletBorders();
      		g.setColor(Color.white);
       		g.drawRect(60,120,40,100);
        	g.setColor(Color.black);
        	Colorselection = Color.black;
       	 	break;

        	case Brown:
        	remakePalletBorders();
      		g.setColor(Color.white);
        	g.drawRect(10,230,40,100);
        	g.setColor(brown);
        	Colorselection = brown;
        	break;

        	case Yellow:
        	remakePalletBorders();
      		g.setColor(Color.white);
       		g.drawRect(60,230,40,100);
        	g.setColor(Color.yellow);
        	Colorselection = Color.yellow;
        	break;

        	case Orange:
        	remakePalletBorders();
        	g.setColor(Color.white);
        	g.drawRect(10,340,40,100);
        	g.setColor(Color.orange);
        	Colorselection = Color.orange;
        	break;

        	case Purple:
        	remakePalletBorders();
        	g.setColor(Color.white);
        	g.drawRect(60,340,40,100);
        	g.setColor(purple);
        	Colorselection = purple;
        	break;

        	case Gray:
        	remakePalletBorders();
        	g.setColor(Color.white);
        	g.drawRect(10,450,40,100);
        	g.setColor(Color.gray);
        	Colorselection = Color.gray;
        	break;

        	case Pink:
        	remakePalletBorders();
        	g.setColor(Color.white);
        	g.drawRect(60,450,40,100);
        	g.setColor(pink);
        	Colorselection = pink;
        	break;

        	case Eraser:
        	remakePalletBorders();
        	g.setColor(Color.white);
        	g.drawRect(10, 560, 40, 30);
        	Colorselection = Color.white;
        	break;

     } //end of color option switchcase
  }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
								//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 	public void drawVisuals()   //this method is called in the paint method///////////////////////////////////////////////////////////////////////////////////
 {                              //statements in this method are just images that appear when paint is opened//////////////////////////////////////////////////
 	Graphics g = getGraphics();
    int width = getSize().width;      //width of applet
    int height = getSize().height;    ///height of applet

    //grey background
    g.setColor(Color.gray);
	g.fillRect(0,0,width,height);
	//white drawing bord
	g.setColor(Color.white);
	g.fillRect(110,10,width-110,440);

	//border around white drawing board
 	g.setColor(Color.black);
    Polygon border = new Polygon();
    border.addPoint(width,450);
    border.addPoint(110,450);
    border.addPoint(110,10);
    border.addPoint(width,10);
    g.drawPolygon(border);

	// 7 rectangles
	g.setColor(Color.red);         //red
	g.fillRect(10,10,40,100);
	g.setColor(Color.green);       //green
	g.fillRect(60,10,40,100);
	g.setColor(Color.blue);        //blue
	g.fillRect(10,120,40,100);
	g.setColor(Color.black);       //black
	g.fillRect(60,120,40,100);
	g.setColor(brown);             //brown
	g.fillRect(10,230,40,100);
	g.setColor(Color.yellow);      //yellow
	g.fillRect(60,230,40,100);
	g.setColor(Color.orange);      //orange
	g.fillRect(10,340,40,100);
	g.setColor(purple);            //purple
	g.fillRect(60,340,40,100);
	g.setColor(Color.gray);        //gray
	g.fillRect(10,450,40,100);
	g.setColor(Color.pink);        //pink
	g.fillRect(60,450,40,100);

    //eraser
    g.setColor(Color.pink);
    g.fillRect(10, 560, 40, 30);
    g.drawRect(10, 560, 40, 30);
    g.setColor(Color.black);
    g.drawString("ERASE", 11, 577);

    //clear
    g.setColor(Color.white);
    g.fillRect(60, 560, 40, 30);
    g.setColor(Color.black);
    g.drawRect(60, 560, 40, 30);
    g.drawString("CLEAR", 61, 577);

	//pallet borders
	remakePalletBorders ();

	//pen
	g.setColor(Color.pink);
	g.fillArc(120,460,40,40,180,-180);
	g.setColor(Color.yellow);
	g.fillRect(120,480,40,80);
	g.setColor(brown);
	Polygon bigtriangle = new Polygon();
	bigtriangle.addPoint(120,560);
	bigtriangle.addPoint(160,560);
	bigtriangle.addPoint(140,590);
	g.fillPolygon(bigtriangle);
	g.setColor(Color.black);
	Polygon littletriangle = new Polygon();
	littletriangle.addPoint(130,575);
	littletriangle.addPoint(150,575);
	littletriangle.addPoint(140,590);
	g.fillPolygon(littletriangle);

	//brush1
	g.setColor(Color.black);
	g.fillRect(220,460,30,45);
	g.setColor(brown);
	g.fillArc(200,500,40,120,180,-90);
	g.fillArc(230,500,40,120,0,90);
	g.fillRect(220,500,30,60);
	g.setColor(Color.yellow);
	g.fillRect(200,560,70,30);
	g.setColor(Color.red);
	loopcoordX = 200;
	for (int line = 1; line <= 36; line++)
	{
	 	g.drawLine(loopcoordX,560,loopcoordX,590);
	 	loopcoordX += 2;
	}

	//brush2
	g.setColor(Color.black);
	g.fillRect(290,460,20,30);
	g.setColor(brown);
	g.fillArc(280,490,40,80,180,-90);
	g.fillArc(280,490,40,80,0,90);
	g.fillRect(280,520,40,10);
	g.setColor(Color.yellow);
	g.fillRect(280,530,40,20);
	g.setColor(Color.red);
	loopcoordX = 280;
	for (int line = 1; line <= 21; line++)
	{
	 	g.drawLine(loopcoordX,530,loopcoordX,550);
	 	loopcoordX += 2;
	}

	//brush3
	g.setColor(brown);
	g.fillArc(290,565,20,40,180,-90);
	g.fillArc(290,565,20,40,0,90);
	g.setColor(Color.black);
	g.fillRect(295,560,10,10);
	g.setColor(Color.yellow);
	g.fillRect(290,580,20,10);
	g.setColor(Color.red);
	loopcoordX = 290;
	for (int line = 1; line <= 11; line++)
	{
	 g.drawLine(loopcoordX,580,loopcoordX,590);
	 loopcoordX += 2;
	}

	//roller1
	g.setColor(brown);
	g.fillRect(430,470,40,20);
	g.fillRect(460,470,10,50);
	g.fillRect(410,510,60,10);
	g.fillRect(410,510,10,80);
	g.setColor(Color.blue);
	g.fillOval(340,460,40,40);
	g.fillRect(360,460,60,40);
	g.fillOval(400,460,40,40);
	g.setColor(Color.black);
	g.drawOval(340,460,40,40);
	g.drawArc(400,460,40,40,90,-180);
	g.drawLine(360,460,420,460);
	g.drawLine(360,500,420,500);

	//roller2
	g.setColor(brown);
	g.fillRect(530,470,30,10);
	g.fillRect(550,470,10,30);
	g.fillRect(520,490,40,10);
	g.fillRect(520,495,10,45);
	g.setColor(Color.blue);
	g.fillOval(480,460,20,20);
	g.fillRect(490,460,40,20);
	g.fillOval(520,460,20,20);
	g.setColor(Color.black);
	g.drawOval(480,460,20,20);
	g.drawArc(520,460,20,20,90,-180);
	g.drawLine(490,460,530,460);
	g.drawLine(490,480,530,480);

	//roller3
	g.setColor(brown);
	g.fillRect(520,550,10,30);
	g.fillRect(500,570,30,10);
	g.fillRect(500,570,10,20);
	g.setColor(Color.blue);
	g.fillOval(480,550,10,10);
	g.fillRect(490,550,30,10);
	g.fillOval(510,550,10,10);
	g.setColor(Color.black);
	g.drawOval(480,550,10,10);
	g.drawArc(510,550,10,10,90,-180);
	g.drawLine(490,550,520,550);
	g.drawLine(490,560,520,560);

	//fillbox
	g.setColor(Color.white);
	g.fillRect(600,460,60,80);
	g.setColor(Color.black);
	g.drawString("FILL",620,500);

	//drawseizure
	g.setColor(Color.white);
	g.fillRect(680,460,60,80);
	g.setColor(Color.black);
	g.drawString("OPTIC",695,500);
	g.drawString("TORTURE",680,510);

	//draw3Dlines
	g.setColor(Color.white);
	g.fillRect(760,460,60,80);
	g.setColor(Color.black);
	g.drawString("DRAW",775,500);
	g.drawString("3D",775,510);
	g.drawString("LINE",775,520);

	//drawCircles
	g.setColor(Color.white);
	g.fillRect(840,460,60,80);
	g.setColor(Color.black);
	g.drawString("DRAW",855,500);
	g.drawString("CIRC",855,510);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

 } // end draw()

 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 // the following are extra methods needed to use mouseListener///////////////////////////////////////////////////////////////////////////////////////////////////////////////
 // when making paint, none of the following methods need program/////////////////////////////////////////////////////////////////////////////////////////////////////////////
 // statements, and they should be left blank.////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

 public void mouseEntered(MouseEvent evt) { }           //called when the mouse enters a certain area in the applet
 public void mouseExited(MouseEvent evt) { } 			//called when the mouse exists a certain area in the applet
 public void mouseMoved(MouseEvent evt) { } 			//called when mouse is moved through a certain area in the applet

  public static void main(String[] args)
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




 //end finalpaint()//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////the program is now finished, run the HTML file of this to see paint/////////////////////////////////////////////////////////////////////////////////////////////////
//////////enjoy!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!/////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////