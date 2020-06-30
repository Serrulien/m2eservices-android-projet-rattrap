package android.eservices.rawgytbmonitor.presentation.search.adapter;

import android.eservices.rawgytbmonitor.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {


    public static class GameViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView ratingTextView;
        private ImageView imageView;
        private View v;
        private GameItemViewModel gameItemViewModel;
        private GameActionInterface gameActionInterface;
        private Switch favoriteSwitch;

        public GameViewHolder(View v, final GameActionInterface gameActionInterface) {
            super(v);
            this.v = v;
            nameTextView = v.findViewById(R.id.game_title_textview);
            ratingTextView = v.findViewById(R.id.game_rating_textview);
            imageView = v.findViewById(R.id.game_icon_imageview);
            favoriteSwitch = v.findViewById(R.id.favorite_switch);
            this.gameActionInterface = gameActionInterface;
            setupListeners();
        }

        private void setupListeners() {
            favoriteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    gameActionInterface.onFavoriteToggle(gameItemViewModel.getGameId(), b);
                }
            });
        }

        void bind(GameItemViewModel gameItemViewModel) {
            this.gameItemViewModel = gameItemViewModel;
            nameTextView.setText(gameItemViewModel.getName());
            ratingTextView.setText(String.format("%.2f", gameItemViewModel.getRating()));
            favoriteSwitch.setChecked(gameItemViewModel.isFavorite());
            Glide.with(v)
                    .load(gameItemViewModel.getImageUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);

        }

    }

    private List<GameItemViewModel> gameItemViewModelList;
    private GameActionInterface gameActionInterface;

    // Provide a suitable constructor (depends on the kind of dataset)
    public GameAdapter(GameActionInterface gameActionInterface) {
        gameItemViewModelList = new ArrayList<>();
        this.gameActionInterface = gameActionInterface;
    }

    public void bindViewModels(List<GameItemViewModel> gameItemViewModelList) {
        this.gameItemViewModelList.clear();
        this.gameItemViewModelList.addAll(gameItemViewModelList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_game, parent, false);
        GameViewHolder gameViewHolder = new GameViewHolder(v, gameActionInterface);
        return gameViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        holder.bind(gameItemViewModelList.get(position));
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return gameItemViewModelList.size();
    }


}