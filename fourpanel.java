package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FourPanel extends JPanel
{
    private int playerX, playerY;
    private int diameter = 100;
    private int numRows = 6, numCols = 7;
    private int turn = 0;
    FourCircle[][] circle;
    
    public FourPanel()
    {
        this.setPreferredSize(new Dimension(720, 700));
        this.setBackground(Color.CYAN);
        this.setLayout(null);
        setCircles();
        
        addMouseListener(new MouseAdapter()
                {
                    public void mouseReleased(MouseEvent e)
                    {
                        dropToken(e.getX(), e.getY());
                    }
                });
        
        addMouseMotionListener(new MouseAdapter()
                {
                    public void mouseDragged(MouseEvent e)
                    {
                        movePlayer(e.getX(), e.getY());
                    }
                });
        
    }

    protected void movePlayer(int x, int y)
    {
        if (playerX == x)
        {
            return;
        }
            repaint();
            playerX = x - 5;
            playerY = 0;
            if(playerX < 0) {playerX = 0;}
            if(playerX>620) {playerX = 620;}
            repaint();
    }

    protected void dropToken(int x, int y)
    {
        if(y>100)
        {
            return;
        }
        
        int red, green, blue;
        if(turn % 2 == 0)
        {
            red = 255;
            green = 0;
            blue = 0;
        }
        else
        {
            red = 20;
            green = 255;
            blue = 0;
        }
        
        int col = 0;
        if(x > 0 && x < 80)
        {
            col = 0;
        }
        else if(x > 85 && x < 185)
        {
            col = 1;
        }
        else if(x > 185 && x < 285)
        {
            col = 2;
        }
        else if(x > 285 && x < 385)
        {
            col = 3;
        }
        else if(x > 385 && x < 485)
        {
            col = 4;
        }
        else if(x > 485 && x < 585)
        {
            col = 5;
        }
        else if(x > 585 && x < 685)
        {
            col = 6;
        }
        
        for(int row = 0; row < numRows; row++)
        {
            if(circle[row][col].getRed() == 0)
            {
                circle[row][col].setRed(red);
                circle[row][col].setGreen(green);
                circle[row][col].setBlue(blue);
                turn++;
                repaint();
                break;
            }
        }
        checkWinner();
    }
    
    private void checkWinner()
    {
        boolean win = false;
        //Check Horizontal
        for(int row = 0; row < numRows; row++)
        {
            for(int col = 0; col < numCols - 3; col++)
            {
                if(circle[row][col].getRed() != 0 &&
                        circle[row][col].getRed() == circle[row][col+1].getRed() &&
                        circle[row][col+1].getRed() == circle[row][col+2].getRed() &&
                        circle[row][col+2].getRed() == circle[row][col+3].getRed() )
                {
                    win = true;
                }
            }
        }
      //Check Vertical
        for(int row = 0; row < numRows - 3; row++)
        {
            for(int col = 0; col < numCols; col++)
            {
                if(circle[row][col].getRed() != 0 &&
                        circle[row][col].getRed() == circle[row+1][col].getRed() &&
                        circle[row+1][col].getRed() == circle[row+2][col].getRed() &&
                        circle[row+2][col].getRed() == circle[row+3][col].getRed() )
                {
                    win = true;
                }
            }
        }
        
        //Check positive slope
        for(int row = 0; row < numRows - 3; row++)
        {
            for(int col = 0; col < numCols - 3; col++)
            {
                if(circle[row][col].getRed() != 0 &&
                        circle[row][col].getRed() == circle[row+1][col+1].getRed() &&
                        circle[row+1][col+1].getRed() == circle[row+2][col+2].getRed() &&
                        circle[row+2][col+2].getRed() == circle[row+3][col+3].getRed() )
                {
                    win = true;
                }
            }
        }
        
      //Check negative slope
        for(int row = 3; row < numRows; row++)
        {
            for(int col = 0; col < numCols - 3; col++)
            {
                if(circle[row][col].getRed() != 0 &&
                        circle[row][col].getRed() == circle[row-1][col+1].getRed() &&
                        circle[row-1][col+1].getRed() == circle[row-2][col+2].getRed() &&
                        circle[row-2][col+2].getRed() == circle[row-3][col+3].getRed() )
                {
                    win = true;
                }
            }
        }
        
        if(win)
        {
            String whoWon = "";
            if(turn % 2 == 1)
            {
                whoWon = "Red is the winner!";
            }
            else
            {
                whoWon = "Green is the winner!";
            }
            
            int restart = JOptionPane.showConfirmDialog(null,"Play Again?", whoWon,
                    JOptionPane.YES_NO_OPTION);
            if(restart == 0)
            {
                startOver();
            }
            else
            {
                System.exit(0);
            }
        }
    }
    private void startOver()
    {
    	for (int row = 0; row < numRows; row++)
    	{
    		for (int col = 0; col < numCols; col++)
    		{
    			circle[row][col].setRed(0);
    			circle[row][col].setGreen(0);
    			circle[row][col].setBlue(0);
    			
    		}
    	}
    	repaint();
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        if(turn % 2 == 0)
        {
            g2d.setColor(Color.RED);
        }
        else
        {
            g2d.setColor(Color.GREEN);
        }
        g2d.fillOval(playerX, playerY, diameter, diameter);
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 100, 720, 700);
        for(int rows = 0; rows<numRows; rows++)
        {
            for(int cols = 0; cols<numCols; cols++)
            {
                g2d.setPaint(new Color(circle[rows][cols].getRed(),
                        circle[rows][cols].getGreen(), 
                        circle[rows][cols].getBlue()));
                g2d.fillOval(circle[rows][cols].getxPos(), 
                        circle[rows][cols].getyPos(), 
                        circle[rows][cols].getDiameter(),
                        circle[rows][cols].getDiameter());
             }
        }
    }

    private void setCircles()
    {
        circle = new FourCircle[numRows][numCols];
        int x = 0, y = 0;
        
        for(int rows = 0; rows < numRows; rows++)
        {
            for(int cols = 0; cols < numCols; cols++)
            {
                y = 600 - (rows * 100);
                x = cols * 100 + 10;
                
                circle[rows][cols] = new FourCircle(x,y);
                circle[rows][cols].setRed(0);
                circle[rows][cols].setGreen(0);
                circle[rows][cols].setBlue(0);
            }
        }
    }

}

