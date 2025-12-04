package io.netty.example.JavaFuture;

import java.util.concurrent.*;

public class FutureDemo {

    public static void main(String[] args) throws Exception {

        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // 提交一个有返回值的任务
        Future<String> future = executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() +  "任务开始执行...");
            Thread.sleep(2000); // 模拟耗时操作
            return "任务执行结果：OK";
        });

        System.out.println(Thread.currentThread().getName() + "主线程继续执行...");

        // 检查是否完成
        System.out.println(Thread.currentThread().getName() + "任务是否完成？" + future.isDone());

        // 获取结果（阻塞）
        String result = future.get();
        System.out.println(Thread.currentThread().getName() + "任务返回结果：" + result);

        // 再次检查
        System.out.println(Thread.currentThread().getName() + "任务是否完成？" + future.isDone());

        executor.shutdown();
    }
}
