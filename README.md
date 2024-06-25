# Practica3_TopologiasRedes.

Documentacion:

Para la elaboracion de esta practica se consideraron aspectos como la estructura para gestionar
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

## En nuestra clase de NetworkTopology es donde definimos la interfaz la cual implementaran todas las diferentes topologias.
### Los metodos que contiene esta interfaz son los siguientes:

-void configureNetwork(int numberOfNodes);   //Este metodo recibe el numero de nodos, el cual se utilizara para configurar la distribucion de la red.

-void addNode(Node node);		    // Este es para agregar mas nodos a la red en caso de ser necesario.

-void connectNodes(Node node1, Node node2); //Este metodo es el que permite conectar los nodos entre si, por ello recibe 

-void sendMessage(Message message);         //En este metodo es que se gestionara el envio de los mensajes.

-List<Node> getNodes();                   //Este metodo retorna la lista de los nodos.


## En nuestra clase de Menssage contamos con 3 atributos, los cuales son el sourceId (quien envia el msj), el destination (hacia quien se envia), y el content (cuerpo del mensaje).
## Luego simplemtente se tiene el constructor, y ademas, un metodo para convertir el objeto de mensaje en un String con el siguiente formato:

"Message{" +
                "sourceId=" + sourceId +
                ", destinationId=" + destinationId +
                ", content='" + content + '\'' +
                '}';
                
                
## Posteriormente, tenemos la clase NetworkManager, en la cual se implementan los metodos como addTopology, 
## startNetwork, sendMenssage, shutdownNetwork y getNodeById.

## Esta sera nuestra clase encargada de la gestion de las topologias, siempre que se cree una instancia de una topologia debe 
## ser pasada al NetworkManager para poder tener acceso a los metodos.

## En el metodo de startNetwork es que inicializamos el ExecutorServices para hacer uso de la concurrencia, luego
## recorremos la lista de nodo, para pasarlo a executorServices.

## En el metodo sendMenssage se recibe el mensaje, se verifica que el nodo de destino sea diferente de null, para asegurarnos
## de que el msj no va hacia un destino sin definir.

## Finalmente, en la clase de Node, esta implementa de Runnable para conseguir el paralelismo, cuenta con 3 atributos, el id, la lista de vecinos (nodos), y el
## messageQueue que es un BlockingQueue.

## Ademas, cuenta con el metodo run, el cual es que vuelve esto paralelo y concurrente. Este metodo es el que se encarga de recorrer los hilos, siempre y cuando no sean interrumpidos 
## hasta recibir todos los msj y enviarlos.

## Ya luego simplemente se hace la gestion de recibir los mensajes, y enviarlos.


## Para las topologias simplemente se creo una clase para cada una de las implementaciones,
## cada implementacion es similar, usan el metodo configureNetwork, donde se gestiona
## el como se implementara la Network, aqui utilizamos concurrencia y semafaros en ocasiones 
## para controlar los envios de msj de los nodos.

## Tambien esta el uso del metodo connectNodes para conectar dos nodos, tambien sendMenssage, etc.



### Deberiamos saber o ser conciente que el uso de estas topologias dependera para que tipo de problemas necesitemos resolver,
### no existe topologia que se adapte a todos los escenarios posibles. Aunque una de las mas utilizadas es la topologia estrella.

## Este programa puede ser util para el manejo de redes, siempre y cuando se tengan en cuenta las necesidades.



# Ejemplos:

Aqui en el main es como instanciamos las topologias:

        // Crear instancias de las topologías deseadas
        NetworkTopology ringTopology = new RingNetwork();
        
        NetworkTopology starTopology = new Star();
        
        NetworkTopology busTopology = new Bus();
        
        NetworkTopology meshTopology = new Mesh();
        
        NetworkTopology fullyConnectedTopology = new FullyConnected();
        
        NetworkTopology treeTopology = new Tree();
        
        NetworkTopology switchedTopology = new Switched();
        
        NetworkTopology hypercubeTopology = new Hypercube();
        
        
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
        
        
        
         // Iniciar las redes con 8 nodos cada una
        networkManager.startNetwork(8);

        networkManager.addTopology(hypercubeTopology);
        
        // Enviar un mensaje en la topología de anillo del nodo 1 al nodo 4
        Message ringMessage = new Message(1, 4, "Hola, estoy saludando desde nodo 1 al nodo 4 en la topología de anillo.");
        networkManager.sendMessage(ringMessage);

        // Enviar un mensaje en la topología de estrella del nodo 2 al nodo 3
        Message starMessage = new Message(2, 3, "Hola, estoy saludando desde nodo 2 al nodo 3 en la topología de estrella.");
        networkManager.sendMessage(starMessage);
        
        
## Esta es la salida:


Nodo 4 recibió mensaje: Message{sourceId=1, destinationId=4, content='Hola, estoy saludando desde nodo 1 al nodo 4 en la topología de anillo.'}. Proveniente del nodo: 1
Nodo 3 recibió mensaje: Message{sourceId=2, destinationId=3, content='Hola, estoy saludando desde nodo 2 al nodo 3 en la topología de estrella.'}. Proveniente del nodo: 2

