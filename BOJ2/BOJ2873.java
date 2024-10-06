import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// BOJ #2873 롤러코스터

public class Main {
	static final int[][] dirArrX = {{0, -1, 1, 0}, {-1, 0, 0, 1}}; // 가로, 세로
	static final int[][] dirArrY = {{-1, 0, 0, 1}, {0, -1, 1, 0}};

	static int minX = -1, minY = -1, minCost = Integer.MAX_VALUE, R, C;
	static boolean change = false;

	static boolean[][] visited;


	private static class FastIO {
		final private int BUFFER_SIZE = 1 << 16;
		final private int INT_SIZE = 10;
		private DataInputStream din;
		private DataOutputStream dout;

		private byte[] inbuffer;
		private int inputbufferpointer, bytesread;
		private byte[] outbuffer;
		private int outputbufferpointer;
		private byte[] bytebuffer;

		FastIO() {
			din = new DataInputStream(System.in);
			dout = new DataOutputStream(System.out);
			inputbufferpointer = bytesread = outputbufferpointer = 0;
			bytebuffer = new byte[INT_SIZE];
			inbuffer = new byte[BUFFER_SIZE];
			outbuffer = new byte[BUFFER_SIZE];
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte b = read();
			while (b <= ' ')
				b = read();
			boolean isneg = (b == '-');
			if (isneg)
				b = read();
			do {
				ret = ret * 10 + b - '0';
			} while ((b = read()) >= '0' && b <= '9');

			if (isneg)
				return -ret;
			return ret;
		}

		private byte read() throws IOException {
			if (inputbufferpointer == bytesread)
				fillbuffer();
			return inbuffer[inputbufferpointer++];
		}

		private void fillbuffer() throws IOException {
			bytesread = din.read(inbuffer, inputbufferpointer = 0, BUFFER_SIZE);
			if (bytesread == -1)
				inbuffer[0] = -1;
		}

		public void write(int i) {
			if (i == 0) {
				writebuffer((byte) '0');
				return;
			}

			if (i < 0) {
				writebuffer((byte) '-');
				i = -i;
			}

			int index = 0;
			while (i > 0) {
				bytebuffer[index++] = (byte) ((i % 10) + '0');
				i /= 10;
			}

			while (index-- > 0)
				writebuffer(bytebuffer[index]);
		}

		public void writels(int i) {
			write(i);
			writebuffer((byte)' ');
		}

		private void writebuffer(byte b) {
			if (outputbufferpointer == outbuffer.length) {
				flushbuffer();
			}
			outbuffer[outputbufferpointer++] = b;
		}

		private void flushbuffer() {
			if (outputbufferpointer != 0) {
				try {
					dout.write(outbuffer, 0, outputbufferpointer);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			outputbufferpointer = 0;
		}
	}
	public static void main(String[] args) throws IOException {
		Main main = new Main();

		FastIO io = new FastIO();

		R = io.nextInt();
		C = io.nextInt();

		visited = new boolean[2][C];

		for(int i = 0 ; i < R ; i ++){
			for(int j = 0 ; j < C ; j ++){
				if((i + j) % 2 == 1) main.checkMin(i, j, io.nextInt());
				else io.nextInt();
			}
		}

		StringBuilder sb = main.solve();
		System.out.println(sb);
	}

	private void checkMin(int r, int c, int cost){
		if(r == 0 && c == 0 || r == R - 1 && c == C - 1) return;
		if(minCost > cost){
			minY = r;
			minX = c;
			minCost = cost;
		}
	}

	/**
	 * 홀 + 홀 인 경우
	 * 어느 방향으로 시작해도 가능
	 *
	 * 짝 + 홀 인 경우
	 * C가 짝수 -> 가로
	 * R이 짝수 -> 세로
	 *
	 * 짝 + 짝인 경우가 중요
	 * r + c 가 홀수인 경우의 최솟값을 찾아야 함
	 *
	 * 최솟값을 제외한 경로로 이동
	 * 최초 경로 방향은 R, C 중 큰 값에 맞게
	 * R이 큰 경우 -> 세로 방향
	 * C가 큰 경우 -> 가로 방향
	 *
	 * 이후 Math.max(R, C) - 1 에 처음 도달하였을 때 방향을 전환해야 함
	 */


	private StringBuilder move4(int len ){
		StringBuilder sb = new StringBuilder();
		sb.append('D');
		for(int i = 0 ; i < len - 1 ; i ++){
			sb.append('L');
		}
		sb.append('D');
		for(int i = 0 ; i < len - 1 ; i ++){
			sb.append('R');
		}
		return sb;
	}
	private StringBuilder solve(){
		// 짝 + 짝
		if(R % 2 == 0 && C % 2 == 0){
			StringBuilder answer = new StringBuilder();
			StringBuilder tmp = move2(C);
			for (int i = 0; i < minY / 2; i++) {
				answer.append(tmp);
			}

			answer.append(move());

			StringBuilder tmp2 = move4(C);
			for(int i = 0 ; i < R / 2 - (minY / 2 + 1) ; i ++){
				answer.append(tmp2);
			}
			return answer;
		}
		// 홀 + 짝
		else if(R % 2 == 0){
			StringBuilder answer = new StringBuilder();
			StringBuilder repeat = new StringBuilder();
			for(int i = 0 ; i < R - 1 ; i ++){
				repeat.append('D');
			}
			repeat.append('R');
			for(int i = 0 ; i < R - 1 ; i ++){
				repeat.append('U');
			}
			repeat.append('R');

			for(int i = 0 ; i < C / 2 ; i ++){
				answer.append(repeat);
			}
			for(int i = 0 ; i < R - 1 ; i ++){
				answer.append('D');
			}
			return answer;
		}
		else if(C % 2 == 0){
			StringBuilder answer = new StringBuilder();
			StringBuilder tmp = move2(C);
			for(int i = 0 ; i < R / 2; i ++){
				answer.append(tmp);
			}
			for(int i = 0 ; i < C - 1 ; i ++){
				answer.append('R');
			}
			return answer;
		}
		// 홀 + 홀
		else{
			StringBuilder answer = new StringBuilder();
			StringBuilder tmp = move2(C);
			for(int i = 0 ; i < R / 2; i ++){
				answer.append(tmp);
			}
			for(int i = 0 ; i < C - 1 ; i ++){
				answer.append('R');
			}
			return answer;
		}
	}

	private StringBuilder move2(int len){
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < len - 1 ; i ++){
			sb.append('R');
		}
		sb.append('D');
		for(int i = 0 ; i < len - 1 ; i ++){
			sb.append('L');
		}
		sb.append('D');

		return sb;
	}

	private StringBuilder move(){ // 0 = 가로,  1 = 세로
		StringBuilder sb = new StringBuilder();
		visited[minY % 2][minX] = true;
		int curX = 0, curY = 0;
		int[] dy = dirArrY[1], dx = dirArrX[1];
		while(true){
			if(curX == C - 1 && curY == 1){
				return sb;
			}
			visited[curY][curX] = true;
			for(int i = 0 ; i < 4 ; i ++){
				int nx = curX + dx[i];
				int ny = curY + dy[i];
				if(nx >= 0 && ny >= 0 && nx < C && ny < 2 && !visited[ny][nx]){
					curX = nx;
					curY = ny;
					sb.append(dirToChar(dy[i], dx[i]));
					break;
				}
			}
		}
	}

	private boolean canChange(int curIdx, int dir){
		return (dir == 0 ? R - 2 : C - 2) == curIdx;
	}

	private Character dirToChar(int dy, int dx){
		if(dy == 1){
			return 'D';
		}
		else if(dy == 0){
			if(dx == 1){
				return 'R';
			}
			else return 'L';
		}
		else {
			return 'U';
		}

	}
}
