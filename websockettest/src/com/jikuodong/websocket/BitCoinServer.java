package com.jikuodong.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * 比特币服务端
 * @ServerEndpoint 注解是一个类层次的注解，他的功能主要是将目前的类定义成一个websocket服务器端，
 * 注解的值将被用于箭筒用户连接的终端访问URL地址，客户端可以通过这个URL来连接到WebSocket服务器端
 * @author JKD
 * @version 1.0.0
 * @ClassName BitCoinServer.java
 * @createTime 2019年03月15日 15:44:00
 */
@ServerEndpoint("/ws/bitcoinServer")
public class BitCoinServer {

    // 与某个客户端的连接会话，需要通过他来给客户端发送数据
    private Session session;

    /**
     *  有浏览器链接过来的时候被调用
     * @method onOpen
     * @author JKD
     * @param session
     * @return void
     * @data 2019/3/18 9:53
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        ServerManager.add(this);
    }

    public void sendMessage (String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     *  客户端关闭，删除当前的websocket
     * @method onClose
     * @author JKD
     * @return void
     * @data 2019/3/15 16:12
     */
    @OnClose
    public void onClose() {
        ServerManager.remove(this);
    }

    /**
     *
     * @method onMessage
     * @author JKD
     * @param message 来自客户端的消息
     * @param session 当前的session
     * @return void
     * @data 2019/3/15 16:13
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息：" +message);
    }

    /**
     *  发生错误时
     * @method onError
     * @author JKD
     * @return void
     * @data 2019/3/15 16:15
     */
    @OnError
    public void onError (Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
}
