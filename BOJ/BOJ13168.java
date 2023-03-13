import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

// BOJ #13168 내일로 여행 
public class Main {
	
	static HashMap<String, Integer> city = new HashMap<>();
	static double[][] noticket, ticket;
	static int N;
	
	static void floydWarshall() {
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < N ; j ++) {
				for(int k = 0 ; k < N ; k ++) {
					if(noticket[j][k] > noticket[j][i] + noticket[i][k]) noticket[j][k] = noticket[j][i] + noticket[i][k];
					if(ticket[j][k] > ticket[j][i] + ticket[i][k]) ticket[j][k] = ticket[j][i] + ticket[i][k];
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T=  new Main();
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		noticket = new double[N][N];
		ticket = new double[N][N];
		for(int i = 0 ; i < N ; i ++) {
			Arrays.fill(noticket[i], 987654321);
			Arrays.fill(ticket[i], 987654321);
		}
		for(int i = 0 ; i < N ; i ++) {
			noticket[i][i] = 0;
			ticket[i][i] = 0;
		}
		for(int i = 0 ; i < N ; i ++) {
			city.put(st.nextToken(), i);
		}
		int M = Integer.parseInt(br.readLine());
		StringTokenizer visit = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < K ; i ++) {
			st = new StringTokenizer(br.readLine());
			String tmp = st.nextToken();
			int start = city.get(st.nextToken());
			int end = city.get(st.nextToken());
			double cost = (double)Integer.parseInt(st.nextToken());
			noticket[start][end] = Math.min(noticket[start][end], cost);
			noticket[end][start] = Math.min(noticket[end][start], cost);
			if(tmp.equals("ITX-Saemaeul") || tmp.equals("ITX-Cheongchun") || tmp.equals("Mugunghwa")) {
				cost = 0;
			}
			else if(tmp.equals("S-Train") || tmp.equals("V-Train")) {
				cost /= 2;
			}
			ticket[start][end] = Math.min(ticket[start][end], cost);
			ticket[end][start] = Math.min(ticket[end][start], cost);
		}
		T.floydWarshall();
		double tcost = 0;
		double cost = 0;
		int last = city.get(visit.nextToken());
		for(int i = 1 ; i < M ; i ++) {
			int next = city.get(visit.nextToken());
			tcost += ticket[last][next];
			cost += noticket[last][next];
			last = next;
		}
		if(tcost + R < cost) System.out.println("Yes");
		else System.out.println("No");
	}
}
