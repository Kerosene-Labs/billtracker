package com.kerosenelabs.billtracker.repository

import com.kerosenelabs.billtracker.entity.RecurringExpenseEventCreatorEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RecurringExpenseEventCreatorRepository : JpaRepository<RecurringExpenseEventCreatorEntity, UUID>
