package life.league.challenge.java.modules.feeds.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import life.league.challenge.java.R;
import life.league.challenge.java.common.utils.Glider;
import life.league.challenge.java.modules.models.UserFeed;

/**
 * Created by Pramod Selvaraj on 03-07-2021.
 * Social Media Feeds Adapter View Holder
 */
public class FeedsViewHolder extends RecyclerView.ViewHolder {
    public final ImageView mAvatar;
    public final TextView mUsername;
    public final TextView mTitle;
    public final TextView mDescription;

    private FeedsViewHolder(View itemView) {
        super(itemView);
        mAvatar = itemView.findViewById(R.id.img_avatar);
        mUsername = itemView.findViewById(R.id.txt_username);
        mTitle = itemView.findViewById(R.id.txt_title);
        mDescription = itemView.findViewById(R.id.txt_description);
    }

    static FeedsViewHolder create(ViewGroup parent) {
        return new FeedsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_feeds_row, parent, false));
    }

    public void bind(UserFeed userFeed) {
        /*Set User Avatar Using Glide*/
        Glider.loadRoundGlideImage(userFeed.avatar,
                R.drawable.ic_placeholder_avatar, mAvatar);
        /*Set The Username*/
        mUsername.setText(userFeed.user_name);
        /*Set The Title*/
        mTitle.setText(userFeed.title);
        /*Set The Description*/
        mDescription.setText(userFeed.description);
    }
}