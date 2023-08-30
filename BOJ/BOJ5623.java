import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N == 2){ // N은 양의 정수, 수열 A는 항상 유일하다.
            System.out.println("1 1");
            return;
        }
        int[][] map = new int[N][N];
        StringTokenizer st;
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        long[] arr = new long[N];
        for(int i = 2 ; i < N ; i ++){
            arr[i] = map[0][i] - map[0][i - 1];
        }
        arr[1] = map[N - 1][1] - map[N - 1][0];
        arr[0] = (map[0][1] - arr[1]) / 2;
        for(int i = 1 ; i < N ; i ++){
            arr[i] = arr[i - 1] + arr[i];
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < N ; i ++){
            sb.append(arr[i] + " ");
        }
        System.out.println(sb);
    }
}
