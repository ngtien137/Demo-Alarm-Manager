package com.lhd.alarm

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

const val EXTRA_CHANNEL = "extra.channel"
const val EXTRA_INFO = "extra.info"
const val EXTRA_TIME = "extra.time"
const val EXTRA_VIEW = "extra.view"
const val EXTRA_TITLE = "extra.title"
const val EXTRA_MESSAGE = "extra.message"
const val EXTRA_NOTI_ID = "extra.notify.id"

@Parcelize
data class Channel(
    var channelId: String = "", var channelName: String = ""
    , var channelDescription: String = ""
) : Parcelable {
    override fun toString(): String {
        return "Channel(channelId='$channelId', channelName='$channelName', channelDescription='$channelDescription')"
    }
}

@Parcelize
data class Info(
    var smallIcon: Int = R.drawable.ic_action_name,
    var title: String = "",
    var message: String = ""
) : Parcelable {

    override fun toString(): String {
        return "Info(smallIcon=$smallIcon, title='$title', message='$message')"
    }
}