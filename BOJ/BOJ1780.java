import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

// BOJ #1780 종이의 개수
public class Main {

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
	
	static int[][] prefix;
	static int[] dx = { 0, 1, 2, 0, 1, 2, 0, 1, 2 };
	static int[] dy = { 0, 0, 0, 1, 1, 1, 2, 2, 2 };
	static int[] answer = new int[3]; // -1, 0, 1의 개수 
	static int getSum(int y1, int x1, int y2, int x2) {
		return prefix[y1][x1] - prefix[y2 - 1][x1] - prefix[y1][x2 - 1] + prefix[y2 - 1][x2 - 1];
	}
	
	static boolean isZero(int y1, int x1, int y2, int x2) {
		for(int i = y2 ; i <= y1 ; i ++) {
			for(int j = x2 ; j <= x1 ; j ++ ) {
				if(getSum(i, j, i, j) != 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	static void DAQ(int N, int cury, int curx) { 
		int tmpSum = getSum(cury + N - 1, curx + N - 1, cury, curx);
		if(N == 1) {
			answer[tmpSum + 1] ++;
			return;
		}
		if(tmpSum == N * N) {
			answer[2] ++;
			return;
		}
		
		if(tmpSum == -1 * N * N) {
			answer[0] ++;
			return;
		}
		
		if(tmpSum == 0) {
			if(isZero(cury + N - 1, curx + N - 1, cury, curx)) {
				answer[1] ++;
				return;				
			}
		}
		
		for(int i = 0 ; i < 9 ; i ++) {
			int ny = cury + N / 3 * dy[i];
			int nx = curx + N / 3 * dx[i];
			DAQ(N / 3, ny, nx);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		FastIO myIO = new FastIO();
		int N = myIO.nextInt();
		prefix = new int[N + 1][N + 1];
		for(int i = 1 ; i <= N ; i ++) {
			for(int j = 1 ; j <= N ; j ++) {
				prefix[i][j] = prefix[i][j - 1] + myIO.nextInt();
			}
		}
		for(int i = 1 ; i <= N ; i ++) {
			for(int j = 1 ; j <= N ; j ++) {
				prefix[i][j] += prefix[i - 1][j];
			}
		}
		T.DAQ(N, 1, 1);
		System.out.println(answer[0] + "\n" + answer[1] + "\n" + answer[2]);
	}
}
