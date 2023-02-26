import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #5588 별자리 찾기 
class Move {
	int x, y;
	public Move(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
public class Main {

	static ArrayList<Move> list = new ArrayList<>();
	static HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int curx = Integer.parseInt(st.nextToken());
		int cury = Integer.parseInt(st.nextToken());
		int startx = curx;
		int starty = cury;
		for(int i = 1 ; i < m ; i ++) {
			st = new StringTokenizer(br.readLine());
			int nextx = Integer.parseInt(st.nextToken());
			int nexty = Integer.parseInt(st.nextToken());
			list.add(new Move(nexty - cury, nextx - curx));
			curx = nextx;
			cury = nexty;
		}
		int n = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < n ; i ++) {
			st = new StringTokenizer(br.readLine());
			int cx = Integer.parseInt(st.nextToken());
			int cy = Integer.parseInt(st.nextToken());
			if(!map.containsKey(cx)) {
				map.put(cx, new HashSet<>());
			}
			map.get(cx).add(cy);
		}
		for(int x : map.keySet()) {
			for(int y : map.get(x)) {
				boolean flag = true;
				int curpx = x;
				int curpy = y;
				for(int i = 0 ; i < list.size() ; i ++) {
					int nextpx = curpx + list.get(i).x;
					int nextpy = curpy + list.get(i).y;
					if(map.containsKey(nextpx)) {
						if(map.get(nextpx).contains(nextpy)) {
							curpx = nextpx;
							curpy = nextpy;
						}
						else {
							flag = false;
							break;
						}
					}
					else {
						flag = false;
						break;
					}
				}
				if(flag) {
					System.out.println((x - startx) + " " + (y - starty));
					return;
				}
			}
		}
	}
}
