import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #C - 발머의 피크 이론
public class Main {

	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}	
		int tmp = 0;
		int answer = 0;
		for(int i = 0 ; i < L ; i ++) {
			tmp += arr[i];
			if(tmp >= 129 && tmp <= 138) answer ++;
		}
		for(int i = 0 ; i < N - L ; i ++) {
			tmp -= arr[i];
			tmp += arr[i + L];
			if(tmp >= 129 && tmp <= 138) answer ++;
		}
		System.out.println(answer);
	}
}
