package codingTest_063;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point {
	public int x, y;
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
}

public class Main {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int[][] box;
	public int BFS(int a, int b) { // a == x , b == y 
		Queue<Point> Q = new LinkedList<>();
		int num = 0;
		for(int i = 0 ; i < b; i ++) {
			for(int k = 0 ; k < a; k ++) {
				if(box[i][k] == 1) {
					Q.offer(new Point(k,i));
				}
				else if (box[i][k] == 0) {
					num ++;
				}
				
			}
		}
		int ripe = 0;
		int L = 0;
		while(!Q.isEmpty()) {
			if(ripe == num) {
				return L;
			}
			else {
				int len = Q.size();
				for(int i = 0; i < len ; i ++) {
					Point tmp = Q.poll();
					//System.out.print(tmp.x + " ");
					//System.out.println(tmp.y);
					for(int k = 0 ; k < 4 ; k ++) {
							int nx = tmp.x + dx[k];
							int ny = tmp.y + dy[k];

							if(nx >= 0 && ny >= 0 && nx <= a-1 && ny <= b-1 && box[ny][nx] == 0) {
								box[ny][nx] = 1;
								ripe ++;
								Q.offer(new Point(nx,ny));
							}
					}
					
				}
			}
			
			L++;
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		box = new int[b][a];
		for(int i = 0 ; i < b; i ++) {
			for(int k = 0 ; k < a ; k ++) {
				box[i][k] = sc.nextInt();
			}
		}
		
		System.out.println(T.BFS(a, b));
	}
}
