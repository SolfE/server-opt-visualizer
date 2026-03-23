package com.example.serveroptvisualizer.concert;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ConcertService {

  private final ConcertRepository concertRepository;

  public ConcertService(ConcertRepository concertRepository) {
    this.concertRepository = concertRepository;
  }

  public List<ConcertSummaryView> getConcerts() {
    return concertRepository.findAllByOrderByIdAsc().stream()
        .map(
            concert ->
                new ConcertSummaryView(
                    concert.getId(),
                    concert.getTitle(),
                    concert.getVenue(),
                    concert.getDescription()))
        .toList();
  }
}
