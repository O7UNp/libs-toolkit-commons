package dev.xethh.libs.toolkits.commons.stopWatchEx;

import java.util.function.Supplier;

public class StopWatchExFactory {
    public StopWatchExFactory(Supplier<StopWatchEx> stopWatchExSupplier){
        this.builder = stopWatchExSupplier;
    }

    private Supplier<StopWatchEx> builder;
    public StopWatchEx getNewOne(){
        return builder.get();
    }

    public static StopWatchExFactory get(Supplier<StopWatchEx> builder){
        return new StopWatchExFactory(builder);
    }

}
