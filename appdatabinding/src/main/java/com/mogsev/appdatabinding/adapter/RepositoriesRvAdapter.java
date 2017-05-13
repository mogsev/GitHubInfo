package com.mogsev.appdatabinding.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mogsev.appdatabinding.R;
import com.mogsev.appdatabinding.activity.RepositoryInfoActivity;
import com.mogsev.appdatabinding.model.Repository;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class RepositoriesRvAdapter extends RecyclerView.Adapter {
    private static final String TAG = RepositoriesRvAdapter.class.getSimpleName();

    private static final int CARD_VIEW_REPOSITORY = 0;
    private static final int CARD_VIEW_PROGRESS_BAR = 1;

    private List<Repository> mList = new ArrayList<>();
    private Context mContext;

    public RepositoriesRvAdapter(@NonNull Context context) {
        Log.i(TAG, "RepositoriesRvAdapter");
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder");
        RecyclerView.ViewHolder viewHolder = null;
        View view = null;
        switch (viewType) {
            case CARD_VIEW_REPOSITORY:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_repository, parent, false);
                viewHolder = new RepositoryViewHolder(view);
                break;
            case CARD_VIEW_PROGRESS_BAR:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress_loading, parent, false);
                viewHolder = new ProgressViewHolder(view);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Repository repository = mList.get(position);
        if (holder instanceof RepositoryViewHolder) {
            RepositoryViewHolder viewHolder = (RepositoryViewHolder) holder;
            String value = null;
            // show avatar
            value = repository.getOwner().getAvatarUrl();
            if (!TextUtils.isEmpty(value)) {
                Picasso.with(mContext)
                        .load(value)
                        .error(R.drawable.ic_avatar_gray_24dp)
                        .into(viewHolder.ivAvatar);
            }
            // show name
            value = repository.getName();
            viewHolder.tvName.setText(mContext.getString(R.string.card_repository_name, value));
            // show name
            viewHolder.tvCountOfForks.setText(mContext.getString(R.string.card_repository_count_of_forks, repository.getForksCount()));
            // show description
            value = repository.getDescription();
            viewHolder.tvDescription.setText(mContext.getString(R.string.card_repository_description, value));
            // set open detail info of repository
            viewHolder.llRepository.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, RepositoryInfoActivity.class);
                    intent.putExtra(Repository.BUNDLE_NAME, repository);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position) != null ? CARD_VIEW_REPOSITORY : CARD_VIEW_PROGRESS_BAR;
    }

    public synchronized void addItem(@NonNull Repository repository, int position) {
        Log.i(TAG, "addItem: " + position);
        mList.add(repository);
        notifyItemInserted(position);
    }

    public synchronized void addProgress() {
        Log.i(TAG, "addProgress");
        mList.add(null);
        notifyItemInserted(mList.size() - 1);
    }

    public synchronized void removeProgress() {
        Log.i(TAG, "removeProgress");
        int pos = mList.size() - 1;
        mList.remove(pos);
        notifyItemRemoved(pos);
    }

    public void setList(@Nullable List<Repository> list) {
        if (list != null && !list.isEmpty()) {
            mList = list;
            notifyDataSetChanged();
        }
    }

    /**
     * Class is like the helper
     */
    public static class RepositoryViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout llRepository;
        public ImageView ivAvatar;
        public TextView tvName;
        public TextView tvCountOfForks;
        public TextView tvDescription;

        public RepositoryViewHolder(View itemView) {
            super(itemView);

            llRepository = (LinearLayout) itemView.findViewById(R.id.llRepository);
            ivAvatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvCountOfForks = (TextView) itemView.findViewById(R.id.tvCountOfForks);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
        }
    }

    /**
     * Class shows progress loading when pagination starts
     */
    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = ProgressViewHolder.class.getSimpleName();

        public LinearLayout linearLayout;

        public ProgressViewHolder(View itemView) {
            super(itemView);
            //linearLayout = (LinearLayout) itemView.findViewById(R.id.llProgressLoading);
        }
    }

}
