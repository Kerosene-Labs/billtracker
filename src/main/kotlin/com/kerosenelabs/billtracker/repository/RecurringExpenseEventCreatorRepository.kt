package com.kerosenelabs.billtracker.repository

import com.kerosenelabs.billtracker.entity.RecurringExpenseEventCreatorEntity
import com.kerosenelabs.billtracker.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RecurringExpenseEventCreatorRepository : JpaRepository<RecurringExpenseEventCreatorEntity, UUID>{
    fun findAllByUser(user: UserEntity): List<RecurringExpenseEventCreatorEntity>
}