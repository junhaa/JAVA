import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #15565 귀여운 라이언
public class Main {
	static int N, K;

	public static void main(String[] args) throws IOException {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int curSum = 0;
		int lt = 0;
		int rt = -1;

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int min = Integer.MAX_VALUE;
		while(true){
			if(curSum < K){
				rt ++;
				if(rt == N) break;
				if(arr[rt] == 1){
					curSum ++;
					if(curSum == K){
						min = Math.min(rt - lt + 1, min);
					}
				}
			}
			else{
				if(arr[lt] == 1){
					curSum --;
				}
				min = Math.min(min, rt - lt + 1);
				lt ++;
			}
		}
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
}
