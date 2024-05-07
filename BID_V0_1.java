package blades_in_doomsdays;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BID_V0_1 {
    static boolean state=true;
    static JLabel lab=new JLabel("player");
    static int xspeed=0,yspeed=0,mousex=0,mousey=0;
    static int step=10;
    static public void setSpeed(MouseEvent e){
        int lx=lab.getX(),mx=e.getX();
        int ly=lab.getY(),my=e.getY();
        mousex=mx;
        mousey=my;
        int devx=-(lx-mx),devy=-(ly-my);
        devy/=10;
        devx/=10;
        devy= devy>20 ? 20 : devy;
        devx= devx>20 ? 20 : devx;
        System.out.println(devy);
        System.out.println(devx);
        xspeed=devx;
        yspeed=devy;
    }
    static public void main(String[] arg){
        JFrame f=new JFrame("mtm");
        f.setSize(500,500);
        Container con=f.getContentPane();
        con.setLayout(null);
        lab.setBounds(250,250,50,10);
        con.add(lab);
        SwingWorker setpos=new SwingWorker() {
            protected Object doInBackground(){
                while (state){
                    int lx=lab.getX();
                    int ly=lab.getY();
                    if(!((lx-step < mousex && mousex < lx+step) && (ly-step < mousey && mousey < ly+step))){
                        lab.setLocation(lab.getX()+xspeed,lab.getY()+yspeed);}
                    try{
                        Thread.sleep((long)(1000/150));}
                    catch (InterruptedException e){
                        System.out.println("error while spleeping");
                    }
                }
                return null;
            }
        };
        f.addWindowListener(new WindowListener() {
            public void windowClosing(WindowEvent e){}
            public void windowOpened(WindowEvent e){
                setpos.execute();
            }
            public void windowDeiconified(WindowEvent e){}
            public void windowActivated(WindowEvent e){}
            public void windowClosed(WindowEvent e){
                state=false;
            }
            public void windowDeactivated(WindowEvent e){}
            public void windowIconified(WindowEvent e){}
        });
        con.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e){setSpeed(e);}
            public void mouseMoved(MouseEvent e){setSpeed(e);}
        });
        f.setDefaultCloseOperation(3);
        f.setVisible(true);
    }
}
