package com.sivalabs.chitchat.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq")
    var id: Long? = null,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false, unique = true)
    var email: String,
    @Column(nullable = false)
    var password: String,
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var role: Role,
    @Column(name = "created_at")
    var createdAt: LocalDateTime,
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null,
)
