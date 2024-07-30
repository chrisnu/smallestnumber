package nl.west.chris.smallestnumber.controller;

import nl.west.chris.smallestnumber.service.SmallestNumberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api")
@RestController
public class SmallestNumberController {

    private final SmallestNumberService smallestNumberService;

    public SmallestNumberController(SmallestNumberService smallestNumberService) {
        this.smallestNumberService = smallestNumberService;
    }
    @GetMapping(path = "/smallest")
    public ResponseEntity<Integer> user(@RequestParam(name = "start") Integer start, @RequestParam(name = "end") Integer end) {
        return new ResponseEntity<>(smallestNumberService.getSmallestNumber(start, end), HttpStatus.OK);
    }
}
