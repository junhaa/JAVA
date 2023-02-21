import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #27495 만다리트 만들기
class Goal implements Comparable<Goal>{
	String Goal;
	int x, y;
	public Goal(String s) {
		Goal = s;
	}
	public Goal(String s, int y, int x) {
		Goal = s;
		this.y = y;
		this.x = x;
	}
	@Override
	public int compareTo(Goal o) {
		for(int i = 0 ; i < Math.min(o.Goal.length(), this.Goal.length()) ; i ++) {
			if(o.Goal.charAt(i) != this.Goal.charAt(i)) {
				return this.Goal.charAt(i) - o.Goal.charAt(i);
			}
		}
		return this.Goal.length() - o.Goal.length();
	}
}
public class Main {

	static ArrayList<ArrayList<Goal>> list = new ArrayList<>();
	static String[][] map = new String[9][9];
	static int[] dx = { -1, 0, 1, 1, 1, 0, -1, -1};
	static int[] dy = { -1, -1, -1, 0, 1, 1, 1, 0};
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i = 0 ; i < 9 ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 9 ; j ++) {
				map[i][j] = st.nextToken();
			}
		}
		ArrayList<Goal> middle = new ArrayList<>();
		for(int i = 1 ; i <= 3 ; i ++) {
			for(int j = 1 ; j <= 3 ; j ++) {
				if(i == 2 && j == 2) continue;
				middle.add(new Goal(map[3 * i - 2][3 * j - 2], 3 * i - 2, 3 * j - 2));
			}
		}
		Collections.sort(middle);
		for(int i = 0 ; i <= 7 ; i ++) {
			Goal tmp = middle.get(i);
			ArrayList<Goal> cur = new ArrayList<>();
			for(int j = 0 ; j < 8 ; j ++) {
				int nx = tmp.x + dx[j];
				int ny = tmp.y + dy[j];
				cur.add(new Goal(map[ny][nx]));
			}
			Collections.sort(cur);
			list.add(cur);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1 ; i <= 8 ; i ++) {
			sb.append("#" + i + ". " + middle.get(i - 1).Goal + "\n");
			for(int j = 1 ; j <= 8 ; j ++) {
				sb.append("#" + i + "-" + j +". " + list.get(i - 1).get(j - 1).Goal + "\n");
			}
		}
		System.out.println(sb);
	}
}
