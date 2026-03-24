package com.example.serveroptvisualizer.concert

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ConcertService(private val concertRepository: ConcertRepository) {

  fun getConcerts(): List<ConcertSummaryView> =
    concertRepository.findAllByOrderByIdAsc().map { concert ->
      ConcertSummaryView(
        id = concert.id,
        title = concert.title,
        venue = concert.venue,
        description = concert.description,
      )
    }
}
