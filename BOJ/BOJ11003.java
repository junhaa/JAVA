import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

// BOJ #11003 최솟값 찾기
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
	
	static int[] tree = new int[16777216];
	static int size = 16777216;
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
		}
	}
	
	static int getMin(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return Integer.MAX_VALUE;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return Math.min(getMin(L, R, nodeNum * 2, nodeL, mid), getMin(L, R, nodeNum * 2 + 1, mid + 1, nodeR));
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		FastIO myIO = new FastIO();
		int N = myIO.nextInt();
		int L = myIO.nextInt();
		for(int i = 0 ; i < N ; i ++) {
			if(i < N) {
				tree[size / 2 + i] = myIO.nextInt();
			}
			else tree[size / 2 + i] = Integer.MAX_VALUE;
		}
		T.construct();
		for(int i = 0 ; i < N ; i ++) {
			myIO.writels(T.getMin(i - L + 1 < 0 ? 0 : i - L + 1, i, 1, 0, size / 2 - 1));
		}
		myIO.flushbuffer();
	}
}
