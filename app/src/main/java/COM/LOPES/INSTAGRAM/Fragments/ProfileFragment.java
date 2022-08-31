package COM.LOPES.INSTAGRAM.Fragments;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import COM.LOPES.INSTAGRAM.Models.post;

public class ProfileFragment extends PostFragment{

    @Override
    protected void queryposts() {
        super.queryposts();
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
