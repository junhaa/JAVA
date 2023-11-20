import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #17827 달팽이 리스트
public class Main {
    static int N, M, V;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        int[] arr = new int[N];
        int clen = N - (V - 1);
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0 ; i < M ; i ++){
            int q = Integer.parseInt(br.readLine());
            if(q >= N){
                int idx = ((V - 1) + ((q - N) % clen));
                sb.append(arr[idx] + "\n");
            }
            else{
                sb.append(arr[q] + "\n");
            }
        }
        System.out.println(sb);
    }
}
