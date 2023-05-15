package assistant.interfaces;

public interface CommandReceiver<T> {
    void invoke(T input);
}
