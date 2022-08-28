import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #13164 행복 유치원
public class Main {

	static ArrayList<Integer> list = new ArrayList<>();
	
	static int solution(int K) {
		Collections.sort(list);
		int answer = 0 ;
		for(int i = 0 ; i < list.size() - (K - 1) ; i ++) {
			answer += list.get(i);
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		for(int i = 0 ; i < N - 1 ; i ++) {
			list.add(arr[i + 1] - arr[i]);
		}
		if(list.isEmpty()) System.out.println(0);
		else System.out.println(T.solution(K));
	}
}
