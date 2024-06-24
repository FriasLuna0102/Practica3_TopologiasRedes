package org.example.Topologias;

import org.example.Implementacion.Message;
import org.example.Implementacion.NetworkTopology;
import org.example.Implementacion.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Switched implements NetworkTopology {
    private List<Node> nodes;
    private ExecutorService executorService;

    @Override
    public void configureNetwork(int numberOfNodes) {
        nodes = new ArrayList<>();

        // Crear nodos
        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(new Node(i));
        }

        // Conectar nodos a través de un switch central
        Node switchNode = new Node(numberOfNodes); // Nodo switch
        for (Node node : nodes) {
            connectNodes(switchNode, node);
        }
        nodes.add(switchNode);

        // Inicializar el ExecutorService con un número de hilos igual al número de nodos más el switch
        executorService = Executors.newFixedThreadPool(numberOfNodes + 1);
        for (Node node : nodes) {
            executorService.execute(node);
        }
    }

    @Override
    public void addNode(Node node) {
        nodes.add(node);
        // Conectar el nuevo nodo al switch central
        Node switchNode = nodes.get(nodes.size() - 1);
        connectNodes(switchNode, node);
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
            // Enviar el mensaje al switch central para reenviarlo al nodo destino
            Node switchNode = nodes.get(nodes.size() - 1);
            switchNode.sendMessage(message);
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
