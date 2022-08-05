import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #1525 ∆€¡Ò
class Point{
	int blank;
	String map;
	public Point(int blank, String map) {
		this.blank = blank;
		this.map = map;
	}
}
public class Main {
	static Queue<Point> Q = new LinkedList<>();
	static HashSet<String> ch = new HashSet<>();
	static void move(Point x, int i) {
		int blank = x.blank;
		switch (i) {
		case 0: { // UP
			if(blank > 2) {
				char[] arr = x.map.toCharArray();
				StringBuffer sb = new StringBuffer(x.map);
				sb.setCharAt(blank, sb.charAt(blank - 3));
				sb.setCharAt(blank - 3 , '0');
				String str = sb.toString();
				if(!ch.contains(str)) {
					Q.offer(new Point(blank - 3, str));
					ch.add(str);
				}
			}
			break;
		}
		case 1:{ // RIGHT
			if(blank != 2 && blank != 5 && blank != 8) {
				StringBuffer sb = new StringBuffer(x.map);
				sb.setCharAt(blank, sb.charAt(blank + 1));
				sb.setCharAt(blank + 1 , '0');
				String str = sb.toString();
				if(!ch.contains(str)) {
					Q.offer(new Point(blank + 1, str));
					ch.add(str);
				}
			}
			break;
		}
		case 2:{ // DOWN
			if(blank <= 5) {
				StringBuffer sb = new StringBuffer(x.map);
				sb.setCharAt(blank, sb.charAt(blank + 3));
				sb.setCharAt(blank + 3 , '0');
				String str = sb.toString();
				if(!ch.contains(str)) {
					Q.offer(new Point(blank + 3, str));
					ch.add(str);
				}
			}
			break;
		}
		case 3:{ // LEFT
			if(blank != 0 && blank != 3 && blank != 6) {
				StringBuffer sb = new StringBuffer(x.map);
				sb.setCharAt(blank, sb.charAt(blank - 1));
				sb.setCharAt(blank - 1, '0');
				
				String str = sb.toString();
				if(!ch.contains(str)) {
					Q.offer(new Point(blank - 1, str));
					ch.add(str);
				}
			}
			break;
		}
		}
	}
	
	static int solution(String map) {
		int answer = 0;
		Q.offer(new Point(map.indexOf('0'), map));
		ch.add(map);
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int i = 0 ; i < length ; i ++) {
				Point tmp = Q.poll();
				if(tmp.blank == 8) {
					if(tmp.map.equals("123456780")) return answer;
				}
				for(int k = 0 ; k < 4 ; k ++) {
					move(tmp, k);
				}
			}
			answer ++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		String map = "";
		for(int i = 0 ; i < 3 ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k < 3 ; k ++){
				map += Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(T.solution(map));
	}
}
