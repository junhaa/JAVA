import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #2535 아시아 정보올림피아드
class Team implements Comparable<Team>{
	int con, num, score;
	public Team(int con, int num, int score) {
		this.con = con;
		this.num = num;
		this.score = score;
	}
	@Override
	public int compareTo(Team o) {
		return o.score - this.score;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Team> list = new ArrayList<>();
		for(int i = 0 ; i < N ; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			list.add(new Team(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list);
		int[] ch = new int[N];
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for(int i = 0 ; i < list.size() ; i ++) {
			Team tmp = list.get(i);
			if(ch[tmp.con] != 2) {
				sb.append(tmp.con + 1 + " " + tmp.num + "\n");
				ch[tmp.con] ++;
				if(++cnt == 3) break;
			}
		}
		System.out.println(sb);
	}
}
