import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #2792 보석 상자 
public class Main {
    static int N, M;
    static int[] arr;

    static int binarySearch() {
        int lt = 1;
        int rt = 1_000_000_000;
        int min = Integer.MAX_VALUE;
        while(lt <= rt){
            int mid = (lt + rt) / 2;
            int pt = 0;
            int sum = 0;
            int cnt = 0;
            for(int i = 0 ; i < M ; i ++){
                cnt += arr[pt] / mid + 1;
                if(arr[pt] % mid == 0) cnt --;
                if(cnt > N) break;
                pt ++;
            }
            if(cnt > N) {
                lt = mid + 1;
            }
            else{
                min = Math.min(min, mid);
                rt = mid - 1;
            }
        }
        return min;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];
        for(int i = 0 ; i < M ; i ++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(binarySearch());
    }
}
