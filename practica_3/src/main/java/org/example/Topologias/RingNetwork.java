package org.example.Topologias;

import org.example.Implementacion.Message;
import org.example.Implementacion.NetworkTopology;
import org.example.Implementacion.Node;

import java.util.ArrayList;
import java.util.List;

public class RingNetwork implements NetworkTopology {
    private List<Node> nodes;

    @Override
    public void configureNetwork(int numberOfNodes) {
        nodes = new ArrayList<>();
        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(new Node(i));
        }

        // Conectar los nodos en forma de anillo
        for (int i = 0; i < numberOfNodes; i++) {
            int next = (i + 1) % numberOfNodes;
            connectNodes(nodes.get(i), nodes.get(next));
        }
    }

    @Override
    public void addNode(Node node) {
        nodes.add(node);
    }

    @Override
    public void connectNodes(Node node1, Node node2) {
        node1.addNeighbor(node2);
        node2.addNeighbor(node1);
    }

    @Override
    public void sendMessage(Message message) {

    }

    @Override
    public List<Node> getNodes() {
        return nodes;
    }
}