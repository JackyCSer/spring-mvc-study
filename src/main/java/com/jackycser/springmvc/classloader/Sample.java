package com.jackycser.springmvc.classloader;

/**
 * @author Jacky Zhang
 * @since 13/01/2017
 */
public class Sample {
    private Sample instance;

    public void setSample(Object instance) {
        this.instance = (Sample) instance;
    }
}
