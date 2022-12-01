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
    public Image giconPic;
    //the scores
    public Image a;
    public Image b;
    public Image c;
    public Image d;
    public Image e;
    public Image f;
    public Image gg;
    public Image h;
    public Image i;
    public Image j;



    public Characters rick;
    public Characters ball;
    public Characters peter;
    //just a box for now but serves as goal will not move
    public Characters goal1;
    public Characters goal2;
    //goal icon



    public int scoregoal1 = 0;
    public int scoregoal2 = 0;

    public int goalTimer = 0;
    public boolean goalScored = false;

    public boolean isCrashing = false;


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
        rick = new Characters("Rick",70,450,0,10);

        //goalie 2

        peterPic = Toolkit.getDefaultToolkit().getImage("realpeter.png");
        peter = new Characters("peter",830,450,0,-10);


        ballPic = Toolkit.getDefaultToolkit().getImage("ball.png");
        ball = new Characters("Morty",(int)(Math.random()*800+100),(int)(Math.random()*500+100),6,3);

        backgroundPic = Toolkit.getDefaultToolkit().getImage("SoccerFeild.jpeg");

        goal1 = new Characters("Goal",0,250,0,0);
        goal1.width=70;
        goal1.height=200;
        goal2 = new Characters("Goal",930,250,0,0);
        goal2.width=70;
        goal2.height=200;

        //GOAL icon
        giconPic = Toolkit.getDefaultToolkit().getImage("GOAL.png");

        //score icons
        a = Toolkit.getDefaultToolkit().getImage("0.png");
        b = Toolkit.getDefaultToolkit().getImage("1.png");
        c = Toolkit.getDefaultToolkit().getImage("2.jpeg");
        d = Toolkit.getDefaultToolkit().getImage("3.png");
        e = Toolkit.getDefaultToolkit().getImage("4.jpg");
        f = Toolkit.getDefaultToolkit().getImage("5.jpg");
        gg = Toolkit.getDefaultToolkit().getImage("6.jpg");
        h = Toolkit.getDefaultToolkit().getImage("7.jpg");
        i = Toolkit.getDefaultToolkit().getImage("8.jpg");
        j = Toolkit.getDefaultToolkit().getImage("9.jpeg");





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
        if(ball.hitBox.intersects(goal1.hitBox)){
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
        }
        if(ball.hitBox.intersects(goal2.hitBox)){
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
        if (ball.hitBox.intersects(rick.leftHitBox) && isCrashing == false){
            ball.dx = -ball.dx;
            isCrashing = true;
        }
        if(!ball.hitBox.intersects(rick.leftHitBox)){
            isCrashing = false;
        }
        //ball intersect rick right hitboxes
        if (ball.hitBox.intersects(rick.rightHitBox) && isCrashing == false){
            ball.dx = -ball.dx;
            isCrashing = true;
        }
        if(!ball.hitBox.intersects(rick.rightHitBox)){
            isCrashing = false;
        }

        //ball intersects rick top hitboxes

        if (ball.hitBox.intersects(rick.topHitBox) && isCrashing == false){
            ball.dy = -ball.dy;
            isCrashing = true;
        }
        if(!ball.hitBox.intersects(rick.topHitBox)){
            isCrashing = false;
        }

        //ball intersects rick bottom hitboxs

        if (ball.hitBox.intersects(rick.bottomHitBox) && isCrashing == false){
            ball.dy = -ball.dy;
            isCrashing = true;
        }
        if(!ball.hitBox.intersects(rick.bottomHitBox)){
            isCrashing = false;
        }

        //ball intersects peter left hitboxes
        if (ball.hitBox.intersects(peter.leftHitBox) && isCrashing == false){
            ball.dx = -ball.dx;
            isCrashing = true;
        }
        if(!ball.hitBox.intersects(peter.leftHitBox)){
            isCrashing = false;
        }
        //ball intersect peter right hitboxes
        if (ball.hitBox.intersects(peter.rightHitBox) && isCrashing == false){
            ball.dx = -ball.dx;
            isCrashing = true;
        }
        if(!ball.hitBox.intersects(peter.rightHitBox)){
            isCrashing = false;
        }

        //ball intersects peter top hitboxes

        if (ball.hitBox.intersects(peter.topHitBox) && isCrashing == false){
            ball.dy = -ball.dy;
            isCrashing = true;
        }
        if(!ball.hitBox.intersects(peter.topHitBox)){
            isCrashing = false;
        }

        //ball intersects peter bottom hitboxs

        if (ball.hitBox.intersects(peter.bottomHitBox) && isCrashing == false){
            ball.dy = -ball.dy;
            isCrashing = true;
        }
        if(!ball.hitBox.intersects(peter.bottomHitBox)){
            isCrashing = false;
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

        //draw the image of the charecters
        g.drawImage(ballPic, ball.xpos, ball.ypos, ball.width, ball.height, null);
        g.drawImage(rickPic, rick.xpos, rick.ypos, rick.width, rick.height, null);
        g.drawImage(peterPic, peter.xpos, peter.ypos,peter.width,peter.height, null);


        //charecters hitbox

        //rick hitboxs

        g.setColor(Color.BLUE);
        g.drawRect(rick.hitBox.x, rick.hitBox.y, rick.hitBox.width, rick.hitBox.height);
        g.setColor(Color.RED);
        g.drawRect(rick.rightHitBox.x, rick.rightHitBox.y, rick.rightHitBox.width, rick.rightHitBox.height);
        g.setColor(Color.GREEN);
        g.drawRect(rick.leftHitBox.x, rick.leftHitBox.y, rick.leftHitBox.width, rick.leftHitBox.height);
        g.setColor(Color.BLACK);
        g.drawRect(rick.topHitBox.x, rick.topHitBox.y, rick.topHitBox.width, rick.topHitBox.height);
        g.setColor(Color.YELLOW);
        g.drawRect(rick.bottomHitBox.x, rick.bottomHitBox.y, rick.bottomHitBox.width, rick.bottomHitBox.height);

        //peter hitboxes
        g.setColor(Color.BLUE);
        g.drawRect(peter.hitBox.x, peter.hitBox.y, peter.hitBox.width, peter.hitBox.height);
        g.setColor(Color.RED);
        g.drawRect(peter.rightHitBox.x, peter.rightHitBox.y, peter.rightHitBox.width, peter.rightHitBox.height);
        g.setColor(Color.GREEN);
        g.drawRect(peter.leftHitBox.x, peter.leftHitBox.y, peter.leftHitBox.width, peter.leftHitBox.height);
        g.setColor(Color.BLACK);
        g.drawRect(peter.topHitBox.x, peter.topHitBox.y, peter.topHitBox.width, peter.topHitBox.height);
        g.setColor(Color.YELLOW);
        g.drawRect(peter.bottomHitBox.x, peter.bottomHitBox.y, peter.bottomHitBox.width, peter.bottomHitBox.height);

        //ball hitboxes
        g.setColor(Color.BLUE);
        g.drawRect(ball.hitBox.x, ball.hitBox.y, ball.hitBox.width, ball.hitBox.height);
        g.setColor(Color.RED);
        g.drawRect(ball.rightHitBox.x, ball.rightHitBox.y, ball.rightHitBox.width, ball.rightHitBox.height);
        g.setColor(Color.GREEN);
        g.drawRect(ball.leftHitBox.x, ball.leftHitBox.y, ball.leftHitBox.width, ball.leftHitBox.height);
        g.setColor(Color.BLACK);
        g.drawRect(ball.topHitBox.x, ball.topHitBox.y, ball.topHitBox.width, ball.topHitBox.height);
        g.setColor(Color.YELLOW);
        g.drawRect(ball.bottomHitBox.x, ball.bottomHitBox.y, ball.bottomHitBox.width, ball.bottomHitBox.height);


        //goal 1 hitboxs
        g.setColor(Color.BLUE);
        g.drawRect(goal1.hitBox.x, goal1.hitBox.y, goal1.hitBox.width, goal1.hitBox.height);
        g.setColor(Color.RED);
        g.drawRect(goal1.rightHitBox.x, goal1.rightHitBox.y, goal1.rightHitBox.width, goal1.rightHitBox.height);
        g.setColor(Color.GREEN);
        g.drawRect(goal1.leftHitBox.x, goal1.leftHitBox.y, goal1.leftHitBox.width, goal1.leftHitBox.height);
        g.setColor(Color.BLACK);
        g.drawRect(goal1.topHitBox.x, goal1.topHitBox.y, goal1.topHitBox.width, goal1.topHitBox.height);
        g.setColor(Color.YELLOW);
        g.drawRect(goal1.bottomHitBox.x, goal1.bottomHitBox.y, goal1.bottomHitBox.width, goal1.bottomHitBox.height);
        //goal 2 hitboxes
        g.setColor(Color.BLUE);
        g.drawRect(goal2.hitBox.x, goal2.hitBox.y, goal2.hitBox.width, goal2.hitBox.height);
        g.setColor(Color.RED);
        g.drawRect(goal2.rightHitBox.x, goal2.rightHitBox.y, goal2.rightHitBox.width, goal2.rightHitBox.height);
        g.setColor(Color.GREEN);
        g.drawRect(goal2.leftHitBox.x, goal2.leftHitBox.y, goal2.leftHitBox.width, goal2.leftHitBox.height);
        g.setColor(Color.BLACK);
        g.drawRect(goal2.topHitBox.x, goal2.topHitBox.y, goal2.topHitBox.width, goal2.topHitBox.height);
        g.setColor(Color.YELLOW);
        g.drawRect(goal2.bottomHitBox.x, goal2.bottomHitBox.y, goal2.bottomHitBox.width, goal2.bottomHitBox.height);
        //goal Icon

        if(goalTimer > 0){
            g.drawImage(giconPic,300,0,400,200,null);
        }

        //show scroe for rick

        if (scoregoal2 == 0){
            g.drawImage(a, 400,600, 100,100,null);

        }
        if (scoregoal2 == 1){
            g.drawImage(b, 400,600, 100,100,null);

        }
        if (scoregoal2 == 2){
            g.drawImage(c, 400,600, 100,100,null);

        }
        if (scoregoal2 == 3){
            g.drawImage(d, 400,600, 100,100,null);

        }
        if (scoregoal2 == 4){
            g.drawImage(e, 400,600, 100,100,null);

        }
        if (scoregoal2 == 5){
            g.drawImage(f, 400,600, 100,100,null);

        }
        if (scoregoal2 == 6){
            g.drawImage(gg, 400,600, 100,100,null);

        }
        if (scoregoal2 == 7){
            g.drawImage(h, 400,600, 100,100,null);

        }
        if (scoregoal2 == 8){
            g.drawImage(i, 400,600, 100,100,null);

        }
        if (scoregoal2 == 9){
            g.drawImage(j, 400,600, 100,100,null);

        }

        //show score for peter

        if (scoregoal1 == 0){
            g.drawImage(a, 500,600, 100,100,null);

        }
        if (scoregoal1 == 1){
            g.drawImage(b, 500,600, 100,100,null);

        }
        if (scoregoal1 == 2){
            g.drawImage(c, 500,600, 100,100,null);

        }
        if (scoregoal1 == 3){
            g.drawImage(d, 500,600, 100,100,null);

        }
        if (scoregoal1 == 4){
            g.drawImage(e, 500,600, 100,100,null);

        }
        if (scoregoal1 == 5){
            g.drawImage(f, 500,600, 100,100,null);

        }
        if (scoregoal1 == 6){
            g.drawImage(gg, 500,600, 100,100,null);

        }
        if (scoregoal1 == 7){
            g.drawImage(h, 500,600, 100,100,null);

        }
        if (scoregoal1 == 8){
            g.drawImage(i, 500,600, 100,100,null);

        }
        if (scoregoal1 == 9){
            g.drawImage(j, 500,600, 100,100,null);

        }






        g.dispose();
        bufferStrategy.show();
    }
}

