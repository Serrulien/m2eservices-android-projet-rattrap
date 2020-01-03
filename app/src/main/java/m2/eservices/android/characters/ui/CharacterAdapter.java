package m2.eservices.android.characters.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.example.android_m2_eservices_projet_etu.R;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(holder.itemView.getContext());
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();
        holder.img_character.setImageDrawable(circularProgressDrawable);

        this.presenter.getCharacter(position+1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new SingleObserver<CharacterViewModel>() {
                            @Override
                            public void onSubscribe(Disposable d) { }

                            @Override
                            public void onSuccess(CharacterViewModel characterViewModel) {
                                holder.name_character.setText(characterViewModel.getName());

                                Glide.with(holder.itemView.getContext())
                                    .load(characterViewModel.getImgUrl())
                                    .centerCrop()
                                    .into(holder.img_character);
                            }

                            @Override
                            public void onError(Throwable e) { }
                        }
                );
    }

    @Override
    public int getItemCount() {
        return this.presenter.getItemCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_character;
        private TextView name_character;
        public ViewHolder(View view) {
            super(view);
            this.img_character = view.findViewById(R.id.img_character);
            this.name_character = view.findViewById(R.id.name_character);
        }
    }

}
