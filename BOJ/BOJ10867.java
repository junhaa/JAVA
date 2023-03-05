import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #10867 중복 빼고 정렬하기
public class Main {

	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		HashSet<Integer> set = new HashSet<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		ArrayList<Integer> list = new ArrayList<>(set);
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < list.size() ; i ++) {
			sb.append(list.get(i) + " ");
		}
		System.out.println(sb);
	}
}
