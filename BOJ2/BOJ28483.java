import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #28483 행렬 연산 (행렬 계산하기)

public class Main {
	static int[] rarr, carr;
	static int N, M;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // row
		M = Integer.parseInt(st.nextToken()); // column
		rarr = new int[N];
		carr = new int[M];
		int Q = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i < Q ; i ++){
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1){
				rarr[Integer.parseInt(st.nextToken()) - 1] += Integer.parseInt(st.nextToken());
			}
			else{
				carr[Integer.parseInt(st.nextToken()) - 1] += Integer.parseInt(st.nextToken());
			}
		}

		StringBuilder sb = new StringBuilder();

		for(int i = 0 ; i < N ; i ++){
			for(int j = 0 ; j < M ; j ++){
				sb.append((carr[j] + rarr[i]) + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
