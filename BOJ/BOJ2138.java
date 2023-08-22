import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2138 전구와 스위치
public class Main {
    static int convert(int i) {return 1 - i;}

    static boolean ch(int[] swit, int[] end, int N){
        for(int i = 0 ; i < N ; i ++){
            if(swit[i] != end[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] start = new int[N];
        int[] end = new int[N];
        int[] swit = new int[N];
        String input = br.readLine();
        for(int i = 0 ; i < N ; i ++){
            start[i] = (int)input.charAt(i) - '0';
        }
        input = br.readLine();
        for(int i = 0 ; i < N ; i ++){
            end[i] = (int)input.charAt(i) - '0';
        }
        System.arraycopy(start, 0, swit, 0, N);
        // switch first
        swit[0] = convert(swit[0]);
        swit[1] = convert(swit[1]);
        int cnt = 1;
        int min = Integer.MAX_VALUE;
        for(int i = 1 ; i < N ; i ++){
            if(swit[i - 1] != end[i - 1]){
                cnt ++;
                swit[i - 1] = convert(swit[i - 1]);
                swit[i] = convert(swit[i]);
                if(i != N - 1) swit[i + 1] = convert(swit[i + 1]);
            }
        }
        if(ch(swit, end, N)) {
            min = Math.min(cnt, min);
        }
        System.arraycopy(start, 0, swit, 0, N);
        cnt = 0;
        for(int i = 1 ; i < N ; i ++){
            if(swit[i - 1] != end[i - 1]){
                cnt ++;
                swit[i - 1] = convert(swit[i - 1]);
                swit[i] = convert(swit[i]);
                if(i != N - 1) swit[i + 1] = convert(swit[i + 1]);
            }
        }
        if(ch(swit, end, N)){
            System.out.println(Math.min(min, cnt));
            return;
        }
        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }
}
