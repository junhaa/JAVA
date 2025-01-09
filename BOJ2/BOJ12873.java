import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// BOJ #12873 기념품
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue queue = initQueue(N);
        int cur = 1;
        while(queue.size() != 1){
            long cycleCount = ((long)Math.pow(cur, 3) - 1) % queue.size();
            pollDestination(cycleCount, queue);

            cur ++;
        }
        System.out.println(queue.poll());
    }

    private static Queue<Integer> initQueue(int N){
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0 ; i < N ; i ++){
            queue.add(i + 1);
        }

        return queue;
    }

    private static void pollDestination(long cycleCount, Queue<Integer> queue){
        for(int i = 0 ; i < cycleCount ; i ++){
            queue.add(queue.poll());
        }
        queue.poll();
    }
}
