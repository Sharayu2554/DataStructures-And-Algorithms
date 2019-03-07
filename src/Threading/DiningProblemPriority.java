
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
 *DiningProblemPriorty:
 * we label left and right chopsticks and we will pickup chopstick with higher value. so last will not pickup and deadlock will be handeled.
 *
 *
 */
package Threading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningProblemPriority {

    class Chopstick {
        private Lock lock;
        int name;

        public Chopstick(int number) {
            lock = new ReentrantLock();
            name = number;
        }

        boolean pickUP(int name, int which){
            System.out.println(name + " " + which + " locking ");
            return lock.tryLock();
        }

        void putDOWN(int name, int which){
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
            Chopstick pickUP1, pickUP2;
            if (left.name < right.name) {
                pickUP1 = left;
                pickUP2 = right;
            }
            else {
                pickUP1 = right;
                pickUP2 = left;
            }
            if (!pickUP1.pickUP(name, pickUP1.name)) {
                System.out.println(name + " " + pickUP1.name +" locking failed ");
                return false;
            }
            System.out.println(name +" " + pickUP1.name + " locked ");
            if (!pickUP2.pickUP(name, pickUP2.name)) {
                System.out.println(name + " " + pickUP2.name + " locking failed ");
                pickUP1.putDOWN(name, pickUP1.name);
                return false;
            }
            System.out.println(name + " " + pickUP2.name +" locked ");
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
            if (left.name < right.name) {
                right.putDOWN(name, right.name);
                left.putDOWN(name, left.name);
            }
            else {
                left.putDOWN(name, left.name);
                right.putDOWN(name, right.name);
            }
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
        DiningProblemPriority d = new DiningProblemPriority();
        Philosopher[] people = new Philosopher[5];
        Chopstick prev = d.new Chopstick(0);

        people[count-1] = d.new Philosopher(count -1, d.new Chopstick(count-1), prev);
        for (int i = 0; i < count-1; i++) {
            Chopstick right = d.new Chopstick(i+1);
            people[i] = d.new Philosopher(i, prev, right);
            prev = right;
        }
        people[count -1].left = prev;

        for (int i =0; i < count; i++) {
            people[i].start();
        }
    }
}
