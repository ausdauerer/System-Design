import java.util.*;

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

        // Same as above for last example - addLast, offerLast, etc.

        Queue<Integer> pq = new PriorityQueue<>();

        q.add(1);
        q.offer(2);

        q.remove();
        q.poll();

        q.element();
        q.peek();
    }
}