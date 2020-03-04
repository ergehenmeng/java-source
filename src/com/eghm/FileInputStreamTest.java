package com.eghm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamTest implements ActionListener {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("/User/wyb/IdeaProjects/logback.xsd");
        int read = fis.read();
    }

    public synchronized void test(){
        try {
            wait();
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
