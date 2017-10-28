package hulub;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SocketPoolTest {

    class Socket {
        public Socket(String host, int port) { }
        public void close() { }
        public String getPort() { return null; }
    }

    interface SocketPool {
        Socket allocate(String host, int port);
        void release(Socket s);
    }

    class SimplePool implements SocketPool {
        public Socket allocate(String host, int port) {
            return new Socket(host, port);
        }

        public void release(Socket s) {
            s.close();
        }
    }

    class KeepalivePool implements SocketPool {
        private Map connections = new HashMap();

        public Socket allocate(String host, int port) {
            Socket connection = (Socket) connections.get(host + ":" + port);
            if (connection == null)
                connection = new Socket(host, port);
            return connection;
        }

        public void release(Socket s) {
            String host = "host";
            connections.put(host + ":" + s.getPort(), s);
        }
    }

    class ProtocolHandler2 {

        protected Socket allocate(String host, int port) {
            return new Socket(host, port);
        }

        protected void release(Socket s) {
            s.close();
        }

        public void process(String host, int port) {
            Socket in = allocate(host, port);
            release(in);
        }
    }

    class KeepAliveProtocolHandler extends ProtocolHandler2 {
        private Map connections = new HashMap();

        public Socket allocate(String host, int port) {
            Socket connection = (Socket) connections.get(host + ":" + port);
            if (connection == null)
                connection = new Socket(host, port);
            return connection;
        }

        public void release(Socket s) {
            String host = "host";
            connections.put(host + ":" + s.getPort(), s);
        }
    }

    @Test
    public void test2() {
        KeepAliveProtocolHandler handler = new KeepAliveProtocolHandler();
        handler.process("host", 3);
    }
}
