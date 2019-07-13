package com.muke.IO;

/*
NIO服务端
 */

import org.springframework.jmx.export.naming.IdentityNamingStrategy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    private  Selector selector;

    public void start(int port) throws IOException {

          //1、创建selector
         selector = Selector.open();
        //2、通过serversocketChannel 创建channel通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //3、为channel通道绑定监听的端口
        serverSocketChannel.bind(new InetSocketAddress(port));
        //4、设置channel为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //5、将channel注册到selector/上 监听连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动！");
        //6、循环等待接入的连接
        while (true){
            //TODO 获取channel的可用的数量
            int select = selector.select();
            // TODO 为什么要这样做
            if (select == 0) continue;
            //获取可用channel的集合
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                //移除Set中的当前的selectionKey
                iterator.remove();

                // 7、根据情况处理具体的业务逻辑
                //如果是接入事件
                if (selectionKey.isAcceptable()){
                    this.acceptHandler(serverSocketChannel);
                }
                //如果是 可读事件
                if(selectionKey.isReadable()){
                    this.readHandler(selectionKey);
                }
            }
        }
    }

    //处理接入事件
    private void acceptHandler(ServerSocketChannel serverSocketChannel) throws IOException {
        /**
         * 创建socketchannel
         *
         */
        SocketChannel socketChannel = serverSocketChannel.accept();

        /**
         * 将socketchannel 设置为非阻塞模式
         */
        socketChannel.configureBlocking(false);

        /**
         * 将channel注册到selector撒上监听可读事件
         */
        socketChannel.register(selector,SelectionKey.OP_READ);
        /**
         * 回复客户端提示的信息
         */
        socketChannel.write(Charset.forName("UTF-8").encode("请注意聊天的用词和安全"));

    }

    //处理可读事件
    private void readHandler(SelectionKey key) throws IOException {
        //从selectionKey 中获取就绪的channel
        SocketChannel channel = (SocketChannel) key.channel();
        //创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //循环读取客户端的请求信息
        String req = "";
        while (channel.read(byteBuffer)>1){
            //切换buffer为读模式
            byteBuffer.flip();
            req += Charset.forName("UTF-8").decode(byteBuffer);
        }
        //将channel再次注册到selector上监听其他的可读的事件
        channel.register(selector,SelectionKey.OP_READ);
        if (req.length()>0){
            //将客户端发送的请求的信息发送给其他的客户端
            broadCast(channel,req);
        }
    }

    private void broadCast(SocketChannel sourceChannel,String info) {
        //获取所有的已经接入的channel
        Set<SelectionKey> keys = selector.keys();
        for (SelectionKey key :keys){
            //循环向所有的channel发送信息
            Channel targetChannel = key.channel();
            if (targetChannel instanceof SocketChannel && targetChannel != sourceChannel){
                try {
                    ((SocketChannel) targetChannel).write(Charset.forName("UTF-8").encode(info));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        NIOServer nioServer = new NIOServer();
        nioServer.start(9999);
    }
}
