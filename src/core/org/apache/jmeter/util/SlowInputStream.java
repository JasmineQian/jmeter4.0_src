package org.apache.jmeter.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * InputStream wrapper to emulate a slow device, e.g. modem
 *
 */
public class SlowInputStream extends FilterInputStream {

    private final CPSPauser pauser;

    /**
     * Wraps the input stream to emulate a slow device
     * @param in input stream
     * @param cps characters per second to emulate
     */
    public SlowInputStream(InputStream in, int cps) {
        super(in);
        pauser = new CPSPauser(cps);
    }

    @Override
    public int read() throws IOException {
        pauser.pause(1);
        return in.read();
    }

    // Also handles read(byte[])
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        pauser.pause(len);
        return in.read(b, off, len);
    }

}
