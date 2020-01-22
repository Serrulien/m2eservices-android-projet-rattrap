package m2.eservices.android.characters.ui.main;

import android.content.Context;
import android.content.Intent;
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
import m2.eservices.android.characters.ui.ui.character.CharacterActivity;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private Context context;
    private CharacterListContract.Presenter presenter;

    public CharacterAdapter(Context context, CharacterListContract.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public CharacterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item, parent, false);
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
                        new SingleObserver<CharacterListItemViewModel>() {
                            @Override
                            public void onSubscribe(Disposable d) { }

                            @Override
                            public void onSuccess(CharacterListItemViewModel characterViewModel) {
                                holder.name_character.setText(characterViewModel.getName());

                                Glide.with(holder.itemView.getContext())
                                    .load(characterViewModel.getImgUrl())
                                    .centerCrop()
                                    .into(holder.img_character);

                                setupClickListener(holder, characterViewModel.getId());
                            }

                            @Override
                            public void onError(Throwable e) { }
                        }
                );
    }

    private void setupClickListener(final RecyclerView.ViewHolder holder, final int charId) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CharacterActivity.class);
                intent.putExtra("charId", charId);
                context.startActivity(intent);
            }
        });
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
