package com.kerosenelabs.billtracker.entity

import com.kerosenelabs.billtracker.model.OAuth2Provider
import jakarta.persistence.*
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "user", schema = "public")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @Column(nullable = false, unique = true)
    var emailAddress: String = "",

    // oauth
    @Column(nullable = false)
    var provider: OAuth2Provider = OAuth2Provider.GOOGLE,
    @Column(nullable = false, unique = true)
    var sub: String = "",

    // introductions
    var firstName: String? = null,
    var lastName: String? = null,
    var birthday: LocalDate? = null,
)