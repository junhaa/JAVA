import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #9328 열쇠
class Point{
	int x;
	int y;
	public Point(int y, int x) {
		this.x = x;
		this.y = y;
	}
}
public class Main {

	static HashSet<Character> key ;
	static HashMap<Character, ArrayList<Point>> hm; // 위치 저장
	static boolean[][] ch;
	static char[][] map;
	static Queue<Point> Q = new LinkedList<>();
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1 ,0 };
	static int answer, h, w;
	
	static void DFS(int x, int y) {
		ch[y][x] = true;
		if(map[y][x] == '$') answer ++;
		for(int i = 0 ; i < 4 ; i ++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			if(xx < 0 || yy < 0 || xx >= w  || yy >= h) continue;
			if(map[yy][xx] == '*' || ch[yy][xx]) continue;
			if(Character.isAlphabetic(map[yy][xx])) {
				//소문자
				if(Character.isLowerCase(map[yy][xx])) {
					if(!key.contains(map[yy][xx])) {
						if(hm.containsKey(Character.toUpperCase(map[yy][xx]))) {
							for(Point p : hm.get(Character.toUpperCase(map[yy][xx]))) {
								Q.offer(p);
							}
						}
						key.add(map[0][i]);
					}
					Q.offer(new Point(0, i));
				}
				//대문자 
				else if(Character.isUpperCase(map[yy][xx])) {
					// 열쇠가 있으면
					if(key.contains(Character.toLowerCase(map[yy][xx]))) DFS(xx, yy);
					// 열쇠가 없으면
					else {
						if(!hm.containsKey(map[yy][xx])) {
							hm.put(map[yy][xx], new ArrayList<>());
						}
						hm.get(map[yy][xx]).add(new Point(yy, xx));
					}
				}
			}
			else if(map[yy][xx] == '.' || map[yy][xx] == '$') DFS(xx, yy);
		}
	}
	
	static void solution() { // BFS
		for(int i = 0 ; i < w ; i ++) {
			if(Character.isAlphabetic(map[0][i])) {
				//소문자
				if(Character.isLowerCase(map[0][i])) {
					if(!key.contains(map[0][i])) {
						if(hm.containsKey(Character.toUpperCase(map[0][i]))) {
							for(Point p : hm.get(Character.toUpperCase(map[0][i]))) {
								Q.offer(p);
							}
						}
						key.add(map[0][i]);
					}
					Q.offer(new Point(0, i));
				}
				
				//대문자 
				else if(map[0][i] <= 90 && map[0][i] >= 65) {
					// 열쇠가 있으면
					if(key.contains(Character.toLowerCase(map[0][i]))) Q.offer(new Point(0, i));
					// 열쇠가 없으면
					else {
						if(!hm.containsKey(map[0][i])) {
							hm.put(map[0][i], new ArrayList<>());
						}
						hm.get(map[0][i]).add(new Point(0, i));
					}
				}
			}
			else if(map[0][i] == '.' || map[0][i] == '$') Q.offer(new Point(0, i)); //'.' 일 경우
		}
		for(int i = 1 ; i < h - 1 ; i ++) {
			if(Character.isAlphabetic(map[i][0])) {
				//소문자
				if(Character.isLowerCase(map[i][0])) {
					if(!key.contains(map[i][0])) {
						if(hm.containsKey(Character.toUpperCase(map[i][0]))) {
							for(Point p : hm.get(Character.toUpperCase(map[i][0]))) {
								Q.offer(p);
							}
						}
						key.add(map[i][0]);
					}
					Q.offer(new Point(i, 0));
				}
				//대문자 
				else if(map[i][0] <= 90 && map[i][0] >= 65) {
					// 열쇠가 있으면
					if(key.contains(Character.toLowerCase(map[i][0]))) Q.offer(new Point(i, 0));
					// 열쇠가 없으면
					else {
						if(!hm.containsKey(map[i][0])) {
							hm.put(map[i][0], new ArrayList<>());
						}
						hm.get(map[i][0]).add(new Point(i, 0));
					}
				}
			}
			else if(map[i][0] == '.' || map[i][0] == '$') Q.offer(new Point(i, 0)); //'.' 일 경우
		}
		for(int i = 1 ; i < h - 1 ; i ++) {
			if(Character.isAlphabetic(map[i][w - 1])) {
				//소문자
				if(Character.isLowerCase(map[i][w - 1])) {
					if(!key.contains(map[i][w - 1])) {
						if(hm.containsKey(Character.toUpperCase(map[i][w - 1]))) {
							for(Point p : hm.get(Character.toUpperCase(map[i][w - 1]))) {
								Q.offer(p);
							}
						}
						key.add(map[i][w - 1]);
					}
					Q.offer(new Point(i, w - 1));
				}
				//대문자 
				else if(map[i][w - 1] <= 90 && map[i][w - 1] >= 65) {
					// 열쇠가 있으면
					if(key.contains(Character.toLowerCase(map[i][w - 1]))) Q.offer(new Point(i, w - 1));
					// 열쇠가 없으면
					else {
						if(!hm.containsKey(map[i][w - 1])) {
							hm.put(map[i][w - 1], new ArrayList<>());
						}
						hm.get(map[i][w - 1]).add(new Point(i, w - 1));
					}
				}
			}
			else if(map[i][w - 1] == '.' || map[i][w - 1] == '$') Q.offer(new Point(i, w - 1)); //'.' 일 경우
		}
		for(int i = 0 ; i < w ; i ++) {
			if(Character.isAlphabetic(map[h - 1][i])) {
				//소문자
				if(Character.isLowerCase(map[h - 1][i])) {
					if(!key.contains(map[h - 1][i])) {
						if(hm.containsKey(Character.toUpperCase(map[h - 1][i]))) {
							for(Point p : hm.get(Character.toUpperCase(map[h - 1][i]))) {
								Q.offer(p);
							}
						}
						key.add(map[h - 1][i]);
					}
					Q.offer(new Point(h - 1, i));
				}
				//대문자 
				else if(map[h - 1][i] <= 90 && map[h - 1][i] >= 65) {
					// 열쇠가 있으면
					if(key.contains(Character.toLowerCase(map[h - 1][i]))) Q.offer(new Point(h - 1, i));
					// 열쇠가 없으면
					else {
						if(!hm.containsKey(map[h - 1][i])) {
							hm.put(map[h - 1][i], new ArrayList<>());
						}
						hm.get(map[h - 1][i]).add(new Point(h - 1, i));
					}
				}
			}
			else if(map[h - 1][i] == '.' || map[h - 1][i] == '$') Q.offer(new Point(h - 1, i)); //'.' 일 경우
		}
		while(!Q.isEmpty()) {
			Point tmp = Q.poll();
			if(!ch[tmp.y][tmp.x]) DFS(tmp.x, tmp.y);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		while(test -- > 0) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			ch = new boolean[h][w];
			map = new char[h][w];
			hm = new HashMap<>();
			key = new HashSet<>();
			answer = 0;
			for(int i = 0 ; i < h ; i ++) {
				String str = br.readLine();
				for(int k = 0 ; k < w ; k ++) {
					map[i][k] = str.charAt(k);
				}
			}
			String keyStr = br.readLine();
			if(keyStr.charAt(0) != '0') {
				for(int i = 0 ; i < keyStr.length() ; i ++) {
					if(!key.contains(keyStr.charAt(i))) key.add(keyStr.charAt(i));
				}
			}
			T.solution();
			sb.append(answer).append('\n');
		}
		System.out.println(sb);
	}
}
