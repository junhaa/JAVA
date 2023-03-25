import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2512 예산
public class Main {

	static int[] arr;
	static int max = Integer.MIN_VALUE;
	
	static int search(int M) {
		int lt = 0;
		int rt = max;
		while(lt <= rt) {
			int mid = (lt + rt) / 2;
			int cur = 0;
			for(int tmp : arr) {
				if(tmp <= mid) cur += tmp;
				else cur += mid;
			}
			if(cur <= M) {
				lt = mid + 1;
			}
			else {
				rt = mid - 1;
			}
		}
		return rt;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}
		int M = Integer.parseInt(br.readLine());
		System.out.println(T.search(M));
	}
}
