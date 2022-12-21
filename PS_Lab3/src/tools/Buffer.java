package tools;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer<T> {
    private T message = null;
    private final Lock RWLock = new ReentrantLock();
    public boolean writeInBuffer(T newObject) {
        boolean writingSuccess = false;
        try {
            if (RWLock.tryLock(10, TimeUnit.SECONDS)) {
                if (message != null) throw new Exception("Message is full");
                else {
                    message = newObject;
                    writingSuccess = true;
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            RWLock.unlock();
        }
        return writingSuccess;
    }
    public T readFromBuffer() {
        T messageObject = null;
        try {
            if (RWLock.tryLock(10, TimeUnit.SECONDS)) {
                if (message == null) throw new Exception("Message is empty");
                else {
                    messageObject = message;
                    message = null;
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            RWLock.unlock();
        }
        return messageObject;
    }
}
