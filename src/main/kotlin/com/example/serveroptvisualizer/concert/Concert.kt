package com.example.serveroptvisualizer.concert

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "concert")
class Concert(
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
  @Column(nullable = false) var title: String = "",
  @Column(nullable = false) var venue: String = "",
  @Column(columnDefinition = "text") var description: String? = null,
  @Column(nullable = false) var createdAt: LocalDateTime = LocalDateTime.now(),
)
