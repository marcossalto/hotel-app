package com.architectcoders.hotelapp.ui

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.architectcoders.hotelapp.R
import com.architectcoders.hotelapp.model.HotelDetailSerializer

class HotelDetailInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    fun setHotel(hotelDetailSerializaer: HotelDetailSerializer) = with(hotelDetailSerializaer) {
        text = buildSpannedString {
            data.body.run {
                bold { appendLine(context.getString(R.string.amenities)) }
                for (i in amenities.indices) {
                    bold { appendLine("\t" + amenities[i].heading) }
                    for (j in amenities[i].listItems.indices) {
                        bold { appendLine("\t\t" + amenities[i].listItems[j].heading) }
                        for (element in amenities[i].listItems[j].listItems) {
                            appendLine("\t\t\t" + element)
                        }
                    }
                }

                bold { appendLine(context.getString(R.string.at_a_glance)) }
                bold { appendLine("\t" + context.getString(R.string.arriving_leaving)) }
                for (element in atAGlance.keyFacts.arrivingLeaving) {
                    appendLine("\t\t" + element)
                }

                bold { appendLine("\t" + context.getString(R.string.hotel_size)) }
                for (element in atAGlance.keyFacts.hotelSize) {
                    appendLine("\t\t" + element)
                }

                bold { appendLine("\t" + context.getString(R.string.required_at_checkin)) }
                for (element in atAGlance.keyFacts.requiredAtCheckIn) {
                    appendLine("\t\t" + element)
                }

                bold { appendLine("\t" + context.getString(R.string.special_checkin_instructions)) }
                for (element in atAGlance.keyFacts.specialCheckInInstructions) {
                    appendLine("\t\t" + element)
                }

                bold { appendLine("\t" + context.getString(R.string.transport_and_others)) }
                bold { appendLine("\t" + context.getString(R.string.travelling_or_internet)) }
                bold { appendLine(context.getString(R.string.guest_reviews)) }
                bold { appendLine(context.getString(R.string.hotel_badge)) }
                bold { appendLine(context.getString(R.string.hotel_welcome_rewards)) }
                bold { appendLine(context.getString(R.string.hygiene_and_cleanliness)) }
                bold { appendLine(context.getString(R.string.miscellaneous)) }
                bold { appendLine(context.getString(R.string.overview)) }
                bold { appendLine(context.getString(R.string.page_info)) }
                bold { appendLine(context.getString(R.string.php_header)) }
                bold { appendLine(context.getString(R.string.property_description)) }
                bold { appendLine(context.getString(R.string.small_print)) }
                bold { appendLine(context.getString(R.string.special_features)) }
            }

            bold { appendLine(context.getString(R.string.neighborhood)) }
            neighborhood.run {
                bold { appendLine("\t" + neighborhoodName) }
            }

            bold { appendLine(context.getString(R.string.transportation)) }
            transportation.run {
                for (i in transportLocations.indices) {
                    bold { appendLine("\t" + transportLocations[i].category) }
                    for (element in transportLocations[i].locations) {
                        appendLine("\t\t\t" + element)
                    }
                }
            }
        }
    }
}
