import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #13279 곱의 합 쿼리 
public class Main {
	static int[] arr, mem;
	static private final int MOD = 100_003;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N + 1];
		mem = new int[N + 1]; // 1 - based

		for(int i = 1 ; i <= N ; i ++){
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for(int i = 1 ; i <= N; i ++){
			for(int j = i ; j > 0 ; j --){
				if(j == 1) mem[j] = (mem[j] + arr[i]) % MOD;
				else mem[j] = (mem[j] + (int)((long)mem[j - 1] * arr[i] % MOD)) % MOD;
			}
		}

		int Q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < Q ; i ++){
			int cur = Integer.parseInt(br.readLine());
			sb.append(mem[cur] + "\n");
		}
		System.out.println(sb);
	}
}
