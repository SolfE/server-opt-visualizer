package com.example.serveroptvisualizer.concert

import org.springframework.data.jpa.repository.JpaRepository

interface ConcertRepository : JpaRepository<Concert, Long> {
  fun findAllByOrderByIdAsc(): List<Concert>
}
