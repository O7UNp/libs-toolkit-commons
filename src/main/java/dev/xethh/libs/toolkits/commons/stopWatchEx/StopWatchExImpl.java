package dev.xethh.libs.toolkits.commons.stopWatchEx;

import org.apache.commons.lang3.time.StopWatch;

public class StopWatchExImpl implements StopWatchEx{
    protected StopWatch stopWatch = new StopWatch();

    @Override
    public StopWatchEx start() {
        stopWatch.start();
        return this;
    }

    @Override
    public StopWatchEx stop() {
        stopWatch.stop();
        return this;
    }

    @Override
    public StopWatchEx reset() {
        stopWatch.reset();
        return this;
    }

    @Override
    public Long nano() {
        return stopWatch.getNanoTime();
    }

    @Override
    public Long miliSecond() {
        return stopWatch.getTime();
    }

}
