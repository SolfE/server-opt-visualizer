package com.example.serveroptvisualizer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.serveroptvisualizer.concert.Concert;
import com.example.serveroptvisualizer.concert.ConcertRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ConcertListIntegrationTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private ConcertRepository concertRepository;

  @BeforeEach
  void setUp() {
    concertRepository.deleteAll();
    concertRepository.save(
        new Concert(
            "Test Concert",
            "Test Venue",
            "Concert seeded for list integration testing.",
            LocalDateTime.now()));
    concertRepository.save(
        new Concert(
            "Second Concert",
            "Second Venue",
            "Another concert to verify ordering.",
            LocalDateTime.now().plusMinutes(1)));
  }

  @Test
  void getConcertsReturnsRenderedConcertListPage() throws Exception {
    mockMvc
        .perform(get("/concerts"))
        .andExpect(status().isOk())
        .andExpect(view().name("concerts/list"))
        .andExpect(model().attributeExists("concerts"))
        .andExpect(content().string(org.hamcrest.Matchers.containsString("Test Concert")))
        .andExpect(content().string(org.hamcrest.Matchers.containsString("Second Concert")));
  }
}
