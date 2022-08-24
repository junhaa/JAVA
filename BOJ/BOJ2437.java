import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #2437 Àú¿ï
public class Main {

	static ArrayList<Integer> list = new ArrayList<>();
	
	static int solution(int N) {
		Collections.sort(list);
		int idx = 0;
		int sum = 0;
		if(list.get(0) != 1) return 1;
		sum += list.get(idx ++); 
		while(true) {
			if(idx >= list.size()) return ++sum;
			if(list.get(idx) <= sum + 1) {
				sum += list.get(idx);
				idx ++;
			}
			else return ++sum;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		System.out.println(T.solution(N));
	}
}
