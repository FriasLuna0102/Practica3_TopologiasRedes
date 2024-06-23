package org.example;

import org.example.Implementacion.Message;
import org.example.Implementacion.NetworkManager;
import org.example.Implementacion.NetworkTopology;
import org.example.Topologias.RingNetwork;
import org.example.Topologias.Star;

public class Main {
    public static void main(String[] args) {
        // Crear instancias de las topologías deseadas
        NetworkTopology ringTopology = new RingNetwork();
        NetworkTopology starTopology = new Star();

        // Crear el NetworkManager
        NetworkManager networkManager = new NetworkManager();

        // Agregar las topologías al NetworkManager
        networkManager.addTopology(ringTopology);
        networkManager.addTopology(starTopology);

        // Iniciar las redes con 5 nodos cada una
        networkManager.startNetwork(5);

        // Enviar un mensaje en la topología de anillo del nodo 1 al nodo 4
        Message ringMessage = new Message(1, 4, "Hola, estoy saludando desde nodo 1 al nodo 4 en la topología de anillo.");
        networkManager.sendMessage(ringMessage);

        // Enviar un mensaje en la topología de estrella del nodo 2 al nodo 3
        Message starMessage = new Message(2, 3, "Hola, estoy saludando desde nodo 2 al nodo 3 en la topología de estrella.");
        networkManager.sendMessage(starMessage);

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
