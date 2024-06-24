package org.example.Topologias;

import org.example.Implementacion.Message;
import org.example.Implementacion.NetworkTopology;
import org.example.Implementacion.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Bus implements NetworkTopology {
    private List<Node> nodes;
    private Semaphore busSemaphore;

    @Override
    public void configureNetwork(int numberOfNodes) {
        nodes = new ArrayList<>();
        busSemaphore = new Semaphore(1); // Solo un nodo puede transmitir a la vez

        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(new Node(i));
        }

        // Conectar todos los nodos entre sí (aunque no es necesario para bus, lo hacemos por consistencia)
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                if (i != j) {
                    connectNodes(nodes.get(i), nodes.get(j));
                }
            }
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
        try {
            busSemaphore.acquire();
            Node sourceNode = getNodeById(message.getSourceId());
            if (sourceNode != null) {
                for (Node node : nodes) {
                    if (node.getId() != message.getSourceId()) {
                        node.sendMessage(message);
                    }
                }
            } else {
                System.out.println("No se encontró nodo con id: " + message.getSourceId());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            busSemaphore.release();
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
