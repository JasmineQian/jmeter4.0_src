package org.apache.jmeter.swing;

import java.awt.Rectangle;

import javax.swing.JTextPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements an HTML Pane with local hyperlinking enabled.
 */
public class HtmlPane extends JTextPane {
    private static final long serialVersionUID = 241L;

    private static final Logger log = LoggerFactory.getLogger(HtmlPane.class);

    public HtmlPane() {
        this.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    String ref = e.getURL().getRef();
                    if (ref != null) {
                        log.debug("reference to scroll to = '{}'", ref);
                        if (ref.length() > 0) {
                            scrollToReference(ref);
                        } else { // href="#"
                            scrollRectToVisible(new Rectangle(1,1,1,1));
                        }
                    }
                }
            }
        });
    }
}
