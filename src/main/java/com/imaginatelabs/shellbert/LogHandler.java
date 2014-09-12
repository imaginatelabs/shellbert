package com.imaginatelabs.shellbert;

import org.apache.commons.exec.LogOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
