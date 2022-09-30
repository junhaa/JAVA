import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2243 사탕상자
public class Main {
	static int[] arr;
	static int size = 2;
	
	
	static void update(int i, int val) {
		i += size / 2;
		arr[i] += val;
		while(i > 1) {
			i /= 2;
			arr[i] = arr[i * 2] + arr[i * 2 + 1];
		}
	}
	
	static int findNum(int nodeNum, int val) {
		while(true) {
			if(nodeNum >= size / 2) {
				update(nodeNum - size / 2, -1);
				return nodeNum - size / 2 + 1;
			}
			if(arr[nodeNum * 2] >= val) nodeNum *= 2;
			else {
				val -= arr[nodeNum * 2];
				nodeNum *= 2;
				nodeNum += 1;
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int a, b, c;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(true) {
			if(size >= 1000000) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		arr = new int[size];
		for(int i = 0 ; i < n; i ++) {
			st =  new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			if(a == 1) {
				b = Integer.parseInt(st.nextToken());
				sb.append(T.findNum(1, b) + "\n");
			}
			else {
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				T.update(b - 1, c);
			}
		}
		System.out.println(sb);
	}
}
