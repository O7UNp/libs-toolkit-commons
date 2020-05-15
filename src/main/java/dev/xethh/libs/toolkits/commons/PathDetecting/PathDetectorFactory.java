package dev.xethh.libs.toolkits.commons.PathDetecting;

public class PathDetectorFactory {
    public static PathDetector get(String path){
        return new PathDetector.PathDetectorImpl(path);
    }
}
