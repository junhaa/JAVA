import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #1064 평행사변형
class Point {
    int y, x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {

    static double getLength(Point a, Point b) {
        return Math.sqrt(Math.pow((a.y - b.y), 2) + Math.pow((a.x - b.x), 2));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        Point[] arr = new Point[3];
        for (int i = 0; i < 3; i++) {
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i] = new Point(y, x);
        }


        if ((arr[0].y == arr[1].y) && (arr[1].y == arr[2].y)) {
            System.out.println(-1);
            return;
        }
        if((arr[0].x == arr[1].x) && (arr[1].x == arr[2].x)){
            System.out.println(-1);
            return;
        }

        double slope = (double) (arr[0].y - arr[1].y) / (double) (arr[0].x - arr[1].x);
        if(!(slope == Double.NEGATIVE_INFINITY || slope == Double.POSITIVE_INFINITY)) {
            if ((double) (arr[1].y - arr[2].y) / (double) (arr[1].x - arr[2].x) == slope) {
                System.out.println(-1);
                return;
            }
        }


        for (int i = 0; i < 3; i++) {
            double length = 0;
            for (int j = 0; j < 3; j++) {
                if (i == j) continue;
                length += getLength(arr[i], arr[j]) * 2;
            }
            max = Math.max(length, max);
            min = Math.min(length, min);
        }
        System.out.println(max - min);
    }
}
