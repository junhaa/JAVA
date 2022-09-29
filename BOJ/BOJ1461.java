import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #1461 도서관
public class Main {
	static ArrayList<Integer> arr1 = new ArrayList<>();
	static ArrayList<Integer> arr2 = new ArrayList<>();
	
	static int solution(int M) {
		int answer = 0;
		int max;
		if(arr1.isEmpty()) max = arr2.get(0);
		else if(arr2.isEmpty()) max = arr1.get(0);
		else max = Math.max(arr1.get(0), arr2.get(0));
		if(!arr1.isEmpty()) {
			for(int i = 0 ; i <= arr1.size() - 1 ; i += M) {
				answer += arr1.get(i) * 2;
			}
		}
		if(!arr2.isEmpty()) {
			for(int i = 0 ; i <= arr2.size() - 1 ; i += M) {
				answer += arr2.get(i) * 2;
			}
		}
		return answer - max;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			if(tmp < 0) {
				arr1.add(tmp * -1);
			}
			else {
				arr2.add(tmp);
			}
		}
		Collections.sort(arr1, Collections.reverseOrder());
		Collections.sort(arr2, Collections.reverseOrder());
		System.out.println(T.solution(M));
	}
}
