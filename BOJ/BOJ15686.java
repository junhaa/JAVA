// BOJ #15686 치킨 배달
import java.util.ArrayList;
import java.util.Scanner;
class Point{
	int x ;
	int y ;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static ArrayList<Point> house = new ArrayList<>();
	static ArrayList<Point> pizza = new ArrayList<>();
	static int[] combi ;
	static int answer = Integer.MAX_VALUE;
	public void DFS(int L, int S) {
		if( L == combi.length ) {
			int sum = 0;
			for(Point h : house) {
				int min = Integer.MAX_VALUE;
				for(int x : combi ) {
					min = Math.min(min, Math.abs(h.x - pizza.get(x).x) + Math.abs(h.y - pizza.get(x).y));
				}
				sum += min;
			}
			answer = Math.min(answer, sum);
		}
		else {
			for(int i = S ; i < pizza.size() ; i ++) {
				combi[L] = i;
				DFS(L + 1 , i + 1);
			}
		}
	}
	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		combi = new int[M];
		for(int i = 0 ; i < N ; i ++) {
			for(int k = 0 ; k < N ; k ++) {
				int tmp = sc.nextInt();
				if(tmp == 1) house.add(new Point(k,i));
				else if (tmp == 2) pizza.add(new Point(k,i));
			}
		}
		T.DFS(0, 0);
		System.out.println(answer);
	}
}