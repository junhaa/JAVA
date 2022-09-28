import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #2513 통학버스
class Student implements Comparable<Student>{
	int dis; // 학교로 부터 거리
	int num; // 학생 수
	public Student(int dis, int num) {
		this.dis = dis;
		this.num = num;
	}
	@Override
	public int compareTo(Student o) {
		return o.dis - this.dis;
	}
}
public class Main {

	static PriorityQueue<Student> rQ = new PriorityQueue<>();
	static PriorityQueue<Student> lQ = new PriorityQueue<>();
	
	static int solution(int K) {
		int answer = 0;
		while(!rQ.isEmpty()) {
			Student tmp = rQ.poll();
			int cap = K;
			answer += tmp.dis * 2;
			if(cap > tmp.num) {
				cap -= tmp.num;
				while(!rQ.isEmpty()) {
					tmp = rQ.poll();
					if(cap > tmp.num) {
						cap -= tmp.num;
					}
					else {
						if(cap != tmp.num) {
							tmp.num -= cap;
							rQ.offer(tmp);
						}
						break;
					}
				}
			}
			else {
				if(cap != tmp.num) {
					tmp.num -= cap;
					rQ.offer(tmp);
				}
			}
		}
		while(!lQ.isEmpty()) {
			Student tmp = lQ.poll();
			int cap = K;
			answer += tmp.dis * 2;
			if(cap > tmp.num) {
				cap -= tmp.num;
				while(!lQ.isEmpty()) {
					tmp = lQ.poll();
					if(cap > tmp.num) {
						cap -= tmp.num;
					}
					else {
						if(cap != tmp.num) {
							tmp.num -= cap;
							lQ.offer(tmp);
						}
						break;
					}
				}
			}
			else {
				if(cap != tmp.num) {
					tmp.num -= cap;
					lQ.offer(tmp);
				}
			}
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 아파트 단지의 수
		int K = Integer.parseInt(st.nextToken()); // 통학버스의 정원
		int S = Integer.parseInt(st.nextToken()); // 학교의 위치
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 아파트의 위치
			int b = Integer.parseInt(st.nextToken()); // 학생 수
			if(a < S) lQ.offer(new Student(S - a, b));
			else rQ.offer(new Student(a - S, b));
		}
		System.out.println(T.solution(K));
	}
}
