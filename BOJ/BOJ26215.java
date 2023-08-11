import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//BOJ 26215 눈 치우기
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;
        PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0 ; i < N ; i ++){
            pQ.offer(Integer.parseInt(st.nextToken()));
        }
        while(pQ.size() >= 2){
            int a = pQ.poll();
            int b = pQ.poll();
            answer += b;
            pQ.offer(a - b);
        }
        if(!pQ.isEmpty()) answer += pQ.poll();
        if(answer > 1440) System.out.println(-1);
        else System.out.println(answer);
    }
}
