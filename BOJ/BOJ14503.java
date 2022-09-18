import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #14503 ·Îº¿ Ã»¼Ò±â

class Robot {
	int dir; // ¹Ù¶óº¸´Â ¹æÇâ
	int px; // xÁÂÇ¥
	int py; // yÁÂÇ¥
	public Robot(int py, int px, int dir) {
		this.dir = dir;
		this.px = px;
		this.py = py;
	}
}
public class Main {
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int[][] map; // 0 = ºó Ä­   1 = º®   2 = Ã»¼ÒÇÑ Ä­
	
	static int solution(Robot tmp, int N, int M) {
		int answer = 0;
		boolean findB = false; // ºó Ä­À» Ã£¾Ò´ÂÁö
		while(true) {
			if(map[tmp.py][tmp.px] == 0) {
				map[tmp.py][tmp.px] = 2;
				answer ++;
			}
			for(int i = 1 ; i <= 4 ; i ++) {
				int nDir;
				int xx;
				int yy;
				if(tmp.dir - i < 0) nDir = tmp.dir - i + 4;
				else nDir = tmp.dir - i;
				xx = tmp.px + dx[nDir];
				yy = tmp.py + dy[nDir];
				if(map[yy][xx] == 0) {
					tmp.px = xx;
					tmp.py = yy;
					tmp.dir = nDir;
					findB = true;
					break;
				}
			}
			if(findB) {
				findB =false;
				continue;
			}
			if(map[tmp.py + dy[(tmp.dir + 2) % 4]][tmp.px + dx[(tmp.dir + 2) % 4]] == 2) {
				tmp.px = tmp.px + dx[(tmp.dir + 2) % 4];
				tmp.py = tmp.py + dy[(tmp.dir + 2) % 4];
				continue;
			}
			else return answer;
		}
		
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		map = new int[N][M];
		Robot rb = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k < M ; k ++) {
				map[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(T.solution(rb, N, M));
	}
}
