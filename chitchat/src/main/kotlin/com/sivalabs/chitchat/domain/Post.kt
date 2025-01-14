package com.sivalabs.chitchat.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "posts")
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_id_generator")
    @SequenceGenerator(name = "post_id_generator", sequenceName = "post_id_seq")
    var id: Long? = null,
    @Column(nullable = false, unique = true)
    var uid: String,
    @Column(nullable = false)
    var content: String,
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    var createdBy: User,
    @Column(name = "created_at")
    var createdAt: LocalDateTime,
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null,
)
