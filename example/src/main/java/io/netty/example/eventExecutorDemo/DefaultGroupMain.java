package io.netty.example.eventExecutorDemo;

import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

public class DefaultGroupMain {

    public static void main(String[] args) {

        EventExecutorGroup group = new DefaultEventExecutorGroup(4);

        // 提交任务（自动轮询到某个 executor）
        group.submit(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
        });


    }


}
