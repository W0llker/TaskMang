package api.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

public interface ControllerApi<REQ,RESP> {
    @PostMapping("save")
    RESP save(@RequestBody @Valid REQ req);
    @GetMapping("id")
    RESP findById(@RequestParam Long id);
    @DeleteMapping("delete")
    void delete(@RequestParam Long id);
    @PutMapping("update")
    RESP update(@RequestBody @Valid REQ req);
}
