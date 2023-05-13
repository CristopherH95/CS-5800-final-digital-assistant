package assistant.interfaces;

public interface CommandReceiver {
    void setParameter(String name, String value);
    void execute();
}
