import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #22935 이진 딸기
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T -- > 0) {
            int N = Integer.parseInt(br.readLine());
            if(N % 28 != 0){
                N %= 28;
            }
            else{
                N = 2;
            }
            if (N > 15) {
                N = 15 - (N - 15);
            }
            String bin = Integer.toBinaryString(N);
            if(bin.length() < 4){
                int plen = 4 - bin.length();
                String tmp = "";
                for(int i = 0 ; i < plen ; i ++){
                    tmp += '0';
                }
                bin = tmp + bin;
            }
            for (int i = 0; i < 4; i++) {
                if (bin.charAt(i) == '0') {
                    sb.append("V");
                } else {
                    sb.append("딸기");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
