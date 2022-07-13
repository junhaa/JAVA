import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #3003 Å·, Äý, ·è, ºñ¼ó, ³ªÀÌÆ®, Æù
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = {1, 1, 2, 2, 2, 8};
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < 6 ; i ++) {
			sb.append(arr[i] - Integer.parseInt(st.nextToken())).append(" ");
		}
		System.out.println(sb);	
	}
}
