package com.example.serveroptvisualizer.concert;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/concerts")
public class ConcertController {

  private final ConcertService concertService;

  public ConcertController(ConcertService concertService) {
    this.concertService = concertService;
  }

  @GetMapping
  public String getConcerts(Model model) {
    model.addAttribute("concerts", concertService.getConcerts());
    return "concerts/list";
  }
}
