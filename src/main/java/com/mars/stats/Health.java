package com.mars.stats;
    public class Health {


        //fields
        Integer health = 100; //meant to be understood as a %, hard coded for now


        //accessor methods
        public int getHealth() {
            return health; //return current health value
        }

        public void setHealth(Integer health) {
            this.health = health;
        }

        @Override
        public String toString() {
            return "Health{" +
                    "health=" + health +
                    '}';
        }
    }
