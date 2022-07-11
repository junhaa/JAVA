import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #1205 등수 구하기
public class Main {
	public static int solution(int N, int newsc, int P, ArrayList<Integer> arr) { 
		Collections.sort(arr, Collections.reverseOrder());
		int rank = 1, tmp, i = 0;
		while(i < N) {
			int cnt = 1;
			tmp = arr.get(i);
			if(newsc > tmp) return rank;
			while(i < N - 1 && arr.get(i+1) == tmp) {
				i ++;
				cnt ++;
			}
			if(tmp == newsc) {
				i ++;
				if(i < P) return rank;
				else return -1;
			}
			i++;
			rank += cnt;
		}
		if(i < P) return rank;
		else return -1;
	}
	
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int newsc = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		if(N == 0) {
			System.out.println(1);
			return;
		}
		ArrayList<Integer> arr = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		System.out.println(T.solution(N, newsc, P, arr));
	}
}
