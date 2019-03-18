package com.jikuodong.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * ServerManager 中维护了一个线程安全的集合servers,
 * 用于因为浏览器发起连接请求而创建的BitCoinServer.
 * @author JKD
 * @version 1.0.0
 * @ClassName ServerManager.java
 * @createTime 2019年03月15日 16:21:00
 */
public class ServerManager {
    private  static Collection<BitCoinServer> servers = Collections.synchronizedCollection(new ArrayList<BitCoinServer>());

    /**
     * 将服务端的信息发往客户端
     * @method broadCast
     * @author JKD
     * @param msg 服务端信息
     * @return void
     * @data 2019/3/15 16:26
     */
    public static void broadCast(String msg){
        for (BitCoinServer bitCoinServer : servers) {
            try {
                bitCoinServer.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 获取websocket的数量
     * @method getTotal
     * @author JKD
     * @return int
     * @data 2019/3/15 16:31
     */
    public  static int getTotal() {
        return servers.size();
    }

    /**
     * 添加新连接
     * @method add
     * @author JKD
     * @param bitCoinServer 服务
     * @return void
     * @data 2019/3/15 16:34
     * @throws
     */
    public static void add(BitCoinServer bitCoinServer) {
        System.out.println("有新连接加入！ 当前总连接数是：" + servers.size());
        servers.add(bitCoinServer);
    }

    /**
     * 删除当前的server
     * @method remove
     * @author JKD
     * @param server 当前的server
     * @return void
     * @data 2019/3/15 16:49
     */
    public static void remove(BitCoinServer server){
        System.out.println("有连接退出！ 当前总连接数是：" + servers.size());
        servers.remove(server);
    }
}
