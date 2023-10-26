package com.ckyu.cleanarchitecture_sample_app.domain.model

import androidx.room.Entity

/**
 * Immutable model class for a Task.
 *
 * @param title title of the task
 * @param description description of task
 * @param isCompleted where or not this task is completed
 * @param id id of the task
 */
@Entity
data class Task(
    val title : String = "",
    val description : String = "",
    val isCompleted : Boolean = false,
    val timeStamp : Long,
    val id : String,
)