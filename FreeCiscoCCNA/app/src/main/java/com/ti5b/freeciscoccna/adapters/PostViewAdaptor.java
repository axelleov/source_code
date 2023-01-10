package com.ti5b.freeciscoccna.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ti5b.freeciscoccna.R;
import com.ti5b.freeciscoccna.models.Post;

import java.util.List;
import java.util.Objects;

public class PostViewAdaptor extends RecyclerView.Adapter<PostViewAdaptor.ViewHolder> {
    private Context mContext;
    private List<Post> mData;
    private OnItemLongClicklistener mOnItemLongClicklistener;

    public PostViewAdaptor(Context mContext, List<Post> mData, OnItemLongClicklistener mOnItemLongClicklistener) {
        this.mContext = mContext;
        this.mData = mData;
        this.mOnItemLongClicklistener = mOnItemLongClicklistener;
    }

    @NonNull
    @Override
    public PostViewAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view, mOnItemLongClicklistener);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewAdaptor.ViewHolder holder, int position) {
        holder.tvEmail.setText(mData.get(position).getEmail());
        holder.tvHeadingMain.setText(mData.get(position).getHeading_main());
        holder.tvCreatedDate.setText(mData.get(position).getCreated_date());

        //        HEADING 1

        if (holder.tvHeading1 == null) {
            holder.tvHeading1.setVisibility(View.GONE);
        } else {
            holder.tvHeading1.setVisibility(View.VISIBLE);
            holder.tvHeading1.setText(mData.get(position).getHeading1());
        }

        if (holder.tvHeading2 == null || holder.tvHeading2.equals("") || holder.tvHeading2.length() == 0) {
            holder.tvHeading2.setVisibility(View.GONE);
        } else {
            holder.tvHeading2.setVisibility(View.VISIBLE);
            holder.tvHeading2.setText(mData.get(position).getHeading2());
        }

        if (holder.tvHeading3 == null || holder.tvHeading3.equals("") || holder.tvHeading3.length() == 0) {
            holder.tvHeading3.setVisibility(View.GONE);
        } else {
            holder.tvHeading3.setVisibility(View.VISIBLE);
            holder.tvHeading3.setText(mData.get(position).getHeading3());
        }

        if (holder.tvHeading4 == null || holder.tvHeading4.equals("") || holder.tvHeading4.length() == 0) {
            holder.tvHeading4.setVisibility(View.GONE);
        } else {
            holder.tvHeading4.setVisibility(View.VISIBLE);
            holder.tvHeading4.setText(mData.get(position).getHeading4());
        }

        if (holder.tvHeading5 == null || holder.tvHeading5.equals("") || holder.tvHeading5.length() == 0) {
            holder.tvHeading5.setVisibility(View.GONE);
        } else {
            holder.tvHeading5.setVisibility(View.VISIBLE);
            holder.tvHeading5.setText(mData.get(position).getHeading5());
        }


        //        HEADING 1 PARAGRAPH 1

        if (holder.tvH1Paragraph1 == null) {
            holder.tvH1Paragraph1.setVisibility(View.GONE);
        } else {
            holder.tvH1Paragraph1.setVisibility(View.VISIBLE);
            holder.tvH1Paragraph1.setText(mData.get(position).getH1_paragraph1());
        }

        if (holder.tvH2Paragraph1 == null || holder.tvH2Paragraph1.equals("") || holder.tvH2Paragraph1.length() == 0) {
            holder.tvH2Paragraph1.setVisibility(View.GONE);
        } else {
            holder.tvH2Paragraph1.setVisibility(View.VISIBLE);
            holder.tvH2Paragraph1.setText(mData.get(position).getH2_paragraph1());
        }

        if (holder.tvH3Paragraph1 == null || holder.tvH3Paragraph1.equals("") || holder.tvH3Paragraph1.length() == 0) {
            holder.tvH3Paragraph1.setVisibility(View.GONE);
        } else {
            holder.tvH3Paragraph1.setVisibility(View.VISIBLE);
            holder.tvH3Paragraph1.setText(mData.get(position).getH3_paragraph1());
        }

        if (holder.tvH4Paragraph1 == null || holder.tvH4Paragraph1.equals("") || holder.tvH4Paragraph1.length() == 0) {
            holder.tvH4Paragraph1.setVisibility(View.GONE);
        } else {
            holder.tvH4Paragraph1.setVisibility(View.VISIBLE);
            holder.tvH4Paragraph1.setText(mData.get(position).getH4_paragraph1());
        }

        if (holder.tvH5Paragraph1 == null || holder.tvH5Paragraph1.equals("") || holder.tvH5Paragraph1.length() == 0) {
            holder.tvH5Paragraph1.setVisibility(View.GONE);
        } else {
            holder.tvH5Paragraph1.setVisibility(View.VISIBLE);
            holder.tvH5Paragraph1.setText(mData.get(position).getH5_paragraph1());
        }


//        HEADING 1 PARAGRAPH 2

        if (holder.tvH1Paragraph2 == null) {
            holder.tvH1Paragraph2.setVisibility(View.GONE);
        } else {
            holder.tvH1Paragraph2.setVisibility(View.VISIBLE);
            holder.tvH1Paragraph2.setText(mData.get(position).getH1_paragraph2());
        }

        if (holder.tvH2Paragraph2 == null || holder.tvH2Paragraph2.equals("") || holder.tvH2Paragraph2.length() == 0) {
            holder.tvH2Paragraph2.setVisibility(View.GONE);
        } else {
            holder.tvH2Paragraph2.setVisibility(View.VISIBLE);
            holder.tvH2Paragraph2.setText(mData.get(position).getH2_paragraph2());
        }

        if (holder.tvH3Paragraph2 == null || holder.tvH3Paragraph2.equals("") || holder.tvH3Paragraph2.length() == 0) {
            holder.tvH3Paragraph2.setVisibility(View.GONE);
        } else {
            holder.tvH3Paragraph2.setVisibility(View.VISIBLE);
            holder.tvH3Paragraph2.setText(mData.get(position).getH3_paragraph2());
        }

        if (holder.tvH4Paragraph2 == null || holder.tvH4Paragraph2.equals("") || holder.tvH4Paragraph2.length() == 0) {
            holder.tvH4Paragraph2.setVisibility(View.GONE);
        } else {
            holder.tvH4Paragraph2.setVisibility(View.VISIBLE);
            holder.tvH4Paragraph2.setText(mData.get(position).getH4_paragraph2());
        }

        if (holder.tvH5Paragraph2 == null || holder.tvH5Paragraph2.equals("") || holder.tvH5Paragraph2.length() == 0) {
            holder.tvH5Paragraph2.setVisibility(View.GONE);
        } else {
            holder.tvH5Paragraph2.setVisibility(View.VISIBLE);
            holder.tvH5Paragraph2.setText(mData.get(position).getH5_paragraph2());
        }

//        HEADING IMAGE

        Glide
                .with(mContext)
                .load(mData.get(position).getImage_main())
                .centerCrop()
                .placeholder(R.drawable.logo_no_internet)
                .into(holder.ivImageMain);

        if (holder.ivH1Image == null) {
            holder.ivH1Image.setVisibility(View.GONE);
        } else {
            holder.ivH1Image.setVisibility(View.VISIBLE);
            Glide
                    .with(mContext)
                    .load(mData.get(position).getH1_image())
                    .centerCrop()
                    .into(holder.ivH1Image);
        }

        if (holder.ivH2Image == null) {
            holder.ivH2Image.setVisibility(View.GONE);

        } else {
            holder.ivH2Image.setVisibility(View.VISIBLE);
            Glide
                    .with(mContext)
                    .load(mData.get(position).getH2_image())
                    .centerCrop()
                    .into(holder.ivH2Image);
        }

        if (holder.ivH3Image == null) {
            holder.ivH3Image.setVisibility(View.GONE);

        } else {
            holder.ivH3Image.setVisibility(View.VISIBLE);
            Glide
                    .with(mContext)
                    .load(mData.get(position).getH3_image())
                    .centerCrop()
                    .into(holder.ivH3Image);
        }

        Glide
                .with(mContext)
                .load(mData.get(position).getH4_image())
                .centerCrop()
                .into(holder.ivH4Image);

        if (holder.ivH4Image != null) {
            holder.ivH4Image.setVisibility(View.VISIBLE);

        }

        Glide
                .with(mContext)
                .load(mData.get(position).getH5_image())
                .centerCrop()
                .into(holder.ivH5Image);

        if (holder.ivH5Image != null) {
            holder.ivH5Image.setVisibility(View.VISIBLE);

        }

        boolean isExpandable = mData.get(position).isExpandable();
        holder.expandLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        public TextView tvEmail, tvHeadingMain, tvCreatedDate;
        public TextView tvHeading1, tvHeading2, tvHeading3, tvHeading4, tvHeading5;
        public TextView tvH1Paragraph1, tvH2Paragraph1, tvH3Paragraph1, tvH4Paragraph1, tvH5Paragraph1;
        public TextView tvH1Paragraph2, tvH2Paragraph2, tvH3Paragraph2, tvH4Paragraph2, tvH5Paragraph2;
        public ImageView ivImageMain, ivH1Image, ivH2Image, ivH3Image, ivH4Image, ivH5Image;
        ImageView imageView;
        public OnItemLongClicklistener onItemLongClicklistener;

        LinearLayout linearLayout, expandLayout;

        public ViewHolder(@NonNull View itemView, OnItemLongClicklistener onItemLongClicklistener) {
            super(itemView);

            tvEmail = itemView.findViewById(R.id.tv_email);
            ivImageMain = itemView.findViewById(R.id.iv_image_main);
            tvHeadingMain = itemView.findViewById(R.id.tv_heading_main);
            tvCreatedDate = itemView.findViewById(R.id.tv_created_date);

            tvHeading1 = itemView.findViewById(R.id.tv_heading1);
            tvHeading2 = itemView.findViewById(R.id.tv_heading2);
            tvHeading3 = itemView.findViewById(R.id.tv_heading3);
            tvHeading4 = itemView.findViewById(R.id.tv_heading4);
            tvHeading5 = itemView.findViewById(R.id.tv_heading5);

            tvH1Paragraph1 = itemView.findViewById(R.id.tv_h1_paragraph1);
            tvH2Paragraph1 = itemView.findViewById(R.id.tv_h2_paragraph1);
            tvH3Paragraph1 = itemView.findViewById(R.id.tv_h3_paragraph1);
            tvH4Paragraph1 = itemView.findViewById(R.id.tv_h4_paragraph1);
            tvH5Paragraph1 = itemView.findViewById(R.id.tv_h5_paragraph1);

            tvH1Paragraph2 = itemView.findViewById(R.id.tv_h1_paragraph2);
            tvH2Paragraph2 = itemView.findViewById(R.id.tv_h2_paragraph2);
            tvH3Paragraph2 = itemView.findViewById(R.id.tv_h3_paragraph2);
            tvH4Paragraph2 = itemView.findViewById(R.id.tv_h4_paragraph2);
            tvH5Paragraph2 = itemView.findViewById(R.id.tv_h5_paragraph2);

            ivH1Image = itemView.findViewById(R.id.iv_h1_image);
            ivH2Image = itemView.findViewById(R.id.iv_h2_image);
            ivH3Image = itemView.findViewById(R.id.iv_h3_image);
            ivH4Image = itemView.findViewById(R.id.iv_h4_image);
            ivH5Image = itemView.findViewById(R.id.iv_h5_image);

            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandLayout = itemView.findViewById(R.id.expand_layout);
            imageView = itemView.findViewById(R.id.image_view);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Post post = mData.get(getAdapterPosition());
                    post.setExpandable(!post.isExpandable());
                    notifyItemChanged(getAdapterPosition());

                    if (expandLayout.getVisibility() == View.GONE) {
                        imageView.setImageResource(R.drawable.ic_arrow_down);

                    } if (expandLayout.getVisibility() == View.VISIBLE) {
                        imageView.setImageResource(R.drawable.ic_arrow_up);
                    }
                }
            });
            this.onItemLongClicklistener = onItemLongClicklistener;
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            onItemLongClicklistener.onItemLongClick(v, getAdapterPosition());
            return false;
        }
    }

    public interface OnItemLongClicklistener {
        void onItemLongClick(View v, int position);
    }
}
