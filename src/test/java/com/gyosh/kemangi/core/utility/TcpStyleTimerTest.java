package com.gyosh.kemangi.core.utility;

import org.junit.Test;

import static org.junit.Assert.*;

public class TcpStyleTimerTest {
    @Test
    public void testConstruct() {
        TcpStyleTimer defaultTimer = new TcpStyleTimer();
        assertEquals(
                "Should have default wait time for empty constructor",
                TcpStyleTimer.DEFAULT_WAIT_MILIS,
                defaultTimer.getWaitMilis()
        );

        int customWaitTime = 121;
        TcpStyleTimer customTimer = new TcpStyleTimer(customWaitTime);
        assertEquals(
                "Should have defined wait time for constructor with wait time argument",
                customWaitTime,
                customTimer.getWaitMilis()
        );
    }

    @Test
    public void blockingWait() {
        TcpStyleTimer defaultTimer = new TcpStyleTimer();

        defaultTimer.blockingWait();
        assertEquals(
                "Should doubles wait time",
                2 * TcpStyleTimer.DEFAULT_WAIT_MILIS,
                defaultTimer.getCurrentWaitMilis()
        );

        defaultTimer.blockingWait();
        assertEquals(
                "Should doubles wait time successively",
                4 * TcpStyleTimer.DEFAULT_WAIT_MILIS,
                defaultTimer.getCurrentWaitMilis()
        );
    }

    @Test
    public void testInit() {
        TcpStyleTimer defaultTimer = new TcpStyleTimer();

        defaultTimer.blockingWait();

        defaultTimer.init();
        assertEquals(
                "Should returns wait time to initial wait time",
                TcpStyleTimer.DEFAULT_WAIT_MILIS,
                defaultTimer.getCurrentWaitMilis()
        );
    }
}