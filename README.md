# Practica3_TopologiasRedes.

Documentacion:

Para la elaboracion de esta practica se consideraron aspectos como cual iba hacer la estructura para gestionar
las diferentes topologias en cuestion. Una vez definida la estructura se iban a desarrollar las siguientes topologias:

-Bus Network
-Ring Network
-Network
-Mesh
-Star Network
-Hypercube Network
-Tree Network
-Fully Connected Network
-Switched Network

Para la gestion se utilizo la siguiente estructura:

-Clase NetworkTopology: Interfaz base que define los métodos comunes para todas las topologías de redes.
-Subclases para cada Topología de Red: Implementaciones específicas de cada topología de red, como Bus Network, Ring Network, etc.
-Clase Node: Representa un nodo en la red y contiene atributos como ID, vecinos, etc.
-Clase Message: Representa un mensaje que se envía entre nodos en la red.
-Clase NetworkManager:Gestiona la creación y ejecución de la red, así como la comunicación entre nodos.
-Clase Main: Punto de entrada principal del programa, donde se configuran y ejecutan las topologías de redes.

En nuestra clase de NetworkTopology es donde definimos la interfaz la cual implementaran todas las diferentes topologias.
Los metodos que contiene esta interfaz son los siguientes:

-void configureNetwork(int numberOfNodes);   //Este metodo recibe el numero de nodos, el cual se utilizara para configurar la distribucion de la red.
-void addNode(Node node);		    // Este es para agregar mas nodos a la red en caso de ser necesario.
-void connectNodes(Node node1, Node node2); //Este metodo es el que permite conectar los nodos entre si, por ello recibe 
-void sendMessage(Message message);
-List<Node> getNodes();

