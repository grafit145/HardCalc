package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("Server started!");
        while (true) {
            try (Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String msg;
                while ((msg = in.readLine()) != null) {
                    int n = Integer.parseInt(msg.trim());

                    int result = findNFibonacciLight(n);
                    out.println("Result: " + result);

                    if (msg.equals("end")) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static int findNFibonacci(int n) {
        if (n == 0) {
            return n;
        }
        if (n == 1) {
            return n;
        }
        return findNFibonacci(n - 1) + findNFibonacci(n - 2);
    }

    static int findNFibonacciLight(int n) {
        int a = 0;
        int b = 1;
        int fibonacci = a + b;
        if (n == 0 || n == 1) {
            return n;
        }
        while (n > 2) {
            a = b;
            b = fibonacci;
            fibonacci = a + b;
            n--;
        }
        return fibonacci;
    }
}