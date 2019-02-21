/**
 * Class to Implement AnimalShelter
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/18/19
 * Project : StackProblems
 **/

package StackProblems;

public class AnimalShelter {

    public static void main(String args[]) {

        AnimalQueue animalQueue = new AnimalQueue();
        animalQueue.enqueue(new Dog("Dog1"));
        animalQueue.enqueue(new Dog("Dog2"));
        animalQueue.enqueue(new Dog("Dog3"));
        animalQueue.enqueue(new Cat("Cat1"));
        animalQueue.enqueue(new Cat("Cat2"));
        animalQueue.enqueue(new Cat("Cat3"));

        System.out.println(animalQueue.dequeueAny().name); //Dog1
        System.out.println(animalQueue.dequeueCat().name); //Cat1
        System.out.println(animalQueue.dequeueDog().name); //Dog2
        System.out.println(animalQueue.dequeueAny().name); //Dog3
        System.out.println(animalQueue.dequeueAny().name); //Cat2
        System.out.println(animalQueue.dequeueCat().name); //Cat3
    }
}