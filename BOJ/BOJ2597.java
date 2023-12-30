import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #2597 줄자접기
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        double[][] arr = new double[3][2];
        for(int i = 0 ; i < 3 ; i ++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i][0] = Math.min(a, b);
            arr[i][1] = Math.max(a, b);
        }

        double curStart = 0;
        double curEnd = N;

        for(int i = 0 ; i < 3;  i++){
            // 두 점이 같은 위치이면 통과
            if(arr[i][1] == arr[i][0]) continue;
            double mid = (arr[i][1] + arr[i][0]) / 2;

            boolean foldFromLeft = false; // 왼쪽에서 접는지
            if(Math.abs(curStart - mid) > Math.abs(curEnd - mid)){
                curEnd = mid;
                foldFromLeft = false;
            }
            else{
                curStart = mid;
                foldFromLeft = true;
            }
            for(int j = i ; j < 3 ; j ++){
                if(foldFromLeft){
                    for(int k = 0 ; k < 2 ; k ++){
                        if(arr[j][k] < mid){
                            arr[j][k] = (mid * 2) - arr[j][k];
                        }
                    }
                }
                else{
                    for(int k = 0 ; k < 2 ; k ++){
                        if(arr[j][k] > mid){
                            arr[j][k] = (mid * 2) - arr[j][k];
                        }
                    }
                }
            }
        }
        System.out.println(curEnd - curStart);
    }
}
