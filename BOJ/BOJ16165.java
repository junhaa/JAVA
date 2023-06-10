import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

// BOJ #16165 걸그룹 마스터 준식이
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashMap<String, String> member = new HashMap<>();
		HashMap<String, ArrayList<String>> team = new HashMap<>();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0 ; i < N ; i ++) {
			String cur = br.readLine();
			int num = Integer.parseInt(br.readLine());
			team.put(cur, new ArrayList<>());
			for(int j = 0 ; j < num ; j ++) {
				String tmp = br.readLine();
				member.put(tmp, cur);
				team.get(cur).add(tmp);
			}
		}
		for(int i = 0 ; i < M ; i ++) {
			String cur = br.readLine();
			int flag = Integer.parseInt(br.readLine());
			if(flag == 1) {
				sb.append(member.get(cur) + "\n"); 
			}
			else {
				ArrayList<String> mem = team.get(cur);
				Collections.sort(mem);
				for(String t : mem) {
					sb.append(t + "\n");
				}
			}
		}
		System.out.println(sb);
	}
}
