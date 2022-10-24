import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


// BOJ #5419 북서풍
class Point implements Comparable<Point>{
	int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(Point o) {
		if(o.x == this.x) return o.y - this.y;
		else return this.x - o.x;
	}
}
public class Main {
	
	static int[] tree;
	static int size;
	
	static void update(int i) {
		i += size / 2;
		tree[i] ++;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	
	static long findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return (long)findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int test = Integer.parseInt(br.readLine());
		while(test-->0) {
			ArrayList<Point> list = new ArrayList<>();
			int n = Integer.parseInt(br.readLine());
			int numY = 0;
			for(int i = 0 ; i < n ; i ++) {
				st = new StringTokenizer(br.readLine());
				list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			Collections.sort(list, new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					return o1.y - o2.y;
				}
			});
			int last = list.get(0).y;
			list.get(0).y = numY;
			for(int i = 1 ; i < n ; i ++) {
				if(list.get(i).y != last) numY ++;
				last = list.get(i).y;
				list.get(i).y = numY;
			}
			size = 2;
			while(true) {
				if(size >= numY + 1) {
					size *= 2;
					break;
				}
				size *= 2;
			}
			tree = new int[size];
			
			long answer = 0;
			Collections.sort(list);
			for(Point tmp : list) {
				answer += findSum(tmp.y, numY, 1, 0, size / 2 - 1);
				update(tmp.y);
			}
			
			bw.write(answer + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}      

}
