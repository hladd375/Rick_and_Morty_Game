import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class BasicGameApp implements Runnable {
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    public Image rickPic;
    public Image mortyPic;
    public Image backgroundPic;

    public Characters rick;
    public Characters morty;
    //just a box for now but serves as goal will not move
    public Characters goal;

    public boolean didCrash = true;

    public int score = 0;

    //Declare the objects used in the program
    //These are things that are made up of more than one variable type


    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();
    }

    public BasicGameApp() {
        setUpGraphics();

        rickPic = Toolkit.getDefaultToolkit().getImage("Rick4.png");
        rick = new Characters("Rick",70,450,0,10);

        mortyPic = Toolkit.getDefaultToolkit().getImage("download.png");
        morty = new Characters("Morty",(int)(Math.random()*800+100),(int)(Math.random()*500+100),6,3);

        backgroundPic = Toolkit.getDefaultToolkit().getImage("SoccerFeild.jpeg");

        goal = new Characters("Goal",0,200,0,0);



    }

    public void run() {

        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            crash();
            goal();
            render();  // paint the graphics
            pause(20); // sleep for 10 ms
        }
    }

    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    public void moveThings() {
        //calls the move( ) code in the objects
        morty.bounce();
        rick.goaliebounce();


    }

    public void goal(){
        if(morty.hitBox.intersects(goal.hitBox)){
            System.out.println("GOAL");
            score = score +1;
            System.out.println(score);
            morty.xpos = (int)(Math.random()*800+100);
            morty.ypos = (int)(Math.random()*500+100);
            morty.dx = -morty.dx;
            morty.dy = -morty.dy;

        }
    }

    public void crash(){
        if (morty.hitBox.intersects(rick.hitBox)){
            morty.dx = -morty.dx;


        }
    }

    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.drawImage(backgroundPic, 0,0, WIDTH,HEIGHT,null);

        //draw the image of the astronaut
        g.drawImage(mortyPic, morty.xpos, morty.ypos, morty.width, morty.height, null);
        g.drawImage(rickPic, rick.xpos, rick.ypos, rick.width, rick.height, null);

        g.drawRect(morty.hitBox.x, morty.hitBox.y, morty.hitBox.width, morty.hitBox.height);
        g.drawRect(rick.hitBox.x, rick.hitBox.y, rick.hitBox.width, rick.hitBox.height);
        g.drawRect(goal.hitBox.x,goal.hitBox.y,70,300);





        g.dispose();
        bufferStrategy.show();
    }
}

