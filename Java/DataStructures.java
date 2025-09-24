import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DataStructures {
    public static void main(String[] args) {
        ArrayList<Integer> al = new ArrayList<Integer>(10);
        //List<Integer> stack = new LinkedList<>()

        al.add(1);
        al.add(0, 2);

        al.remove(0);
        al.remove((Integer) 1);
        al.clear();

        al.add((Integer) 1);
        al.add(0, 2);

        al.get(0);
        al.set(0, 2);

        al.size();

        al.contains(2);
        al.indexOf(2);
        al.lastIndexOf(2);

        for (int i = 0; i < al.size(); i++) {
            System.out.println(al.get(i));
        }

        for (int num: al) {
            System.out.println(num);
        }

        Iterator<Integer> i = al.iterator();

        while (i.hasNext()) {
            System.out.println(i.next());
        }

        HashSet<Integer> hs = new HashSet<Integer>();


        //Just has the order behaviour
        //LinkedHashSet<Integer> hs = new LinkedHashSet<Integer>();
        //TreeSet<Integer> ts = new TreeSet<Integer>();

        hs.add(1);
        hs.remove(1);
        hs.contains(1);
        hs.add(1);
        hs.isEmpty();
        hs.size();
        hs.clear();
        hs.iterator();

        hs.add(1);

        for (Integer num: hs) {
            System.out.println(num);
        }

        Iterator<Integer> it = hs.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }


        //HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
        //LinkedHashMap<Integer,Integer> hm = new LinkedHashMap<Integer,Integer>();
        Hashtable<Integer,Integer> hm = new Hashtable<Integer,Integer>();

        hm.put(1,1);
        hm.get(1);

        hm.containsKey(1);
        hm.containsValue(1);

        hm.remove(1); //Key
        hm.clear();

        hm.put(5,1);

        hm.size();

        hm.keySet();
        hm.values();
        hm.entrySet();

        for (Integer key: hm.keySet()) {
            System.out.println(key);
        }

        Iterator<Integer> x = hm.keySet().iterator();

        while (x.hasNext()) {
            System.out.println(x.next());
        }

        Queue<Integer> q = new LinkedList<>();

        q.add(1);
        q.offer(2);

        q.remove();
        q.poll();

        q.element();
        q.peek();
        q.size();


        Deque<Integer> stack = new LinkedList<>();
        //Deque<Integer> stack = new ArrayDeque<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.pop();
        stack.peek();

        stack.addFirst(1);
        stack.offerFirst(2);
        stack.getFirst();
        stack.peekFirst();
        stack.removeFirst();
        stack.pollFirst();
        stack.size();

        // Same as above for last example - addLast, offerLast, etc.

        Queue<Integer> pq = new PriorityQueue<>();

        q.add(1);
        q.offer(2);

        q.remove();
        q.poll();

        q.element();
        q.peek();

        q.size();


        // Blocking Structures

        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>();
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<Integer>();


        // Both these operations are blocking and the thread will keep waiting for the element or wait to put if the queue is full
        queue.put(1);
        queue.take();

        //Concurrent data structures

        ConcurrentHashMap<Integer> map = new ConcurrentHashMap<Integer>();
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
        ConcurrentLinkedDeque<Integer> queue = new ConcurrentLinkedDeque<Integer>();

        AtomicInteger i = new AtomicInteger(1);

        AtomicLong i = new AtomicLong();
        AtomicBoolean b = new AtomicBoolean();

        i.compareAndSet(0, 1);
        i.getAndIncrement();
        i.incrementAndGet();
        i.addAndGet(1);
        i.getAndAdd(1);
        i.get();

    }
}