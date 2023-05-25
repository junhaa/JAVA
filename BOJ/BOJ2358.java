import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// BOJ #2358 평행선 
class Point{
	int y, x;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
public class Main {
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		HashMap<Integer, Boolean> mapy = new HashMap<Integer, Boolean>();
		HashMap<Integer, Boolean> mapx = new HashMap<Integer, Boolean>();
		int answer = 0;
		for(int i = 0 ; i < n ; i ++) {
			st = new StringTokenizer(br.readLine());
			int cy = Integer.parseInt(st.nextToken());
			int cx = Integer.parseInt(st.nextToken());
			if(!mapx.containsKey(cx)) mapx.put(cx, false);
			else mapx.put(cx, true);
			if(!mapy.containsKey(cy)) mapy.put(cy, false);
			else mapy.put(cy, true);
		}
		for(int cx : mapx.keySet()) {
			if(mapx.get(cx)) answer ++;
		}
		for(int cy : mapy.keySet()) {
			if(mapy.get(cy)) answer ++;
		}
		System.out.println(answer);
	}
}
