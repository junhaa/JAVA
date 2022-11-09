import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #1920 수 찾
public class Main {
	public static void main(String[] args) throws IOException{
		Main T_= new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		Arrays.sort(arr);
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			int res = Arrays.binarySearch(arr, Integer.parseInt(st.nextToken()));
			if(res < 0) sb.append("0" + "\n");
			else sb.append(1 + "\n");
		}
		System.out.println(sb);
	}
}
