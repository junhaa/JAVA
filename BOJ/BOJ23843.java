import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// BOJ #23843 콘센트
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
        int[] time = new int[M];
        for(int i = 0 ; i < N ; i ++) pQ.offer(Integer.parseInt(st.nextToken()));
        int pt = 0;
        int cur = -1;
        while(!pQ.isEmpty()){
            if(pt == 0){
                cur = pQ.poll();
                time[pt] += cur;
                pt = (pt + 1) % M;
                continue;
            }
            time[pt] += pQ.poll();
            if(time[pt] == time[0]) pt = (pt + 1) % M;
        }
        System.out.println(time[0]);
    }
}
