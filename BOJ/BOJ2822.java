import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// BOJ #2822 점수 계산
class Score implements Comparable<Score>{
	int score, idx;
	public Score(int score, int idx) {
		this.score = score;
		this.idx = idx;
	}
	@Override
	public int compareTo(Score o) {
		return o.score - this.score;
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		ArrayList<Score> list = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0 ; i < 8 ; i ++) {
			list.add(new Score(Integer.parseInt(br.readLine()), i));
		}
		int answer= 0 ;
		String seq = "";
		boolean[] ch = new boolean[8];
		Collections.sort(list);
		for(int i = 0 ; i < 5 ; i ++) {
			answer += list.get(i).score;
			ch[list.get(i).idx] = true;
		}
		System.out.println(answer);
		for(int i = 0 ; i < 8 ; i ++) {
			if(ch[i]) {
				System.out.print(i + 1 + " ");
			}
		}
	}
}
