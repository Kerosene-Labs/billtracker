package com.kerosenelabs.billtracker.repository

import com.kerosenelabs.billtracker.entity.RecurringExpenseEventCreatorEntity
import com.kerosenelabs.billtracker.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface RecurringExpenseEventCreatorRepository : JpaRepository<RecurringExpenseEventCreatorEntity, UUID> {
    /**
     * Queries for Recurring Expense Event Creators that are for the given user, and optionally, find by IDs.
     * This method also filters out hidden superseded records.
     */
    @Query("SELECT e FROM RecurringExpenseEventCreatorEntity e WHERE e.user = :user AND (:ids IS NULL OR e.id IN :ids) AND e.hidden = false AND e.successor IS NULL")
    fun findAllByUserAndOptionalIds(
        @Param("user") user: UserEntity, @Param("ids") ids: List<UUID>?
    ): List<RecurringExpenseEventCreatorEntity>
}