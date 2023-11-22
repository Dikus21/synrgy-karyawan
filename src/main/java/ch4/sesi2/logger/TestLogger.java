package ch4.sesi2.logger;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TestLogger {
    @Test
    public void testLogger() {
        log.trace("Test, trace");
        log.debug("Test, debug");
        log.info("Test, info");
        log.warn("Test, warn");
        //Buat di dalam try catch
        log.error("Test, error");
    }
}
