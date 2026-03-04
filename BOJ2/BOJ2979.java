import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2979 트럭 주차
public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken()) * 2;
        int C = Integer.parseInt(st.nextToken()) * 3;

        int[] parkingCount = new int[102];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            parkingCount[start]++;
            parkingCount[end]--;
        }

        for (int i = 1; i <= 101; i++) {
            parkingCount[i] += parkingCount[i - 1];
        }

        int cost = 0;
        for (int i = 1; i <= 101; i++) {
            switch (parkingCount[i]) {
                case 1:
                    cost += A;
                    break;
                case 2:
                    cost += B;
                    break;
                case 3:
                    cost += C;
                    break;
                default:
                    break;
            }
        }
        System.out.println(cost);
    }
}
