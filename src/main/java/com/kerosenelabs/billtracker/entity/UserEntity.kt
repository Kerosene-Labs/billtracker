package com.kerosenelabs.billtracker.entity

import com.kerosenelabs.billtracker.model.OAuth2Provider
import jakarta.persistence.*
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate
import java.util.*

@Table(name = "user", schema = "public")
@Entity
class UserEntity(
    @field:Column(
        nullable = false, unique = true
    ) private var emailAddress: String, @field:Column(
        nullable = false, unique = true
    ) private var sub: String, private var provider: OAuth2Provider, firstName: String?, lastName: String?
) {
    constructor() : this("", "", OAuth2Provider.GOOGLE, null, null)

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private val id: UUID? = null

    private var firstName: String? = null
    private var lastName: String? = null
    private val birthday: LocalDate? = null
}