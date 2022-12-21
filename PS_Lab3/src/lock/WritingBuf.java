package lock;

public interface WritingBuf<T> {
    boolean writeInBuf(T newObject);
}
