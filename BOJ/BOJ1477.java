import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #1477 휴게소 세우기
public class Main {
    static int[] arr;
    static int binarySearch(int L, int N, int M){
        int lt = 1;
        int rt = L;
        int min = Integer.MAX_VALUE;
        while(lt <= rt){
            int mid = (lt + rt) / 2;
            int cur = 0;
            int cnt = 0;
            for(int i = 0 ; i < N ; i ++){
                if(arr[i] - cur > mid){
                    cur = cur + mid;
                    i --;
                    cnt ++;
                    if(cnt > M) break;
                }
                else{
                    cur = arr[i];
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
        return min;
    }
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()) + 1;
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < N - 1 ; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[N - 1] = L;
        Arrays.sort(arr);
        System.out.println(binarySearch(L, N, M));
    }
}
