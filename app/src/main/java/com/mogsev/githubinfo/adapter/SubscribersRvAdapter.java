package com.mogsev.githubinfo.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mogsev.githubinfo.R;
import com.mogsev.githubinfo.model.Owner;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class SubscribersRvAdapter extends RecyclerView.Adapter<SubscribersRvAdapter.ViewHolder> {
    private static final String TAG = SubscribersRvAdapter.class.getSimpleName();

    private List<Owner> mList = new ArrayList<>();
    private Context mContext;

    public SubscribersRvAdapter(Context context) {
        Log.i(TAG, "SubscribersRvAdapter");
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_subscriber, parent, false);
        SubscribersRvAdapter.ViewHolder viewHolder = new SubscribersRvAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Owner owner = mList.get(position);

        String value = null;
        // show avatar
        value = owner.getAvatarUrl();
        if (!TextUtils.isEmpty(value)) {
            Picasso.with(mContext)
                    .load(value)
                    .error(R.drawable.ic_avatar_gray_24dp)
                    .into(holder.ivAvatar);
        }
        // show name
        value = owner.getLogin();
        holder.tvName.setText(mContext.getString(R.string.card_owner_login, value));
        // show id
        holder.tvId.setText(mContext.getString(R.string.card_owner_id, owner.getId()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(@Nullable List<Owner> list) {
        if (list != null && !list.isEmpty()) {
            mList = list;
            notifyDataSetChanged();
        }
    }

    /**
     * Class is like the helper
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivAvatar;
        public TextView tvName;
        public TextView tvId;

        public ViewHolder(View itemView) {
            super(itemView);

            ivAvatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvId = (TextView) itemView.findViewById(R.id.tvId);
        }
    }

}
