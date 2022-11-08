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

        public Characters(String pName, int pXpos, int pYpos) { // Astronaut constructor
                name = pName;
                xpos = pXpos;
                ypos = pYpos;
                dx = 6;
                dy = 6;
                width = 100;
                height = 100;
                isAlive = true;
        }

        public void bounce(){
                xpos = xpos + dx;
                ypos = ypos + dy;

                if (xpos >= 1000-width || xpos <= 0){
                        dx = -dx;

                }
                if (ypos >= 700-height || ypos <= 0){
                        dy = -dy;

                }


        }
        public void warp(){
                xpos = xpos + dx;
                ypos = ypos + dy;

                if (xpos >= 1000 - width ){
                        xpos = xpos-1000;
                }
                if (ypos >= 700 - height){
                        ypos = ypos-700;
                }

                if (xpos <= 0){
                        xpos = xpos+1000;
                }
                if (ypos <= 0){
                        ypos = ypos+700;
                }
        }
}
