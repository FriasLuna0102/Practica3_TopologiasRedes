package org.example.Topologias;

import org.example.Implementacion.Message;
import org.example.Implementacion.NetworkTopology;
import org.example.Implementacion.Node;

import java.util.ArrayList;
import java.util.List;

public class Star implements NetworkTopology {
    private List<Node> nodes;
    private Node hub;

    @Override
    public void configureNetwork(int numberOfNodes) {
        nodes = new ArrayList<>();
        hub = new Node(0); // Nodo central
        nodes.add(hub);

        for (int i = 1; i < numberOfNodes; i++) {
            Node node = new Node(i);
            nodes.add(node);
            connectNodes(hub, node); // Conectar todos los nodos al hub
        }
    }

    @Override
    public void addNode(Node node) {
        nodes.add(node);
        connectNodes(hub, node);
    }

    @Override
    public void connectNodes(Node node1, Node node2) {
        node1.addNeighbor(node2);
        node2.addNeighbor(node1);
    }

    @Override
    public void sendMessage(Message message) {
        Node sourceNode = getNodeById(message.getSourceId());
        if (sourceNode != null) {
            if (sourceNode.equals(hub)) {
                hub.sendMessage(message);
            } else {
                // Enviar mensaje al hub para reenviarlo al destino
                hub.sendMessage(message);
            }
        } else {
            System.out.println("No se encontró nodo con id: " + message.getSourceId());
        }
    }

    @Override
    public List<Node> getNodes() {
        return nodes;
    }

    private Node getNodeById(int id) {
        for (Node node : nodes) {
            if (node.getId() == id) {
                return node;
            }
        }
        return null;
    }
}
