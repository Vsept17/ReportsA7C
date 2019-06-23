package com.a7c.reportsa7c.AdminActivity.AHPPrjoect.dashboard;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.a7c.reportsa7c.AdminActivity.AHPPrjoect.dashboard.DashboardFragment.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class DashboardFragment extends Fragment implements DashboardContract.View{


    private AhpDashboardContract.Presenter mPresenter;
    private AhpDashboardFragBinding mAhpDashboardFragBinding;
    private AhpDashboardAlternativeAdapter mAhpDashboardAlternativeAdapter;
    private AhpDashboardCriterionAdapter mAhpDashboardCriterionAdapter;
    private Dialog addCriterionDialog;
    private Dialog addAlternativeDialog;
    private AhpDashboardAddCriterionDialogBinding mAhpDashboardAddCriterionDialogBinding;
    private AhpDashboardAddAlternativeDialogBinding mAhpDashboardAddAlternativeDialogBinding;
    private AhpResultsDialog mAhpResultsDialog;


    public AhpDashboardFragment() {}

    public static AhpDashboardFragment newInstance() {
        return new AhpDashboardFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        List<String> criterionList = new ArrayList<>();
        List<String> alternativeList= new ArrayList<>();
        for (int x=0; x<4;x++){
            String criterion = "Criterion "+x;
            String alternative = "Alternative "+x;
            criterionList.add(criterion);
            alternativeList.add(alternative);
        }

        mAhpDashboardAlternativeAdapter = new AhpDashboardAlternativeAdapter( new ArrayList<String>(0),getContext(),this);
        mAhpDashboardCriterionAdapter = new AhpDashboardCriterionAdapter( new ArrayList<String>(0),getContext(),this);

        mAhpDashboardAlternativeAdapter.replaceData(alternativeList);
        mAhpDashboardCriterionAdapter.replaceData(criterionList);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == RESULT_OK) {

            float [][] preferenceMatrix = (float[][]) data.getExtras().getSerializable("preferenceMatrix");
            float [] averagePriorityMatrix = (float[]) data.getExtras().getSerializable("averagePriorityMatrix");

            mAhpResultsDialog.showResults(averagePriorityMatrix,preferenceMatrix,mAhpDashboardAlternativeAdapter.getAlternatives(),mAhpDashboardCriterionAdapter.getCriterions());

            System.out.println("Matrix of preference");
            imprimeMatriz(preferenceMatrix);
        }
    }

    private void imprimeMatriz(float [][] criterion1matrix3){
        for(float[] c : criterion1matrix3){

            for(float elemento : c)
                System.out.printf(" %.2f ", elemento);
            System.out.printf("\n");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAhpDashboardFragBinding = DataBindingUtil.inflate(inflater, R.layout.ahp_dashboard_frag,container,false);
        mAhpDashboardFragBinding.setHandler(this);


        mAhpResultsDialog = new AhpResultsDialog(getContext(),this);

        if (mAhpDashboardFragBinding.ahpDashboardToolbar != null){
            ((AppCompatActivity) getActivity()).setSupportActionBar(mAhpDashboardFragBinding.ahpDashboardToolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            mAhpDashboardFragBinding.ahpDashboardToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_left));
            mAhpDashboardFragBinding.ahpDashboardToolbar.setNavigationOnClickListener(View ->{
                getActivity().onBackPressed();
            });
        }

        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.LayoutManager layout2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mAhpDashboardFragBinding.ahpDashboardAlternativesListRecyclerView.setLayoutManager(layout);
        mAhpDashboardFragBinding.ahpDashboardAlternativesListRecyclerView.setNestedScrollingEnabled(false);
        mAhpDashboardFragBinding.ahpDashboardAlternativesListRecyclerView.setFocusable(false);
        mAhpDashboardFragBinding.ahpDashboardAlternativesListRecyclerView.setAdapter(mAhpDashboardAlternativeAdapter);

        mAhpDashboardFragBinding.ahpDashboardCriterionsListRecyclerView.setLayoutManager(layout2);
        mAhpDashboardFragBinding.ahpDashboardCriterionsListRecyclerView.setNestedScrollingEnabled(false);
        mAhpDashboardFragBinding.ahpDashboardCriterionsListRecyclerView.setFocusable(false);
        mAhpDashboardFragBinding.ahpDashboardCriterionsListRecyclerView.setAdapter(mAhpDashboardCriterionAdapter);


        checkListsContent();
        configDialogs();

        setHasOptionsMenu(true);
        return mAhpDashboardFragBinding.getRoot();
    }

    @Override
    public void checkListsContent(){
        if(mAhpDashboardAlternativeAdapter.getItemCount() > 0){
            mAhpDashboardFragBinding.ahpDashboardNoAlternativesTextView.setVisibility(View.GONE);
        }

        if(mAhpDashboardCriterionAdapter.getItemCount() > 0){
            mAhpDashboardFragBinding.ahpDashboardNoCriterionsTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void startNew(View view) {
        if(mAhpDashboardAlternativeAdapter.getItemCount() == 4 && mAhpDashboardCriterionAdapter.getItemCount() == 4){
            Intent intent = new Intent(getContext(), AhpStepperActivity.class);
            AhpMethod ahpMethod = new AhpMethod(mAhpDashboardCriterionAdapter.getCriterions(),mAhpDashboardAlternativeAdapter.getAlternatives());
            intent.putExtra("ahpBundle", (Serializable) ahpMethod );
            startActivityForResult(intent, 1);
        }else{
            Toast.makeText(getContext(), "Continue and add 4 criterion and 4 alternative", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void addCriterion(View view) {
        String criterionTitle = mAhpDashboardAddCriterionDialogBinding.ahpDashboardCriterionValueEditText.getText().toString();
        mAhpDashboardAddCriterionDialogBinding.ahpDashboardCriterionValueEditText.setText("");
        if(!criterionTitle.equals(""))
            mAhpDashboardCriterionAdapter.addItem(criterionTitle);

        addCriterionDialog.cancel();
    }

    @Override
    public void addAlternative(View view) {
        String alternativeTitle = mAhpDashboardAddAlternativeDialogBinding.ahpDashboardAlternativeValueEditText.getText().toString();
        mAhpDashboardAddAlternativeDialogBinding.ahpDashboardAlternativeValueEditText.setText("");
        if(!alternativeTitle.equals(""))
            mAhpDashboardAlternativeAdapter.addItem(alternativeTitle);

        addAlternativeDialog.cancel();
    }

    @Override
    public void openAddCriterionDialog(View view) {
        if(mAhpDashboardCriterionAdapter.getItemCount() == 4)
            Toast.makeText(getContext(), "Can only add maximum of 4 criteria .", Toast.LENGTH_SHORT).show();
        else
            addCriterionDialog.show();
    }

    @Override
    public void closeAddCriterionDialog(View view) {
        addCriterionDialog.cancel();
    }

    @Override
    public void openAddAlternativeDialog(View view) {
        if(mAhpDashboardAlternativeAdapter.getItemCount() == 4)
            Toast.makeText(getContext(), "Can only add maximum of 4 alternative.", Toast.LENGTH_SHORT).show();
        else
            addAlternativeDialog.show();
    }

    @Override
    public void closeAddAlternativeDialog(View view) {
        addAlternativeDialog.cancel();
    }

    @Override
    public void setPresenter(AhpDashboardContract.Presenter presenter) {}

    public void configDialogs(){

        addAlternativeDialog = new Dialog(getContext(), R.style.DialogTheme);
        addAlternativeDialog.setTitle("ADD Alternative");
        mAhpDashboardAddAlternativeDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.ahp_dashboard_add_alternative_dialog, null, false);
        mAhpDashboardAddAlternativeDialogBinding.setHandler(this);
        addAlternativeDialog.setContentView(mAhpDashboardAddAlternativeDialogBinding.getRoot());

        addCriterionDialog = new Dialog(getContext(), R.style.DialogTheme);
        addCriterionDialog.setTitle("ADD CRITERION");
        mAhpDashboardAddCriterionDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.ahp_dashboard_add_criterion_dialog, null, false);
        mAhpDashboardAddCriterionDialogBinding.setHandler(this);
        addCriterionDialog.setContentView(mAhpDashboardAddCriterionDialogBinding.getRoot());
    }


}
