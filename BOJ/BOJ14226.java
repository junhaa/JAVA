import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// BOJ #14226 이모티콘 
class Node{
	int copy;
	int num;
	public Node(int copy, int num) {
		this.copy = copy;
		this.num = num;
	}
}
public class Main {

	
	static int solution(int N) {
		boolean[][] visited = new boolean[4004][2002];
		Queue<Node> Q = new LinkedList<>();
		Q.offer(new Node(0, 1));
		int L = 0;
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int i = 0 ; i < length ; i ++) {
				Node tmp = Q.poll();
				if(tmp.num == N) return L;
				if(tmp.num > 0 && !visited[tmp.num - 1][tmp.copy]) {
					Q.offer(new Node(tmp.copy, tmp.num - 1));
					visited[tmp.num - 1][tmp.copy] = true;
					
				}
				if(tmp.num != 0 && tmp.num <= 1000 && !visited[tmp.num][tmp.num]) {
					Q.offer(new Node(tmp.num , tmp.num));
					visited[tmp.num][tmp.num] = true;
				}
				if(tmp.copy != 0 && tmp.num + tmp.copy <= 4000 && !visited[tmp.num + tmp.copy][tmp.copy]) {
					Q.offer(new Node(tmp.copy, tmp.num + tmp.copy));
					visited[tmp.num + tmp.copy][tmp.copy] =true;
				}
				
			}
			L ++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		System.out.println(T.solution(N));
	}
}
