import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1581 락스타 락동호
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ff = Integer.parseInt(st.nextToken());
        int fs = Integer.parseInt(st.nextToken());
        int sf = Integer.parseInt(st.nextToken());
        int ss = Integer.parseInt(st.nextToken());

        if(ff > 0 || fs > 0) {
            int count = ff;
            if(fs > 0) {
                if(fs == sf) {
                    count += fs * 2;
                }
                if(fs > sf) {
                    count += sf * 2 + 1;
                }
                if(fs < sf) {
                    count += fs * 2;
                }
                count += ss;
            }
            System.out.println(count);
            return;
        }

        System.out.println(sf > 0 ? ss + 1 : ss);
    }
}
