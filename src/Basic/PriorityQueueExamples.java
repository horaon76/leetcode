package Basic;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueExamples {

    public static void minPQ(){
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        minPQ.add(1);
        minPQ.add(8);
        minPQ.add(10);
        minPQ.add(11);
        minPQ.add(7);
//        minPQ.forEach(item-> System.out.println(item));
        System.out.println(minPQ.poll()); //1
        System.out.println(minPQ.poll()); //7
        System.out.println(minPQ.poll()); //8
    }

    public static void maxPQ(){
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
        maxPQ.add(1);
        maxPQ.add(8);
        maxPQ.add(10);
        maxPQ.add(11);
        maxPQ.add(7);
//        minPQ.forEach(item-> System.out.println(item));
        System.out.println(maxPQ.poll()); //11
        System.out.println(maxPQ.poll()); //10
        System.out.println(maxPQ.poll()); //8
    }

    public static void customPQ(){
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a,b) -> b-a);
        maxPQ.add(1);
        maxPQ.add(8);
        maxPQ.add(10);
        maxPQ.add(11);
        maxPQ.add(7);
//        minPQ.forEach(item-> System.out.println(item));
        System.out.println(maxPQ.poll()); //11
        System.out.println(maxPQ.poll()); //10
        System.out.println(maxPQ.poll()); //8
    }
    public static void main(String[] args){
        minPQ();
        System.out.println("---------------------");
        maxPQ();
        System.out.println("---------------------");
        customPQ();
    }

}
