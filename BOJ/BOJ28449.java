import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #28449 누가 이길까
public class Main {
    static int[] hi, arc;

    public static int upperBound(int[] arr, int target) {
        int low = 0;
        int high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int lowerBound(int[] arr, int target) {
        int low = 0;
        int high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        hi = new int[N];
        arc = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            hi[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < M ; i ++){
            arc[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(hi);
        Arrays.sort(arc);
        long win = 0;
        long lose = 0;
        long draw = 0;
        for(int i = 0 ; i < N ; i ++){
            int tmp = hi[i];
            int lower = lowerBound(arc, tmp);
            win += lower;
            int upper = upperBound(arc, tmp);
            draw += upper - lower;
            lose += M - upper;
        }
        System.out.println(win + " " + lose + " " +  draw + " ");
    }
}
