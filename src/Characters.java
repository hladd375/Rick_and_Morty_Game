import java.awt.*;

public class Characters {

        //VARIABLE DECLARATION SECTION
        //Here's where you state which variables you are going to use.
        public String name;               //name of the hero
        public int xpos;                  //the x position
        public int ypos;                  //the y position
        public int dx;                    //the speed of the hero in the x direction
        public int dy;                    //the speed of the hero in the y direction
        public int width;                 //the width of the hero image
        public int height;                //the height of the hero image
        public boolean isAlive;           //a boolean to denote if the hero is alive or dead
        public Rectangle hitBox;
        public Rectangle rightHitBox;
        public Rectangle leftHitBox;
        public Rectangle topHitBox;
        public Rectangle bottomHitBox;




        public Characters(String pName, int pXpos, int pYpos, int pDx, int pDy) { // Astronaut constructor
                name = pName;
                xpos = pXpos;
                ypos = pYpos;
                //xpos = xpos = (int)(Math.random()*800+100);
                //ypos = ypos = (int)(Math.random()*500+100);;
                dx = pDx;
                dy = pDy;
                width = 100;
                height = 100;
                isAlive = true;
                hitBox = new Rectangle (xpos, ypos, width, height);
                rightHitBox = new Rectangle (xpos+width-10,ypos,10,height);
                leftHitBox = new Rectangle (xpos,ypos,10,height);
                topHitBox = new Rectangle (xpos,ypos,width,10);
                bottomHitBox = new Rectangle (xpos,ypos+height,width,10);

        }

        public void bounce(){
                xpos = xpos + dx;
                ypos = ypos + dy;

                if (xpos >= 1000-width && dx > 0) {
                        dx = -dx;
                }

                if (xpos <= 0 && dx < 0){
                        dx = -dx;
                }
                if (ypos >= 700-height && dy > 0){
                        dy = -dy;
                }

                if (ypos <= 0 && dy < 0){
                        dy = -dy;
                }
                hitBox = new Rectangle (xpos, ypos, width, height);
                rightHitBox = new Rectangle (xpos+width-10,ypos,10,height);
                leftHitBox = new Rectangle (xpos,ypos,10,height);
                topHitBox = new Rectangle (xpos,ypos,width,10);
                bottomHitBox = new Rectangle (xpos,ypos+height,width,10);

        }

        public void goaliebounce() {
                xpos = xpos + dx;
                ypos = ypos + dy;

                if (xpos >= 1000 - width || xpos <= 0) {
                        dx = -dx;

                }
                if (ypos >= 600 - height || ypos <= 100) {
                        dy = -dy;

                }

                hitBox = new Rectangle (xpos, ypos, width, height);
                rightHitBox = new Rectangle (xpos+width-10,ypos,10,height);
                leftHitBox = new Rectangle (xpos,ypos,10,height);
                topHitBox = new Rectangle (xpos,ypos,width,10);
                bottomHitBox = new Rectangle (xpos,ypos+height,width,10);        }

        public void wrap(){
                xpos = xpos + dx;
                ypos = ypos + dy;

                if (xpos >= 1000 && dx > 0){ //teleport right to left
                        xpos = 0 - width;
                }

                if (xpos <= 0 - width && dx < 0){//teleport right to left
                        xpos = 1000;
                }
                if (ypos <= 0 - height && dy < 0){ //teleport top to bottom
                        ypos = 700;
                }
                if (ypos >= 700 && dy > 0){ //teleport bottom to top
                        ypos = 0 - height;
                }

                hitBox = new Rectangle(xpos, ypos, width, height);

        }
}
