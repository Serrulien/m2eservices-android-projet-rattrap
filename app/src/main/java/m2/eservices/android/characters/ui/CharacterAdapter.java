package m2.eservices.android.characters.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android_m2_eservices_projet_etu.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private CharacterListContract.Presenter presenter;

    public CharacterAdapter(CharacterListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public CharacterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_horizontal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        this.presenter.getCharacter(position+1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                System.out.println(throwable);
                            }
                        }
                )
                .subscribe(
                        new Consumer<CharacterViewModel>() {
                            @Override
                            public void accept(CharacterViewModel characterViewModel) throws Exception {
                                holder.name_character.setText(characterViewModel.getName());
                                Glide.with(holder.itemView.getContext())
                                        .load(characterViewModel.getImgUrl())
                                        .centerCrop()
                                        .into(holder.img_character);
                            }
                        }
                );
    }

    @Override
    public int getItemCount() {
        return 100;
        //return this.presenter.getItemCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_character;
        private TextView name_character;
        public ViewHolder(View view) {
            super(view);
            this.img_character = (ImageView) view.findViewById(R.id.img_character);
            this.name_character = (TextView) view.findViewById(R.id.name_character);
        }
    }

}
