/**
 * Class to Implement AnimalShelter
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/18/19
 * Project : StackProblems
 **/

package StackProblems;

import java.util.LinkedList;
import java.util.Queue;


    class Animal {

        int order;
        String name;

        public Animal(String name) {
            this.name = name;
        }

        public boolean isOrderLessThan(Animal an) {
            return this.order < an.order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public Integer getOrder() {
            return this.order;
        }
    }

    class Dog extends Animal {

        public Dog(String name) {
            super(name);
        }
    }

    class Cat extends Animal {

        public Cat(String name) {
            super(name);
        }
    }

    public class AnimalQueue {
        Queue<Dog> dogs;
        Queue<Cat> cats;
        int order = 0;

        public AnimalQueue() {
            dogs = new LinkedList<Dog>();
            cats = new LinkedList<Cat>();
        }

        public void enqueue(Animal an) {

            an.setOrder(order++);
            if (an instanceof Dog) {
                dogs.add((Dog) an);
            } else {
                cats.add((Cat) an);
            }
        }

        public Dog dequeueDog() {
            return dogs.poll();
        }

        public Cat dequeueCat() {
            return cats.poll();
        }

        public Animal dequeueAny() {

            if (dogs.isEmpty()) {
                return dequeueCat();
            } else if (cats.isEmpty()) {
                return dequeueDog();
            }
            Dog d = dogs.peek();
            Cat c = cats.peek();

            if (d.isOrderLessThan(c))
                return dequeueDog();
            else
                return dequeueCat();
        }
    }

