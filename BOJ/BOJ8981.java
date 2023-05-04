import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #8981 입력숫자 
public class Main {

	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		arr[0] = Integer.parseInt(st.nextToken());
		int last = arr[0];
		int curidx = 0;
		for(int i = 1 ; i < N ; i ++) {
			curidx = (curidx + last) % N;
			while(arr[curidx] != 0) {
				curidx = (curidx + 1) % N;
			}
			arr[curidx] = Integer.parseInt(st.nextToken());
			last = arr[curidx];
		}
		StringBuilder sb = new StringBuilder();
		sb.append(N + "\n");
		for(int i = 0 ; i < N ; i ++) {
			sb.append(arr[i] + " ");
		}
		System.out.println(sb);
	}
}
