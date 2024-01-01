import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
// BOJ #2840 행운의 바퀴
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        char[] arr = new char[N];
        Arrays.fill(arr, '?');
        int curidx = N - 1;
        char cur;
        for(int i = 0 ; i < K ; i ++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            cur = st.nextToken().charAt(0);
            curidx -= s % N;
            if(curidx < 0){
                curidx += N;
            }
            if(arr[curidx] == '?'){
                arr[curidx] = cur;
            }
            else{
                if(arr[curidx] != cur && arr[curidx] != '?'){
                    System.out.println("!");
                    return;
                }
            }
        }
        HashSet<Character> set = new HashSet<>();
        for(int i = 0 ; i < N ; i ++){
            if(arr[i] != '?'){
                if(set.contains(arr[i])) {
                    System.out.println("!");
                    return;
                }
                else{
                    set.add(arr[i]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < N ; i ++){
            sb.append(arr[curidx]);
            curidx ++;
            curidx %= N;
        }
        System.out.println(sb);
    }
}
