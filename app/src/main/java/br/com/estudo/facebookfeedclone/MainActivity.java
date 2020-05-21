package br.com.estudo.facebookfeedclone;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.estudo.facebookfeedclone.model.Post;

public class MainActivity extends AppCompatActivity {

    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecycler();
        postsFakes();
    }

    void postsFakes() {
        List<Post> posts = new ArrayList<>();

        Post post1 = new Post();
        post1.setImageViewUser(R.drawable.jon_snow);
        post1.setImageViewPost(R.drawable.post_1);
        post1.setTextViewUsername(getString(R.string.jon_snow));
        post1.setTextViewContent(getString(R.string.text_post_jon));
        post1.setTextViewTitle(getString(R.string.link_first_post).toUpperCase());
        post1.setTextViewSubtitle(getString(R.string.title_first_post));
        post1.setTextViewTime(getString(R.string.time_post_ago));

        posts.add(post1);

        postAdapter.setPosts(posts);
        postAdapter.notifyDataSetChanged();
    }

    void setupRecycler() {
        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        postAdapter = new PostAdapter();
        rv.setAdapter(postAdapter);
    }

    private static class PostViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewUser;
        private final ImageView imageViewPost;
        private final TextView textViewTime;
        private final TextView textViewUsername;
        private final TextView textViewContent;
        private final TextView textViewTitle;
        private final TextView textViewSubtitle;

        private PostViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewUser = itemView.findViewById(R.id.image_view_user);
            imageViewPost = itemView.findViewById(R.id.image_view_post);
            textViewTime = itemView.findViewById(R.id.text_view_time);
            textViewUsername = itemView.findViewById(R.id.text_view_username);
            textViewContent = itemView.findViewById(R.id.text_view_content);
            textViewTitle = itemView.findViewById(R.id.text_view_post_title);
            textViewSubtitle = itemView.findViewById(R.id.text_view_post_subtitle);
        }

        void bind(Post post) {
            imageViewUser.setImageResource(post.getImageViewUser());
            imageViewPost.setImageResource(post.getImageViewPost());
            textViewTime.setText(post.getTextViewTime());
            textViewUsername.setText(post.getTextViewUsername());
            textViewContent.setText(post.getTextViewContent());
            textViewTitle.setText(post.getTextViewTitle());
            textViewSubtitle.setText(post.getTextViewSubtitle());
        }
    }

    private class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

        private List<Post> posts = new ArrayList<>();

        @NonNull
        @Override
        public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.feed_item, parent, false);
            return new PostViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
            Post post = posts.get(position);
            holder.bind(post);
        }

        @Override
        public int getItemCount() {
            return posts.size();
        }

        private void setPosts(List<Post> posts) {
            this.posts = posts;
        }
    }

}

