package org.example;

import org.example.Implementacion.Message;
import org.example.Implementacion.NetworkManager;
import org.example.Implementacion.NetworkTopology;
import org.example.Topologias.Bus;
import org.example.Topologias.Mesh;
import org.example.Topologias.RingNetwork;
import org.example.Topologias.Star;
import org.example.Topologias.FullyConnected;
import org.example.Topologias.Tree;
import org.example.Topologias.Switched;

public class Main {
    public static void main(String[] args) {
        // Crear instancias de las topologías deseadas
        NetworkTopology ringTopology = new RingNetwork();
        NetworkTopology starTopology = new Star();
        NetworkTopology busTopology = new Bus();
        NetworkTopology meshTopology = new Mesh();
        NetworkTopology fullyConnectedTopology = new FullyConnected();
        NetworkTopology treeTopology = new Tree();
        NetworkTopology switchedTopology = new Switched();

        // Crear el NetworkManager
        NetworkManager networkManager = new NetworkManager();

        // Agregar las topologías al NetworkManager
        networkManager.addTopology(ringTopology);
        networkManager.addTopology(starTopology);
        networkManager.addTopology(busTopology);
        networkManager.addTopology(meshTopology);
        networkManager.addTopology(fullyConnectedTopology);
        networkManager.addTopology(treeTopology);
        networkManager.addTopology(switchedTopology);

        // Iniciar las redes con 5 nodos cada una
        networkManager.startNetwork(5);

        // Enviar un mensaje en la topología de anillo del nodo 1 al nodo 4
        Message ringMessage = new Message(1, 4, "Hola, estoy saludando desde nodo 1 al nodo 4 en la topología de anillo.");
        networkManager.sendMessage(ringMessage);

        // Enviar un mensaje en la topología de estrella del nodo 2 al nodo 3
        Message starMessage = new Message(2, 3, "Hola, estoy saludando desde nodo 2 al nodo 3 en la topología de estrella.");
        networkManager.sendMessage(starMessage);

        // Enviar un mensaje en la topología de bus del nodo 0 al nodo 3
        Message busMessage = new Message(0, 3, "Hola, estoy saludando desde nodo 0 al nodo 3 en la topología de bus.");
        networkManager.sendMessage(busMessage);

        // Enviar un mensaje en la topología de malla del nodo 0 al nodo 4
        Message meshMessage = new Message(0, 4, "Hola, estoy saludando desde nodo 0 al nodo 4 en la topología de malla.");
        networkManager.sendMessage(meshMessage);

        // Enviar un mensaje en la topología completamente conectada del nodo 0 al nodo 3
        Message fullyConnectedMessage = new Message(1, 4, "Hola, estoy saludando desde nodo 0 al nodo 3 en la topología fully connected.");
        networkManager.sendMessage(fullyConnectedMessage);

        // Enviar un mensaje en la topología de árbol del nodo 0 al nodo 4
        Message treeMessage = new Message(0, 4, "Hola, estoy saludando desde nodo 0 al nodo 4 en la topología de árbol.");
        networkManager.sendMessage(treeMessage);

        // Enviar un mensaje en la topología de conmutador del nodo 0 al nodo 3
        Message switchedMessage = new Message(3, 0, "Hola, estoy saludando desde nodo 0 al nodo 3 en la topología de switched.");
        networkManager.sendMessage(switchedMessage);

        // Esperar un tiempo para que los mensajes se procesen
        try {
            Thread.sleep(5000); // Espera de 5 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Apagar la red
        networkManager.shutdownNetwork();
    }
}
