import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #2110 공유기 설치 
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int max = Integer.MIN_VALUE;
        int[] arr = new int[N];
        for(int i = 0 ; i < N ; i ++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int lt = 1;
        int rt = arr[N - 1] - arr[0];
        while(lt <= rt){
            int mid = (rt + lt) / 2;
            int cur = arr[0];
            int cnt = 1;
            for(int i = 1 ; i < N ; i ++){
                if(arr[i] - cur >= mid){
                    cur = arr[i];
                    cnt ++;
                }
            }
            if(cnt >= C){
                max = Math.max(max, mid);
                lt = mid + 1;
            }
            else{
                rt = mid - 1;
            }
        }
        System.out.println(max);
    }
}
