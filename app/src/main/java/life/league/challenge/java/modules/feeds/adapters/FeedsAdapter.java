package life.league.challenge.java.modules.feeds.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import org.jetbrains.annotations.NotNull;

import life.league.challenge.java.modules.models.UserFeed;

/**
 * Created by Pramod Selvaraj on 03-07-2021.
 * Social Media Feeds Adapter
 */
public class FeedsAdapter extends ListAdapter<UserFeed, FeedsViewHolder> {

    public FeedsAdapter(@NonNull DiffUtil.ItemCallback<UserFeed> diffCallback) {
        super(diffCallback);
    }

    @NotNull
    @Override
    public FeedsViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return FeedsViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(FeedsViewHolder holder, int position) {
        UserFeed userFeed = getItem(position);
        holder.bind(userFeed);
    }

    public static class WordDiff extends DiffUtil.ItemCallback<UserFeed> {
        @Override
        public boolean areItemsTheSame(@NonNull UserFeed oldItem, @NonNull UserFeed newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserFeed oldItem, @NonNull UserFeed newItem) {
            return oldItem.post_id == newItem.post_id;
        }
    }
}