
/**
 * Class to Implement DiningProblem
 *
 * @author Sharayu Sharad Mantri
 * Date : 3/6/19
 * Project : Threading
 **/

/**
 * DiningProblem.java : Incorrect Solution causes deadlock
 * DiningProbleAllOrNothing.java : solves dead lock by one change. if a philosopher is unable to gte right chopstick,
 * he will have to putDown right chopstick. Here if we dont add delay, there might be case where all of them are synchronized such that
 * all pick up left, so each of them cant pick right so they put down left. now again all of them simuleanously pick up left and now
 * its again a deadlock like previous situation. This can happend again and again.
 *
 */
package Threading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningProblemAllOrNothing {

    class Chopstick {
        private Lock lock;

        public Chopstick() {
            lock = new ReentrantLock();
        }

        boolean pickUP(int name, String which){
            System.out.println(name + " " + which + " locking ");
            return lock.tryLock();
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
            if (pickUP()) {
                chew(name);
                putDOWN();
                System.out.println(name + " finished eating ");
            }
        }

        boolean pickUP() {
            if (!left.pickUP(name, "left")) {
                System.out.println(name + " left locking failed ");
                return false;
            }
            System.out.println(name + " left locked ");
            try {
                this.sleep(100);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
            if (!right.pickUP(name, "right")) {
                System.out.println(name + " right locking failed ");
                left.putDOWN(name, "left");
                return false;
            }
            System.out.println(name + " right locked ");
            return true;
        }

        void chew(int name){
            try {
                System.out.println(name + " eating ....... ");
                this.bites--;
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
            try {
                while(bites > 0){
                    eat();
                    this.sleep(100);
                }
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String args[]) {
        int count = 5;
        DiningProblemAllOrNothing d = new DiningProblemAllOrNothing();
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