import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// BOJ #1032 명령 프롬프트
public class Main {
	public static String solution(int N, String[] arr) {
		String answer = "";
		for(int i = 0 ; i < arr[0].length(); i ++) {
			boolean flag = false;// 다른 문자가 있는지 검사
			char tmp = arr[0].charAt(i);
			for(int k = 1 ; k < N ; k ++) {
				if(arr[k].charAt(i) != tmp) flag = true;
			}
			if(flag) answer += '?';
			else answer += tmp;
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		String[] arr = new String[N];
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = br.readLine();
		}
		System.out.println(T.solution(N, arr));
	}
}
