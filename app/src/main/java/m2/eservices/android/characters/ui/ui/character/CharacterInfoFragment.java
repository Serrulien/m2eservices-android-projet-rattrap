package m2.eservices.android.characters.ui.ui.character;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.example.android_m2_eservices_projet_etu.R;

public class CharacterInfoFragment extends Fragment implements CharacterContract.View {

    public static CharacterInfoFragment newInstance(CharacterViewModel viewModel) {
        CharacterInfoFragment fragment = new CharacterInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("viewModel", viewModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    private CharacterViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_character, container, false);
        this.viewModel = (CharacterViewModel) this.getArguments().getSerializable("viewModel");
        this.populateView(root, this.viewModel);
        return root;
    }

    private void populateView(View view, CharacterViewModel viewModel) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(this.getContext());
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();
        ImageView img = view.findViewById(R.id.img_character);
        img.setImageDrawable(circularProgressDrawable);

        Glide.with(view.getContext())
                .load(viewModel.getImageUrl())
                .centerCrop()
                .into(img);

        ((TextView) view.findViewById(R.id.name_character)).setText(viewModel.getName());
        ((TextView) view.findViewById(R.id.status_character)).setText(viewModel.getStatus());
        ((TextView) view.findViewById(R.id.gender_character)).setText(viewModel.getGender());
        ((TextView) view.findViewById(R.id.origin_character)).setText(viewModel.getOrigin().getName());
    }
}