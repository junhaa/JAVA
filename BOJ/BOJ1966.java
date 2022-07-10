import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ #1966 프린터 큐
class Document{
	int num;
	int imp;
	public Document(int num, int imp) {
		this.num = num;
		this.imp = imp;
	}
}
public class Main {
	public int solution(int N, int M, Queue<Document> Q) {
		int answer = 0;
		while(!Q.isEmpty()) {
			int max = Integer.MIN_VALUE;
			for(int i = 0 ; i < Q.size() ; i ++) {
				Document tmp = Q.poll();
				max = Math.max(max, tmp.imp);
				Q.offer(tmp);
			}
			for(int k = 0 ; k < Q.size() ; k ++) {
				if(Q.peek().imp < max) {
					Q.offer(Q.poll());
				}
			}
			Document tmp = Q.poll();
			answer ++;
			if(tmp.num == M) return answer;
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st ;
		while(test-- != 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());// 문서의 개수
			int M = Integer.parseInt(st.nextToken());// 순서를 알고 싶은 문서의 위치
			Queue<Document> Q = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i ++) {
				Q.offer(new Document(i, Integer.parseInt(st.nextToken())));
			}
			System.out.println(T.solution(N, M, Q));
			
		}
	
	}
}
