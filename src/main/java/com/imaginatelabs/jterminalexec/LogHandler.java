package com.imaginatelabs.jterminalexec;

import org.apache.commons.exec.LogOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;


public class LogHandler extends LogOutputStream {

    private Logger log = LoggerFactory.getLogger("foo");

    public LogHandler(Logger log) {
        super();
        this.log = log;
    }

    @Override
    protected void processLine(String s, int i) {

    }
}
