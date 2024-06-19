package org.example;

import org.example.Implementacion.Message;
import org.example.Implementacion.NetworkManager;
import org.example.Implementacion.NetworkTopology;
import org.example.Topologias.RingNetwork;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de la topología deseada (por ejemplo, RingNetwork)
        NetworkTopology topology = new RingNetwork();

        // Crear el NetworkManager
        NetworkManager networkManager = new NetworkManager(topology);

        // Iniciar la red con 5 nodos
        networkManager.startNetwork(5);

        // Enviar un mensaje del nodo 0 al nodo 3
        Message message = new Message(0, 4, "Hola, estoy saludando.");
        networkManager.sendMessage(message);


        networkManager.shutdownNetwork();
    }
}