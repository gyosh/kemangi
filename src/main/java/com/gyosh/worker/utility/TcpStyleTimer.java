package com.gyosh.worker.utility;

import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class TcpStyleTimer {
    private static final int DEFAULT_WAIT_MILIS = 20;
    private static final Logger logger = Logger.getLogger(TcpStyleTimer.class);
    private int waitMilis;

    public void init() {
        init(DEFAULT_WAIT_MILIS);
    }

    public void init(int waitMilis) {
        this.waitMilis = waitMilis;
    }

    public void blockingWait() {
        try {
            TimeUnit.MILLISECONDS.sleep(waitMilis);
        } catch (InterruptedException e) {
            logger.info(e.getMessage());
        }
        waitMilis *= 2;
    }

    public int getWaitMilis() {
        return waitMilis;
    }
}
