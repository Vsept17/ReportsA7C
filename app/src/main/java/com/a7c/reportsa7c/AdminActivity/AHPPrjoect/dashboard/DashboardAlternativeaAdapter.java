package com.a7c.reportsa7c.AdminActivity.AHPPrjoect.dashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.godwyn.ahp_project.R;

import java.util.List;

public class DashboardAlternativeaAdapter extends RecyclerSwipeAdapter<DashboardAlternativeaAdapter.ItemViewHolder> {


    private List<String> mAlternatives;
    private Context mContext;
    private DashboardContract.View mAhpDashboardContract;
    private ItemViewHolder mViewHolder;

    private void setList(List<String> list) {
        mAlternatives = list;
    }

    public AhpDashboardAlternativeAdapter(List<String> alternatives, Context context, DashboardContract.View ahpDashboardContract) {
        this.mAlternatives = alternatives;
        this.mContext = context;
        this.mAhpDashboardContract = ahpDashboardContract;
    }

    public List<String> getAlternatives(){
        return mAlternatives;
    }

    public void replaceData(List<String> itemsMethod) {
        setList(itemsMethod);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mAlternatives.size();
    }

    public void addItem(String alternativeTitle){
        String alternative = alternativeTitle;
        mAlternatives.add(alternative);
        notifyDataSetChanged();
        mAhpDashboardContract.checkListsContent();
    }
    public void removeItem(int position){
        mAlternatives.remove(position);
        notifyDataSetChanged();
        mAhpDashboardContract.checkListsContent();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.ahp_dashboard_alternative_item_swipe_layout;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AhpDashboardAlternativeItemBinding ahpDashboardAlternativeItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.ahp_dashboard_alternative_item,
                        parent, false);

        return new ItemViewHolder(ahpDashboardAlternativeItemBinding);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        mViewHolder = holder;

        String alternative = mAlternatives.get(position);
        holder.mAhpDashboardAlternativeItemBinding.setAlternative(alternative);

        holder.mAhpDashboardAlternativeItemBinding.executePendingBindings();

        holder.mAhpDashboardAlternativeItemBinding.ahpDashboardRemoveAlternativeLinearLayout.setOnClickListener(v -> {
            removeItem(position);
        });

    }


    public class ItemViewHolder extends RecyclerView.ViewHolder{

        private AhpDashboardAlternativeItemBinding mAhpDashboardAlternativeItemBinding;

        public ItemViewHolder(AhpDashboardAlternativeItemBinding ahpDashboardAlternativeItemBinding) {
            super(ahpDashboardAlternativeItemBinding.getRoot());

            this.mAhpDashboardAlternativeItemBinding = ahpDashboardAlternativeItemBinding;

        }
    }
}