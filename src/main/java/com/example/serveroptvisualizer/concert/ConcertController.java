package com.example.serveroptvisualizer.concert;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/concerts")
public class ConcertController {

  private final ConcertService concertService;

  public ConcertController(ConcertService concertService) {
    this.concertService = concertService;
  }

  @GetMapping
  public List<ConcertSummaryResponse> getConcerts() {
    return concertService.getConcerts();
  }
}
