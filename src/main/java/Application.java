import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.view.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Application {

    public static void main(String[] args) {
        // TODO: 2020-06-04:serhii.lekariev: insert license
        String license = "insert-your-license";
        EngineOptions options = EngineOptions.newBuilder(RenderingMode.HARDWARE_ACCELERATED)
                .licenseKey(license)
                .remoteDebuggingPort(4567)
                .build();
        Engine engine = Engine.newInstance(options);
        Browser browser = engine.newBrowser();

        Dimension screenSize = Toolkit.getDefaultToolkit()
                .getScreenSize();
        BrowserView view = BrowserView.newInstance(browser);
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("YouTube");
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    browser.close();
                }
            });
            frame.setSize(screenSize);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.add(view, BorderLayout.CENTER);
            frame.setVisible(true);
        });
        browser.navigation()
                .loadUrl("youtube.com");
    }
}
