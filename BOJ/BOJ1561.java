import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
// BOJ #1561 놀이 공원
public class Main {
    static int[] time;

    static int binarySearch(int N, int M, int max){
        long lt = 1;
        long rt = (long)(N / M + 1) * max;
        long min = Long.MAX_VALUE;
        while(lt <= rt){
            long mid = (lt + rt) / 2;
            long sum = 0;
            for(int i = 0 ; i < M ; i ++){
                sum += mid / time[i];
                if(sum > N - M) break;
            }
            if(sum >= N - M){
                min = Math.min(min, mid);
                rt = mid - 1;
            }
            else {
                lt = mid + 1;
            }
        }
        ArrayList<Integer> blist = new ArrayList<>();
        int tsum = 0;
        for(int i = 0 ; i < M ; i ++){
            tsum += min / time[i];
            if(min % time[i] == 0) {
                blist.add(i + 1);
                tsum --;
            }
        }
        return blist.get(N - M -  tsum - 1);
    }
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        if(N <= M){
            System.out.println(N);
            return;
        }
        time = new int[M];
        st = new StringTokenizer(br.readLine());
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < M ; i ++){
            time[i] = Integer.parseInt(st.nextToken());
            max = Math.max(time[i], max);
        }
        System.out.println(binarySearch(N, M, max));
    }
}
