/**
 * Class to Implement DiningProblem
 *
 * @author Sharayu Sharad Mantri
 * Date : 3/6/19
 * Project : Threading
 **/

/**
 * Incorrect Solution causes deadlock
 */
package Threading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningProblem {

    class Chopstick {
        private Lock lock;

        public Chopstick() {
            lock = new ReentrantLock();
        }

        void pickUP(int name, String which){
            lock.lock();
            System.out.println(name + " " + which + " pickup ");
        }

        void putDOWN(int name, String which){
            lock.unlock();
            System.out.println(name + " " + which + " putdown ");
        }
    }

    class Philosopher extends Thread {
        private int name;
        private int bites = 1;
        Chopstick left,right;

        public Philosopher(int name, Chopstick left,Chopstick right){
            this.name = name;
            this.left = left;
            this.right = right;
        }

        void eat() {
            System.out.println(name + " starting to eat ");
            pickUP();
            chew();
            putDOWN();
            System.out.println(name + " finished eating ");
        }

        void pickUP() {
            left.pickUP(name, "left");
            try {
                this.sleep(100);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
            right.pickUP(name, "right");
        }

        void chew(){
            try {
                this.sleep(100);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

        void putDOWN() {
            right.putDOWN(name, "right");
            left.putDOWN(name, "left");
        }

        public void run() {
            for (int i = 0; i < bites; i++) {
                eat();
            }
        }
    }

    public static void main(String args[]) {
        int count = 5;
        DiningProblem d = new DiningProblem();
        Philosopher[] people = new Philosopher[5];
        Chopstick prev = d.new Chopstick();

        people[count-1] = d.new Philosopher(count -1, d.new Chopstick(), prev);
        for (int i = 0; i < count-1; i++) {
            Chopstick right = d.new Chopstick();
            people[i] = d.new Philosopher(i, prev, right);
            prev = right;
        }
        people[count -1].left = prev;

        for (int i =0; i < count; i++) {
            people[i].start();
        }
    }
}
