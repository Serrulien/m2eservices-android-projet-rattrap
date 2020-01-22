package m2.eservices.android.characters.ui.ui.character;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.android_m2_eservices_projet_etu.R;

public class CharacterEpisondesFragment extends Fragment implements CharacterContract.View {

    public static CharacterEpisondesFragment newInstance(CharacterEpisodesViewModel viewModel) {
        CharacterEpisondesFragment fragment = new CharacterEpisondesFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("viewModel", viewModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    private CharacterEpisodesViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_episodes_character, container, false);
        this.viewModel = (CharacterEpisodesViewModel) this.getArguments().getSerializable("viewModel");
        this.populateView(root, this.viewModel);
        return root;
    }

    private void populateView(View view, CharacterEpisodesViewModel viewModel) {
        ((TextView) view.findViewById(R.id.episodes_appareance_character))
                .setText(
                        getResources().getString(
                                R.string.episodes_appereance, viewModel.getEpisodes().size()
                        )
                );

        String episodes = "";
        for (String str: viewModel.getEpisodes()) {
            String[] split = str.split("/");
            episodes += split[split.length-1] + ", ";
        }
        ((TextView) view.findViewById(R.id.episodes_list))
                .setText(episodes);
    }
}