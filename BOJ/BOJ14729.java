import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ #14729 칠무해 
public class Main {
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		double[] arr = new double[N];
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Double.parseDouble(br.readLine());
		}
		Arrays.sort(arr);
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < 7 ; i ++) {
			sb.append(String.format("%.3f", arr[i]) + "\n");
		}
		System.out.println(sb);
	}
}
