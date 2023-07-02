import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #14921 용액 합성하기 
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int lt = 0;
		int rt = N - 1;
		int min = arr[rt] + arr[lt];
		if(arr[rt] + arr[lt] < 0) {
			lt ++;
		}
		else rt --;
		while(lt < rt) {
			int cur = arr[rt] + arr[lt];
			if(Math.abs(cur) < Math.abs(min)) min = cur; 
			if(cur < 0) lt ++;
			else rt --;
		}
		System.out.println(min);
	}
}
