import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #2343 기타 레슨
public class Main {
    static int N, M;
    static int[] arr;
    static int min = Integer.MAX_VALUE;

    static void binarySearch(int smax){
        int lt = smax;
        int rt = 1_000_000_000;
        while(lt <= rt){
            int mid = (lt + rt) / 2;
            int cnt = 0;
            int sum = 1_000_000_000;
            for(int i = 0 ; i < N ; i ++){
                if(sum + arr[i] > mid){
                    cnt ++;
                    if(cnt > M) break;
                    sum = arr[i];
                }
                else{
                    sum += arr[i];
                }
            }
            if(cnt > M){
                lt = mid + 1;
            }
            else{
                min = Math.min(min, mid);
                rt = mid - 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int smax = Integer.MIN_VALUE;
        for(int i = 0 ; i < N ; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
            smax = Math.max(smax, arr[i]);
        }
        binarySearch(smax);
        System.out.println(min);
    }
}
