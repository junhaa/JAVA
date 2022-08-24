import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #1946 신입 사원
class Rank implements Comparable<Rank> {
	int interview;
	int document;
	public Rank(int document, int interview) {
		this.interview = interview;
		this.document = document;
	}
	@Override
	public int compareTo(Rank o) {
		return this.interview - o.interview;
	}
}
public class Main {
	static ArrayList<Rank> list;
	
	
	static int solution(int N) {
		Collections.sort(list);
		int min = list.get(0).document;
		int cnt = 1;
		for(int i = 1 ; i < N ; i ++) {
			if(list.get(i).document < min) {
				cnt ++ ;
				min = list.get(i).document;
			}
		}
		return cnt;
	}
	
	 public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		while(test -- > 0) {
			list = new ArrayList<>();
			int N = Integer.parseInt(br.readLine());
			for(int i = 0 ; i < N ; i ++) {
				st = new StringTokenizer(br.readLine());
				int document = Integer.parseInt(st.nextToken());
				int interview = Integer.parseInt(st.nextToken());
				list.add(new Rank(document ,interview));
			}	
			sb.append(T.solution(N)).append('\n');
		}
		System.out.println(sb);
	 }
}
