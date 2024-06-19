package org.example.Implementacion;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class Node implements Runnable {
    private final int id;
    private final List<Node> neighbors;
    private final BlockingQueue<Message> messageQueue;

    public Node(int id) {
        this.id = id;
        this.neighbors = new CopyOnWriteArrayList<>();  // Safe for concurrent access
        this.messageQueue = new LinkedBlockingQueue<>();
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    public void sendMessage(Message message) {
        try {
            messageQueue.put(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Message receiveMessage() {
        try {
            return messageQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    @Override
    public void run() {
        while (true) {
            Message message = receiveMessage();
            if (message != null) {
                System.out.println("Nodo " + id + " recibió mensaje: " + message + ". Proveniente del nodo: " + message.getSourceId());
            }
        }
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }
}
