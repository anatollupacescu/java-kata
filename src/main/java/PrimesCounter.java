import java.util.LinkedList;
import java.util.Queue;

public class PrimesCounter extends Thread{

    private PrimesCounter other;

    public void setOther(PrimesCounter t1) {
        this.other = t1;
    }

    public static void main(String[] args){
        PrimesCounter t1 = new PrimesCounter();
        PrimesCounter t2 = new PrimesCounter();
        t1.setOther(t2);
        t2.setOther(t1);
        t2.start();
        t1.start();
    }

    public void run() {
        while(true) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " try read...");
            String str = readConsole();
            if (str != null) {
                System.out.println(threadName + " read: " + str);
            } else {
                try {
                    other.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static Queue<String> queue = new LinkedList<>();

    static {
        queue.add("unu");
        queue.add("doi");
        queue.add("tre");
        queue.add("pat");
        queue.add("cin");
        queue.add("sex");
    }

    private String readConsole() {
        return queue.poll();
    }
}
