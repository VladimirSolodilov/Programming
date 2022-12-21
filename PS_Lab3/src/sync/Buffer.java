package sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer<T> {
    private T buffer = null;
    private final Lock RWLock = new ReentrantLock();
    public boolean writeInBuffer(T newObject) {
        boolean writingSuccess = false;
        try {
            if (RWLock.tryLock(10, TimeUnit.MILLISECONDS)) {
                if (buffer != null) throw new Exception("Buffer is full");
                else {
                    buffer = newObject;
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
        T bufferObject = null;
        try {
            if (RWLock.tryLock(10, TimeUnit.SECONDS)) {
                if (buffer == null) throw new Exception("Buffer is empty");
                else {
                    bufferObject = buffer;
                    buffer = null;
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            RWLock.unlock();
        }
        return bufferObject;
    }
}
