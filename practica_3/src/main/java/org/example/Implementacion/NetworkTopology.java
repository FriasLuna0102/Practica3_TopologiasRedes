package org.example.Implementacion;

import java.util.List;

public interface NetworkTopology {
    void configureNetwork(int numberOfNodes);
    void addNode(Node node);
    void connectNodes(Node node1, Node node2);
    void sendMessage(Message message);
    List<Node> getNodes();
}