package com.kerosenelabs.billtracker.repository

import com.kerosenelabs.billtracker.entity.RecurringExpenseEventCreatorEntity
import com.kerosenelabs.billtracker.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface RecurringExpenseEventCreatorRepository : JpaRepository<RecurringExpenseEventCreatorEntity, UUID>{
    @Query("SELECT e FROM RecurringExpenseEventCreatorEntity e WHERE e.user = :user AND (:ids IS NULL OR e.id IN :ids)")
    fun findAllByUserAndOptionalIds(
        @Param("user") user: UserEntity,
        @Param("ids") ids: List<UUID>?
    ): List<RecurringExpenseEventCreatorEntity>}