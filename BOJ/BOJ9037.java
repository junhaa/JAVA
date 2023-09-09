import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #9037 The candy war
public class Main {

    static boolean check(int[] arr){
        int num = arr[0];
        for(int i = 1 ; i < arr.length ; i ++){
            if(arr[i] != num) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int test = Integer.parseInt(br.readLine());
        while(test -- > 0){
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            int[] darr = new int[N];
            int L = 0;
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < N ; i ++){
                arr[i] = Integer.parseInt(st.nextToken());
                if(arr[i] % 2 == 1){
                    arr[i] ++;
                }
            }
            if(check(arr)) {
                sb.append(L + "\n");
                continue;
            }
            while(!check(arr)){
                for(int i = 0 ; i < N ; i ++){
                    darr[(i + 1) % N] = arr[i] / 2;
                    arr[i] /= 2;
                }
                for(int i = 0 ; i < N ; i ++){
                    arr[i] += darr[i];
                    if(arr[i] % 2 == 1) arr[i] ++;
                }
                L ++;
            }
            sb.append(L + "\n");
        }
        System.out.println(sb);
    }
}
