package info.lazycompiler.uuid7_gen.rest.v1;

import info.lazycompiler.uuid7_gen.dto.UUID7;
import info.lazycompiler.uuid7_gen.exception.UUIDGenerationException;
import info.lazycompiler.uuid7_gen.service.TimedUUIDGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UUIDServer {

    private final TimedUUIDGenerator timedUUIDGenerator;

    @GetMapping("/uuid7")
    public ResponseEntity<UUID7> getTimedUUID() throws UUIDGenerationException {
        LOGGER.info("requested uuid7.");
        return ResponseEntity.ok(getUUID7());
    }
    
    @GetMapping("/ui/uuid7/generate")
    public ModelAndView generateNewUUID7(ModelAndView model) {
        LOGGER.info("requesting new UUID7 from UI/");
        var modelAndView = new ModelAndView("index");
        var uuid7 = getUUID7();
        modelAndView.addObject("uuid7", uuid7);
        return modelAndView;
    }

    private UUID7 getUUID7() throws UUIDGenerationException {
        try {
            return timedUUIDGenerator.get();
        }catch (Exception e) {
            LOGGER.error("failed to evaluate UUID ", e);
            throw new UUIDGenerationException(e);
        }
    }

}
