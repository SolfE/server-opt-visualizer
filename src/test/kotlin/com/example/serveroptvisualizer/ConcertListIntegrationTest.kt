package com.example.serveroptvisualizer

import com.example.serveroptvisualizer.concert.Concert
import com.example.serveroptvisualizer.concert.ConcertRepository
import java.time.LocalDateTime
import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ConcertListIntegrationTest {

  @Autowired lateinit var mockMvc: MockMvc
  @Autowired lateinit var concertRepository: ConcertRepository

  @BeforeEach
  fun setUp() {
    concertRepository.deleteAll()
    concertRepository.save(
      Concert(
        title = "Test Concert",
        venue = "Test Venue",
        description = "Concert seeded for list integration testing.",
        createdAt = LocalDateTime.now(),
      )
    )
    concertRepository.save(
      Concert(
        title = "Second Concert",
        venue = "Second Venue",
        description = "Another concert to verify ordering.",
        createdAt = LocalDateTime.now().plusMinutes(1),
      )
    )
  }

  @Test
  fun getConcertsReturnsRenderedConcertListPage() {
    mockMvc
      .perform(get("/concerts"))
      .andExpect(status().isOk)
      .andExpect(view().name("concerts/list"))
      .andExpect(model().attributeExists("concerts"))
      .andExpect(content().string(containsString("Test Concert")))
      .andExpect(content().string(containsString("Second Concert")))
  }
}
