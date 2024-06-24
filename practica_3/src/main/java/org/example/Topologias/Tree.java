package org.example.Topologias;

import org.example.Implementacion.Message;
import org.example.Implementacion.NetworkTopology;
import org.example.Implementacion.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tree implements NetworkTopology {
    private List<Node> nodes;
    private ExecutorService executorService;

    @Override
    public void configureNetwork(int numberOfNodes) {
        nodes = new ArrayList<>();

        // Crear nodos
        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(new Node(i));
        }

        // Conectar nodos en forma de árbol binario
        for (int i = 0; i < numberOfNodes; i++) {
            int leftChildIndex = 2 * i + 1;
            int rightChildIndex = 2 * i + 2;

            if (leftChildIndex < numberOfNodes) {
                connectNodes(nodes.get(i), nodes.get(leftChildIndex));
            }

            if (rightChildIndex < numberOfNodes) {
                connectNodes(nodes.get(i), nodes.get(rightChildIndex));
            }
        }

        // Inicializar el ExecutorService con un número de hilos igual al número de nodos
        executorService = Executors.newFixedThreadPool(numberOfNodes);
        for (Node node : nodes) {
            executorService.execute(node);
        }
    }

    @Override
    public void addNode(Node node) {
        nodes.add(node);
        // No se implementa la adición de nodos en un árbol binario fijo
        executorService.execute(node);
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
            sourceNode.sendMessage(message);
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
