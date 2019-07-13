package com.muke.IO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class NIOClient {

    public void  start(int port,String name) throws IOException {
        //连接服务器端
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(port));

        //接收服务器端的响应  新开一个线程，专门用来接收服务器的响应
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        new Thread(new NioClientHandler(selector)).start();

        //向服务器发送信息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String req = scanner.nextLine();
            if(req != null && req.length()>0){
                socketChannel.write(Charset.forName("UTF-8").encode(name+":"+req));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //new NIOClient().start(9999);
    }
}
