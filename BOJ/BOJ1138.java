import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1138 한 줄로 서기
public class Main {
	public int[] solution(int[] arr) {
		int[] answer = new int[arr.length];
		for(int i = 0 ; i < arr.length ; i ++) {
			int blank = 0;
			for(int k = 0 ; k < arr.length ; k ++) {
				if(answer[k] == 0) {
					if(blank == arr[i]) {
						answer[k] = i + 1;
						break;
					}
					blank ++;
				}
			}
		}
		return answer;
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {	
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int x : T.solution(arr)) {
			sb.append(x).append(" ");
		}
		System.out.println(sb);
	}
}
