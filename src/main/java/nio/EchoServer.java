package nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EchoServer {

  private final InetAddress addr;
  private final int port;

  private Map<SocketChannel, ByteBuffer> dataMap = new HashMap<>();

  public EchoServer(InetAddress addr, int port) {
    this.addr = addr;
    this.port = port;
  }

  public static void main(String[] args) throws Exception {
    EchoServer echoServer = new EchoServer(null, 8989);
    echoServer.startServer();
  }

  private void startServer() throws IOException {

    ServerSocketChannel serverChannel = newNonBlockingServerChannel();

    bindToPort(serverChannel);

    Selector selector = newSelector(serverChannel);

    log("Echo server ready. Ctrl-C to stop.");

    while (true) {
      processEvents(selector);
    }
  }

  private Selector newSelector(ServerSocketChannel serverChannel) throws IOException {
    Selector selector = Selector.open();
    serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    return selector;
  }

  private void processEvents(Selector selector) throws IOException {
    selector.select();
    Iterator keys = selector.selectedKeys().iterator();
    while (keys.hasNext()) {
      SelectionKey key = (SelectionKey) keys.next();
      keys.remove();
      if (key.isValid()) {
        if (key.isAcceptable()) {
          accept(key);
        } else if (key.isReadable()) {
          read(key);
        } else if (key.isWritable()) {
          write(key);
        }
      }
    }
  }

  private void bindToPort(ServerSocketChannel serverChannel) throws IOException {
    InetSocketAddress address = new InetSocketAddress(this.addr, this.port);
    serverChannel.socket().bind(address);
  }

  private ServerSocketChannel newNonBlockingServerChannel() throws IOException {
    ServerSocketChannel serverChannel = ServerSocketChannel.open();
    serverChannel.configureBlocking(false);
    return serverChannel;
  }

  private void accept(SelectionKey key) throws IOException {
    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
    SocketChannel channel = serverChannel.accept();
    channel.configureBlocking(false);

    // write welcome message
    channel.write(ByteBuffer.wrap("Welcome, this is the echo server\r\n".getBytes("US-ASCII")));

    Socket socket = channel.socket();
    SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
    log("Connected to: " + remoteSocketAddress);

    // register channel with selector for further IO
    dataMap.put(channel, ByteBuffer.allocate(80));
    channel.register(key.selector(), SelectionKey.OP_READ);
  }

  private void read(SelectionKey key) throws IOException {
    SocketChannel channel = (SocketChannel) key.channel();

    ByteBuffer buffer = dataMap.get(channel);
    int numRead = -1;
    try {
      numRead = channel.read(buffer);
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (numRead == -1) {
      channel.close();
      key.cancel();
      this.dataMap.remove(channel);
      Socket socket = channel.socket();
      SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
      log("Connection closed by client: " + remoteSocketAddress);
    } else {
      log("Got: " + StandardCharsets.UTF_8.decode(ByteBuffer.wrap(buffer.array(), 0, numRead -2)));
      buffer.flip();
      key.interestOps(SelectionKey.OP_WRITE);
    }
  }

  private void write(SelectionKey key) throws IOException {
    SocketChannel socketChannel = (SocketChannel) key.channel();
    ByteBuffer pendingData = this.dataMap.get(socketChannel);
    socketChannel.write(pendingData);
    if(!pendingData.hasRemaining()) {
      pendingData.compact();
      key.interestOps(SelectionKey.OP_READ);
    }
  }

  private static void log(String s) {
    System.out.println(s);
  }
}