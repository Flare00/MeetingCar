package fr.flareden.meetingcar.metier.entity.client;

public class MoyenCommunication {
    private int id;
    private String type;
    private String value;

    public MoyenCommunication(int id, String type, String value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    public MoyenCommunication(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
