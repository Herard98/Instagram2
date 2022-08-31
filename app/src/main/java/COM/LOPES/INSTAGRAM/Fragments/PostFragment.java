package COM.LOPES.INSTAGRAM.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import COM.LOPES.INSTAGRAM.Adapter.PostAdapter;
import COM.LOPES.INSTAGRAM.LoginActivity;
import COM.LOPES.INSTAGRAM.Models.post;
import COM.LOPES.INSTAGRAM.R;

public class PostFragment extends Fragment {
    TextView tvScreenName;
      RecyclerView rvPosts;
      PostAdapter adapter;
      List<post> Allposts;
      Boolean mFirstLoad;
    Button btnLogout;
     ImageView ivProfileImage;

    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvPosts = view.findViewById(R.id.rvPosts);
       tvScreenName=view.findViewById(R.id.tvScreenName);
        Allposts = new ArrayList<>();
        adapter = new PostAdapter(Allposts,getContext());
        rvPosts.setAdapter(adapter);
        btnLogout = view.findViewById(R.id.btnLogout);
        ivProfileImage = view.findViewById(R.id.ivProfileImage);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvPosts.setLayoutManager(layoutManager);
        layoutManager.setReverseLayout(true);
        mFirstLoad=true;

        queryposts();
        tvScreenName.setText(ParseUser.getCurrentUser().getUsername());
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();

                goLoginActivity();
            }

            private void goLoginActivity() {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    protected void queryposts() {
        ParseQuery<post> query = ParseQuery.getQuery(post.class);
        query.include(post.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(post.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<post>() {
            @Override
            public void done(List<post> Posts, ParseException e) {

                for (post Post: Posts){

                    Log.i("ok", "post " +Post.getDescription() + ",username " +Post.getUser().getUsername());
                }

                if(e == null){
                    Allposts.clear();
                    Allposts.addAll(Posts);
                    adapter.notifyDataSetChanged();
                }
                if(mFirstLoad){
                    rvPosts.scrollToPosition(0);
                    mFirstLoad= false;
                }
                else {
                    Log.e("post", "Error Loading post" + e);
                }

            }
        });
    }

}