package org.example.Implementacion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkManager {
    private final List<NetworkTopology> networkTopologies;
    private ExecutorService executorService;

    public NetworkManager() {
        this.networkTopologies = new ArrayList<>();
    }

    public void addTopology(NetworkTopology networkTopology) {
        networkTopologies.add(networkTopology);
    }

    public void startNetwork(int numberOfNodes) {
        executorService = Executors.newFixedThreadPool(numberOfNodes * networkTopologies.size());

        for (NetworkTopology networkTopology : networkTopologies) {
            networkTopology.configureNetwork(numberOfNodes);
            List<Node> nodes = networkTopology.getNodes();
            for (Node node : nodes) {
                executorService.execute(node);
            }
        }
    }

    public void sendMessage(Message message) {
        for (NetworkTopology networkTopology : networkTopologies) {
            Node destinationNode = getNodeById(networkTopology, message.getDestinationId());
            if (destinationNode != null) {
                destinationNode.sendMessage(message);
                return;
            }
        }
        System.out.println("No se encontró nodo con id: " + message.getDestinationId());
    }

    public void shutdownNetwork() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    private Node getNodeById(NetworkTopology networkTopology, int id) {
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
