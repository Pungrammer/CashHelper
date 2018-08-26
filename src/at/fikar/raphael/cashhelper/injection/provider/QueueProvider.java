package at.fikar.raphael.cashhelper.injection.provider;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueProvider {

    public <T> BlockingQueue<T> get() {
        return new LinkedBlockingQueue<>();
    }
}
