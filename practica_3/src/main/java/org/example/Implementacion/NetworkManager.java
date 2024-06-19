package org.example.Implementacion;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkManager {
    private final NetworkTopology networkTopology;
    private ExecutorService executorService;

    public NetworkManager(NetworkTopology networkTopology) {
        this.networkTopology = networkTopology;
    }

    public void startNetwork(int numberOfNodes) {
        networkTopology.configureNetwork(numberOfNodes);
        executorService = Executors.newFixedThreadPool(numberOfNodes);

        List<Node> nodes = networkTopology.getNodes();
        for (Node node : nodes) {
            executorService.execute(node);
        }
    }

    public void sendMessage(Message message) {
        Node destinationNode = getNodeById(message.getDestinationId());
        if (destinationNode != null) {
            destinationNode.sendMessage(message);
        } else {
            System.out.println("No se encontró nodo con id: " + message.getDestinationId());
        }
    }

    public void shutdownNetwork() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    private Node getNodeById(int id) {
        synchronized (networkTopology) {
            for (Node node : networkTopology.getNodes()) {
                if (node.getId() == id) {
                    return node;
                }
            }
        }
        return null;
    }
}
