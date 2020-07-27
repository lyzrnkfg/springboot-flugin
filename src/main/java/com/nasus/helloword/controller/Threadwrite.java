package com.nasus.helloword.controller;

public class Threadwrite {

    public static void main(String[] args) {

        Thread t = new MyThread();
        t.start(); // 启动新线程

    }

}


class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("start new thread!");
    }
}


