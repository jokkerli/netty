package io.netty.example.echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioKeyTestServer {

    public static void main(String[] args) throws IOException {
        // 1. 打开 ServerSocketChannel
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.bind(new InetSocketAddress(8888));

        // 2. 打开 Selector
        Selector selector = Selector.open();

        // 3. 注册 OP_ACCEPT，并保存返回的 key
        SelectionKey registerKey = serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("注册时的 SelectionKey: " + registerKey + ", hash=" + System.identityHashCode(registerKey));

        while (true) {
            System.out.println("\n=== 等待事件发生 ===");
            selector.select(); // 阻塞等待事件就绪

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();

            while (iter.hasNext()) {
                SelectionKey selectedKey = iter.next();
                iter.remove();

                System.out.println("就绪时的 SelectionKey: " + selectedKey
                        + ", hash=" + System.identityHashCode(selectedKey));

                // Compare
                System.out.println("注册 key == 就绪 key ? " + (registerKey == selectedKey));

                if (selectedKey.isAcceptable()) {
                    SocketChannel client = serverChannel.accept();
                    if (client != null) {
                        client.close();
                    }
                }
            }
        }
    }
}
