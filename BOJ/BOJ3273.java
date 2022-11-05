import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #3273 두 수의 합
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int answer = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int lt = 0, rt = n - 1;
		int x = Integer.parseInt(br.readLine());
		while(lt < rt) {
			if(arr[lt] + arr[rt] == x) answer ++;
			if(arr[lt] + arr[rt] >= x) {
				rt --;
			}
			else lt ++;
		}
		System.out.println(answer);
	}
}
