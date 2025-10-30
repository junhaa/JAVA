import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #10158 개미
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int t = Integer.parseInt(br.readLine());

        int wz = ((p + t) / w) % 2;
        int hz = ((q + t) / h) % 2;

        int wt = (p + t) % w;
        int ht = (q + t) % h;

        if(wz % 2 == 1) {
            System.out.print(w - wt);
        }
        else {
            System.out.print(wt);
        }

        System.out.print(" ");

        if(hz % 2 == 1) {
            System.out.print(h - ht);
        }
        else {
            System.out.print(ht);
        }
    }
}
