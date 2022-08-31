package COM.LOPES.INSTAGRAM.Adapter;import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

import COM.LOPES.INSTAGRAM.Models.post;
import COM.LOPES.INSTAGRAM.R;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    List<post> posts;
    Context context;


    public PostAdapter(List<post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View postView = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false);
        return new ViewHolder(postView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        post Post = posts.get(position);

        holder.bind(Post);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ImFav;
        ImageView ImMes;
        ImageView ImSh;
        ImageView ImPostRetrieve;
        TextView tvUsernameP;
        TextView tvDescriptionP;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ImFav = itemView.findViewById(R.id.ImFav);
            ImMes = itemView.findViewById(R.id.ImMes);
            ImSh = itemView.findViewById(R.id.ImSh);
            ImPostRetrieve = itemView.findViewById(R.id.ImPostRetrieve);
            tvDescriptionP = itemView.findViewById(R.id.tvDescriptionP);
            tvUsernameP = itemView.findViewById(R.id.tvUsernameP);

        }


        public void bind(post post) {
            tvDescriptionP.setText(post.getDescription());
            try {
                tvUsernameP.setText(post.getUser().getUsername());
            }catch (Exception e){

            }

            ParseFile image = post.getImage();
            if(image !=null){
                Glide.with(context).load(image.getUrl()) .into(ImPostRetrieve);
            }
        }

    }
}
