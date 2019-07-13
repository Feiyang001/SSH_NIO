package com.muke.IO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 *  客户端线程类  负责接收服务器端响应的信息
 */

public class NioClientHandler implements Runnable {

    private Selector selector;

    public NioClientHandler(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        while (true) {
            //TODO 获取channel的可用的数量
            try {
                int select = selector.select();
                // TODO 为什么要这样做
                if (select == 0) continue;
                //获取可用channel的集合
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    //移除Set中的当前的selectionKey
                    iterator.remove();
                    //如果是 可读事件
                    if (selectionKey.isReadable()) {
                        this.readHandler(selectionKey);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //处理可读事件
    private void readHandler(SelectionKey key) throws IOException {
        //从selectionKey 中获取就绪的channel
        SocketChannel channel = (SocketChannel) key.channel();
        //创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //循环读取服务器的响应信息
        String info = "";
        while (channel.read(byteBuffer)>1){
            //切换buffer为读模式
            byteBuffer.flip();
            info += Charset.forName("UTF-8").decode(byteBuffer);
        }
        //将channel再次注册到selector上监听其他的可读的事件
        channel.register(selector,SelectionKey.OP_READ);
        if (info.length()>0){
            System.out.println(info);
        }
    }
}
