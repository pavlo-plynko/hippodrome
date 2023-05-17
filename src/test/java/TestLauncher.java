import org.junit.platform.engine.DiscoverySelector;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Collections;

public class TestLauncher {
    public static void main(String[] args) {
        Launcher testLauncher = LauncherFactory.create();
        SummaryGeneratingListener listener = new SummaryGeneratingListener();


        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
               .request()
               .selectors(DiscoverySelectors.selectClass(HorseTest.class))
                .selectors(DiscoverySelectors.selectClass(HippodromeTest.class))
               .selectors(DiscoverySelectors.selectClass(MainTest.class))
                .build();

        testLauncher.execute(request, listener );
        try (PrintWriter printWriter = new PrintWriter(System.out)) {
            listener.getSummary().printTo(printWriter);
        }

    }
}
