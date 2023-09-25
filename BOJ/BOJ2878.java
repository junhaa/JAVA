import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
// BOJ #2878 캔디캔디
public class Main {

    static Integer[] arr;
    static int remCnt = 0;
    static int binarySearch(int max, int M, int N){
        int lt = 0;
        int rt = max;
        int retMax = Integer.MAX_VALUE;


        while(lt <= rt){
            long cnt = 0;
            int mid = (lt + rt) / 2;
            for(int i = 0 ; i < N ; i ++){
                int tmp = arr[i];
                if(tmp > mid){
                    cnt += tmp - mid;
                }
            }
            if(cnt > M){
                lt = mid + 1;
            }
            else{
                if(retMax > mid){
                    remCnt = M - (int)cnt;
                }
                retMax = Math.min(retMax, mid);
                rt = mid - 1;
            }
        }
        return retMax;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int max = 0;
        arr = new Integer[N];
        for(int i = 0 ; i < N ; i ++){
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(arr[i], max);
        }
        Arrays.sort(arr, Collections.reverseOrder());
        long min = binarySearch(max, M, N);
        long sum = 0;
        for(int i = 0 ; i < N ; i ++){

            int tmp = arr[i];
            if(tmp > min){
                if(remCnt > 0) {
                    sum += (min - 1) * (min - 1);
                    remCnt --;
                }
                else{
                    sum += min * min;
                }
            }
            else{
                sum += (long)tmp * (long)tmp;
            }
        }
        System.out.println(sum);
    }
}
