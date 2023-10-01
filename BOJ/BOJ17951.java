import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #17951 흩날리는 시험지 속에서 내 평점이 느껴진거야
public class Main {

    static int binarySearch(int[] arr, int K, int N){
        int lt = 0;
        int rt = N * 20;
        int max = Integer.MIN_VALUE;
        while(lt <= rt){
            int mid = (lt + rt) / 2;
            int cnt = 0;
            int cur = 0;
            for(int i = 0 ; i < N ; i ++){
                cur += arr[i];
                if(cur >= mid){
                    cur = 0;
                    cnt ++;
                }
            }
            if(cnt >= K){
                max = Math.max(max, mid);
                lt = mid + 1;
            }
            else {
                rt = mid - 1;
            }
        }
        return max;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int arr[] = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(binarySearch(arr, K, N));
    }
}
