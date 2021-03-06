package net.jwenzel.matchhistory

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import net.jeremywenzel.halostats.core.haloapi.GameBaseVariant
import net.jeremywenzel.halostats.core.haloapi.MatchHistoryItem
import net.jeremywenzel.halostats.core.haloapi.MultiplayerMap
import net.jeremywenzel.halostats.core.util.Duration
import net.jwenzel.coremvp.fragment.BaseMvpFragment

class MatchHistoryItemFragment: BaseMvpFragment<MatchHistoryItemView, MatchHistoryItemPresenter>(), MatchHistoryItemView {

    private lateinit var mapImageView: ImageView
    private lateinit var mapAndGameTypeTextView: TextView
    private lateinit var gameTimeTextView: TextView
    private lateinit var gameOutcomeTextView: TextView

    override fun createPresenter(): MatchHistoryItemPresenter = MatchHistoryItemPresenterImpl(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.match_history_item_fragment, container, false)
        mapImageView = view.findViewById(R.id.match_history_map_image)
        gameTimeTextView = view.findViewById(R.id.match_history_game_time)
        mapAndGameTypeTextView = view.findViewById(R.id.match_history_game_and_map)
        gameOutcomeTextView = view.findViewById(R.id.match_history_game_outcome)
        return view
    }

    override fun showCard(matchHistoryItem: MatchHistoryItem, multiplayerMap: MultiplayerMap, gameBaseVariant: GameBaseVariant, isVictory: Boolean) {
        Glide.with(this).load(multiplayerMap.imageUrl).centerCrop().into(mapImageView)
        mapAndGameTypeTextView.text = java.lang.String.format(getString(R.string.match_history_card_game_and_map), multiplayerMap.name, gameBaseVariant.name)

        val duration = Duration.fromIsoString(matchHistoryItem.matchDuration)
        gameTimeTextView.text = java.lang.String.format("%d:%02d", duration.minutes, duration.seconds)

        gameOutcomeTextView.text = when (isVictory) {
            true -> getString(R.string.match_history_card_victory)
            false -> getString(R.string.match_history_card_defeat)
        }
        gameOutcomeTextView.setTextColor(when (isVictory) {
            true -> ContextCompat.getColor(context!!, R.color.victory)
            false -> ContextCompat.getColor(context!!, R.color.defeat)
        })
    }

    override val shouldShowBackButton: Boolean = false
}