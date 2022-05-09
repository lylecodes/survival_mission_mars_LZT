package com.mars.stats;
    public class Health {
        //fields
        Integer health = 100; //meant to be understood as a %


        //accessor methods
        public int getHealth() {
            return health;
        }

        @Override
        public String toString() {
            return "Health{" +
                    "health=" + health +
                    '}';
        }
    }
