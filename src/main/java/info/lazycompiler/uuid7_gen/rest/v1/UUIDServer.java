package info.lazycompiler.uuid7_gen.rest.v1;

import info.lazycompiler.uuid7_gen.dto.UUID7;
import info.lazycompiler.uuid7_gen.exception.UUIDGenerationException;
import info.lazycompiler.uuid7_gen.service.TimedUUIDGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UUIDServer {

    private final TimedUUIDGenerator timedUUIDGenerator;

    @GetMapping({"/", "/uuid7"})
    public ResponseEntity<UUID7> getTimedUUID() {
        LOGGER.info("requested uuid7");
        try {
            return ResponseEntity.ok(timedUUIDGenerator.get());
        }catch (Exception e) {
            LOGGER.error("failed to evaluate UUID ", e);
            throw new UUIDGenerationException(e);
        }
    }

}
