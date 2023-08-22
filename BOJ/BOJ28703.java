import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// BOJ #28703 Double It
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Long> pQ = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());
        if(N == 1){
            System.out.println(0);
            return;
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        long maxV = Long.MIN_VALUE;
        for(int i = 0 ; i < N ; i ++){
            long tmp = Long.parseLong(st.nextToken());
            pQ.offer(tmp);
            maxV = Math.max(maxV, tmp);
        }
        long curMax = maxV;
        long min = Long.MAX_VALUE;
        while(pQ.peek() != maxV){
            long tmp = pQ.poll();
            min = Math.min(curMax - tmp, min);
            tmp *= 2;
            curMax = Math.max(tmp, curMax);
            pQ.offer(tmp);
        }
        min = Math.min(curMax - pQ.peek(), min);
        System.out.println(min);
    }
}
