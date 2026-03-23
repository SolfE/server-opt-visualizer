package com.example.serveroptvisualizer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
  void getConcertsReturnsConcertList() throws Exception {
    mockMvc
        .perform(get("/api/concerts"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].title").value("Test Concert"))
        .andExpect(jsonPath("$[1].title").value("Second Concert"));
  }
}
