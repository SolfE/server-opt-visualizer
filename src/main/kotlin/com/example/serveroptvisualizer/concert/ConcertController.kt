package com.example.serveroptvisualizer.concert

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/concerts")
class ConcertController(private val concertService: ConcertService) {

  @GetMapping
  fun getConcerts(model: Model): String {
    model.addAttribute("concerts", concertService.getConcerts())
    return "concerts/list"
  }
}
