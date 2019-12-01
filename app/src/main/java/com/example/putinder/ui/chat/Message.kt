package com.example.putinder.ui.chat

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Message(val messageId: Int, val messageText: String, val sentDate: Date, val isUser: Boolean): Parcelable