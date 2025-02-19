import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #1002 터렛
public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T -- > 0){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            sb.append(main.solve(x1, y1, r1, x2, y2, r2)).append("\n");
        }
        System.out.println(sb);
    }

    public int solve(int x1, int y1, int r1, int x2, int y2, int r2){
        double distance = getDistance(x1, y1, x2, y2);
        if(distance == 0 && r1 == r2) return -1;
        if(distance < r1 && r1 >= r2){
            if(distance + r2 > r1) return 2;
            if(distance + r2 == r1) return 1;
            return 0;
        }
        if(distance < r2 && r2 >= r1){
            if(distance + r1 > r2) return 2;
            if(distance + r1 == r2) return 1;
            return 0;
        }

        if(distance < r1 + r2) return 2;
        if(distance == r1 + r2) return 1;
        if(distance > r1 + r2) return 0;

        return -1;
    }

    public double getDistance(int x1, int y1, int x2, int y2){
        return Math.sqrt((long)(x2 - x1) * (long)(x2 - x1) + (long)(y2 - y1) * (long)(y2 - y1));
    }

}
