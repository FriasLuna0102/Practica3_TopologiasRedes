package org.example.Implementacion;

public class Message {
    private int sourceId;
    private int destinationId;
    private String content;

    public Message(int sourceId, int destinationId, String content) {
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.content = content;
    }

    // Getters
    public int getSourceId() {
        return sourceId;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sourceId=" + sourceId +
                ", destinationId=" + destinationId +
                ", content='" + content + '\'' +
                '}';
    }
}