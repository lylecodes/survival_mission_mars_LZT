package com.mars.stats;
    public class Health {


        //fields
        Integer health = 100; //meant to be understood as a %, hard coded for now


        //accessor methodsx`
        public int getHealth() {
            return health; //return current health value
        }

        public void setHealth(Integer health) {
            this.health = health;
        }

        public void updateHealthLoss(int healthLoss){
            if (this.health - healthLoss <= 0){
                this.health = 0;
            }
            else{
                this.health = health - healthLoss;
            }
        }

        public void updateHealthGain(int healthGain){
//            if (healthGain + health >= 100){
//                health = 100;
//            }
//            else{
//                health = health + healthGain;
//            }
            health = health + healthGain;
        }

        @Override
        public String toString() {
            return "Health{" +
                    "health=" + health +
                    '}';
        }
    }
