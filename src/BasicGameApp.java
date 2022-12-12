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
    public Image ballPic;
    public Image peterPic;
    public Image backgroundPic;
    //GOAL icon
    public Image giconPic;
    //goals
    public Image goal1Pic;
    public Image goal2Pic;





    public Characters rick;
    public Characters ball;
    public Characters peter;
    //just a box for now but serves as goal will not move
    public Characters goal1;
    public Characters goal2;


    //the scores
    public int scoregoal1 = 0;
    public int scoregoal2 = 0;

    public int goalTimer = 0;
    public boolean goalScored = false;

    //for the crash method

    public boolean PisTopCrashing = false;
    public boolean PisBottomCrashing = false;
    public boolean PisLeftCrashing = false;
    public boolean PisRightCrashing = false;

    public boolean RisTopCrashing = false;
    public boolean RisBottomCrashing = false;
    public boolean RisLeftCrashing = false;
    public boolean RisRightCrashing = false;

    double xr = Math.random();
    double yr = Math.random();

    public SoundFile goalceli;
    public SoundFile background;






    //Declare the objects used in the program
    //These are things that are made up of more than one variable type


    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();
    }

    public BasicGameApp() {
        setUpGraphics();

        //goalie 1

        rickPic = Toolkit.getDefaultToolkit().getImage("Rick4.png");
        rick = new Characters("Rick",70,450,0,7);

        //goalie 2

        peterPic = Toolkit.getDefaultToolkit().getImage("realpeter.png");
        peter = new Characters("peter",830,250,0,-7);


        ballPic = Toolkit.getDefaultToolkit().getImage("ball.png");
        ball = new Characters("Morty",(int)(Math.random()*800+100),(int)(Math.random()*500+100),8,4);

        ball.dy = 4;
        ball.dx = 8;

        System.out.println(ball.dy);

        if(yr > .5){
            ball.dy = -ball.dy;
        }

        if(xr > .5){
            ball.dx = -ball.dx;
        }


        backgroundPic = Toolkit.getDefaultToolkit().getImage("SoccerFeild.jpeg");

        goal1 = new Characters("Goal",0,250,0,0);
        goal1Pic = Toolkit.getDefaultToolkit().getImage("soccergoal.png");
        goal1.width=70;
        goal1.height=200;
        goal2 = new Characters("Goal",930,250,0,0);
        goal2Pic = Toolkit.getDefaultToolkit().getImage("soccergoal copy.png");
        goal2.width=70;
        goal2.height=200;



        //GOAL icon
        giconPic = Toolkit.getDefaultToolkit().getImage("GOAL.png");

        goalceli = new SoundFile("VTLFBS5-goal-celebration.wav");
        background = new SoundFile("Background.wav");

        //background.play();







    }

    public void run() {


        while (true) {
            moveThings();  //move all the game objects
            crash();
            //what happens when there is a goal
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
        if (goalScored == false){
            ball.bounce();
        }
        //smaler bounce so goalie dosn't go all the way to the top
        rick.goaliebounce();
        peter.goaliebounce();
        goal1.bounce();
        goal2.bounce();

    }

    public void goal(){
        if(ball.hitBox.intersects(goal1.rightHitBox)){
            goalScored = true;
            System.out.println("GOAL");
            scoregoal1 = scoregoal1 +1;
            System.out.println("Peter " + scoregoal1 + " Rick " + scoregoal2);
            ball.xpos = 450;
            ball.ypos = 300;
            ball.dx = -ball.dx;
            ball.dy = -ball.dy;
            ball.hitBox.x = ball.xpos;
            ball.hitBox.y = ball.ypos;
            goalceli.play();


        }

        if(ball.hitBox.intersects(goal1.topHitBox)){
            ball.dy = -ball.dy;

        }
        if(ball.hitBox.intersects(goal1.bottomHitBox)){
            ball.dy = -ball.dy;

//            ball.bounce();

        }
        if(ball.hitBox.intersects(goal2.leftHitBox)){
            goalScored = true;
            System.out.println("GOAL");
            scoregoal2 = scoregoal2 +1;
            System.out.println("Peter " + scoregoal1 + " Rick " + scoregoal2);
            ball.xpos = 450;
            ball.ypos = 300;
            ball.dx = -ball.dx;
            ball.dy = -ball.dy;
            ball.hitBox.x = ball.xpos;
            ball.hitBox.y = ball.ypos;
            goalceli.play();
        }

        if(ball.hitBox.intersects(goal2.topHitBox)){
            ball.dy = -ball.dy;

        }
        if(ball.hitBox.intersects(goal2.bottomHitBox)){
            ball.dy = -ball.dy;

        }
        if (goalScored == true) {
            goalTimer++;

        }
        if (goalTimer >= 100) {
            goalTimer = 0;
            goalScored = false;
        }
    }

    public void crash(){
        //ball intersects rick left hitboxes
        if (ball.hitBox.intersects(rick.leftHitBox) && RisLeftCrashing == false){
            ball.dx = -ball.dx;
            RisLeftCrashing = true;
        }
        if(!ball.hitBox.intersects(rick.leftHitBox)){
            RisLeftCrashing = false;
        }
        //ball intersect rick right hitboxes
        if (ball.hitBox.intersects(rick.rightHitBox) && RisRightCrashing == false){
            ball.dx = -ball.dx;
           RisRightCrashing = true;
        }
        if(!ball.hitBox.intersects(rick.rightHitBox)){
            RisRightCrashing = false;
        }

        //ball intersects rick top hitboxes

        if (ball.hitBox.intersects(rick.topHitBox) && RisTopCrashing == false){
            ball.dy = -ball.dy;
            RisTopCrashing = true;
        }
        if(!ball.hitBox.intersects(rick.topHitBox)){
            RisTopCrashing = false;
        }

        //ball intersects rick bottom hitboxs

        if (ball.hitBox.intersects(rick.bottomHitBox) && RisBottomCrashing == false){
            ball.dy = -ball.dy;
            RisBottomCrashing = true;
        }
        if(!ball.hitBox.intersects(rick.bottomHitBox)){
            RisBottomCrashing = false;
        }

        //ball intersects peter left hitboxes
        if (ball.hitBox.intersects(peter.leftHitBox) && PisLeftCrashing == false){
            ball.dx = -ball.dx;
            PisLeftCrashing = true;
        }
        if(!ball.hitBox.intersects(peter.leftHitBox)){
            PisLeftCrashing = false;
        }
        //ball intersect peter right hitboxes
        if (ball.hitBox.intersects(peter.rightHitBox) && PisRightCrashing == false){
            ball.dx = -ball.dx;
            PisRightCrashing = true;
        }
        if(!ball.hitBox.intersects(peter.rightHitBox)){
            PisRightCrashing = false;
        }

        //ball intersects peter top hitboxes

        if (ball.hitBox.intersects(peter.topHitBox) && PisTopCrashing == false){
            ball.dy = -ball.dy;
            PisTopCrashing = true;
        }
        if(!ball.hitBox.intersects(peter.topHitBox)){
            PisTopCrashing = false;
        }

        //ball intersects peter bottom hitboxs

        if (ball.hitBox.intersects(peter.bottomHitBox) && PisBottomCrashing == false){
            ball.dy = -ball.dy;
            PisBottomCrashing = true;
        }
        if(!ball.hitBox.intersects(peter.bottomHitBox)){
            PisBottomCrashing = false;
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

        //background image

        g.drawImage(backgroundPic, 0,0, WIDTH,HEIGHT,null);

        //show score

        g.setColor(Color.BLACK);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 100));

        g.drawString(String.valueOf(scoregoal1),550,600);
        g.drawString(String.valueOf(scoregoal2),400,600);


        //draw the image of the charecters
        g.drawImage(ballPic, ball.xpos, ball.ypos, ball.width, ball.height, null);
        g.drawImage(rickPic, rick.xpos, rick.ypos, rick.width, rick.height, null);
        g.drawImage(peterPic, peter.xpos, peter.ypos,peter.width,peter.height, null);
        //goals
        g.drawImage(goal1Pic, goal1.xpos, goal1.ypos,goal1.width,goal1.height, null);
        g.drawImage(goal2Pic, goal2.xpos, goal2.ypos,goal2.width,goal2.height, null);


        //charecters hitbox

        //rick hitboxs

//        g.setColor(Color.BLUE);
//        g.drawRect(rick.hitBox.x, rick.hitBox.y, rick.hitBox.width, rick.hitBox.height);
//        g.setColor(Color.RED);
//        g.drawRect(rick.rightHitBox.x, rick.rightHitBox.y, rick.rightHitBox.width, rick.rightHitBox.height);
//        g.setColor(Color.GREEN);
//        g.drawRect(rick.leftHitBox.x, rick.leftHitBox.y, rick.leftHitBox.width, rick.leftHitBox.height);
//        g.setColor(Color.BLACK);
//        g.drawRect(rick.topHitBox.x, rick.topHitBox.y, rick.topHitBox.width, rick.topHitBox.height);
//        g.setColor(Color.YELLOW);
//        g.drawRect(rick.bottomHitBox.x, rick.bottomHitBox.y, rick.bottomHitBox.width, rick.bottomHitBox.height);
//
//        //peter hitboxes
//        g.setColor(Color.BLUE);
//        g.drawRect(peter.hitBox.x, peter.hitBox.y, peter.hitBox.width, peter.hitBox.height);
//        g.setColor(Color.RED);
//        g.drawRect(peter.rightHitBox.x, peter.rightHitBox.y, peter.rightHitBox.width, peter.rightHitBox.height);
//        g.setColor(Color.GREEN);
//        g.drawRect(peter.leftHitBox.x, peter.leftHitBox.y, peter.leftHitBox.width, peter.leftHitBox.height);
//        g.setColor(Color.BLACK);
//        g.drawRect(peter.topHitBox.x, peter.topHitBox.y, peter.topHitBox.width, peter.topHitBox.height);
//        g.setColor(Color.YELLOW);
//        g.drawRect(peter.bottomHitBox.x, peter.bottomHitBox.y, peter.bottomHitBox.width, peter.bottomHitBox.height);
//
//        //ball hitboxes
//
//        g.setColor(Color.RED);
//        g.drawRect(ball.rightHitBox.x, ball.rightHitBox.y, ball.rightHitBox.width, ball.rightHitBox.height);
//        g.setColor(Color.GREEN);
//        g.drawRect(ball.leftHitBox.x, ball.leftHitBox.y, ball.leftHitBox.width, ball.leftHitBox.height);
//        g.setColor(Color.BLACK);
//        g.drawRect(ball.topHitBox.x, ball.topHitBox.y, ball.topHitBox.width, ball.topHitBox.height);
//        g.setColor(Color.YELLOW);
//        g.drawRect(ball.bottomHitBox.x, ball.bottomHitBox.y, ball.bottomHitBox.width, ball.bottomHitBox.height);
//        g.setColor(Color.BLUE);
//        g.drawRect(ball.hitBox.x, ball.hitBox.y, ball.hitBox.width, ball.hitBox.height);
//
//
//        //goal 1 hitboxs
//        g.setColor(Color.BLUE);
//        g.drawRect(goal1.hitBox.x, goal1.hitBox.y, goal1.hitBox.width, goal1.hitBox.height);
//        g.setColor(Color.RED);
//        g.drawRect(goal1.rightHitBox.x, goal1.rightHitBox.y, goal1.rightHitBox.width, goal1.rightHitBox.height);
//        g.setColor(Color.GREEN);
//        g.drawRect(goal1.leftHitBox.x, goal1.leftHitBox.y, goal1.leftHitBox.width, goal1.leftHitBox.height);
//        g.setColor(Color.BLACK);
//        g.drawRect(goal1.topHitBox.x, goal1.topHitBox.y, goal1.topHitBox.width, goal1.topHitBox.height);
//        g.setColor(Color.YELLOW);
//        g.drawRect(goal1.bottomHitBox.x, goal1.bottomHitBox.y, goal1.bottomHitBox.width, goal1.bottomHitBox.height);
//        //goal 2 hitboxes
//        g.setColor(Color.BLUE);
//        g.drawRect(goal2.hitBox.x, goal2.hitBox.y, goal2.hitBox.width, goal2.hitBox.height);
//        g.setColor(Color.RED);
//        g.drawRect(goal2.rightHitBox.x, goal2.rightHitBox.y, goal2.rightHitBox.width, goal2.rightHitBox.height);
//        g.setColor(Color.GREEN);
//        g.drawRect(goal2.leftHitBox.x, goal2.leftHitBox.y, goal2.leftHitBox.width, goal2.leftHitBox.height);
//        g.setColor(Color.BLACK);
//        g.drawRect(goal2.topHitBox.x, goal2.topHitBox.y, goal2.topHitBox.width, goal2.topHitBox.height);
//        g.setColor(Color.YELLOW);
//        g.drawRect(goal2.bottomHitBox.x, goal2.bottomHitBox.y, goal2.bottomHitBox.width, goal2.bottomHitBox.height);

        //goal Icon


        if(goalTimer > 0){
            g.drawImage(giconPic,300,0,400,200,null);
        }







        g.dispose();
        bufferStrategy.show();
    }
}

