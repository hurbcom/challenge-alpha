package com.edufelip.challengealpha.presentation.fragments.favorites

import com.edufelip.challengealpha.data.local.room.models.Favorite

interface OnDeleteClick {
    fun onDeleteClick(item: Favorite)
}