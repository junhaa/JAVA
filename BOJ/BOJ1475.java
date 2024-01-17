import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #1475 방 번호
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input  =br.readLine();
        char[] arr = new char[10];
        for(int i = 0 ; i < input.length(); i ++){
            char cur = input.charAt(i);
            arr[cur - '0' + 0] ++;
        }
        int ssum = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < 10 ; i ++){
            if(i == 6 || i == 9) {
                ssum += arr[i];
            }
            else {
                max = Math.max(max, arr[i]);
            }
        }
        System.out.println(Math.max((int)Math.ceil((double)ssum / 2), max));
    }
}
