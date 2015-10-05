package com.gyosh.kemangi.core.utility;

import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class TcpStyleTimer {
    public static final int DEFAULT_WAIT_MILIS = 20;
    private static final Logger logger = Logger.getLogger(TcpStyleTimer.class);
    private int waitMilis;
    private int currentWaitMilis;

    public TcpStyleTimer() {
        this(DEFAULT_WAIT_MILIS);
    }

    public TcpStyleTimer(int waitMilis) {
        this.waitMilis = waitMilis;
        currentWaitMilis = waitMilis;
    }

    public void init() {
        currentWaitMilis = waitMilis;
    }

    public void blockingWait() {
        try {
            TimeUnit.MILLISECONDS.sleep(currentWaitMilis);
        } catch (InterruptedException e) {
            logger.info(e.getMessage());
        }
        currentWaitMilis *= 2;
    }

    public int getCurrentWaitMilis() {
        return currentWaitMilis;
    }

    public int getWaitMilis() {
        return waitMilis;
    }
}
