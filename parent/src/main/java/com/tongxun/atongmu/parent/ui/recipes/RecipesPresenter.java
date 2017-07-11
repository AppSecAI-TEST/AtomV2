package com.tongxun.atongmu.parent.ui.recipes;

import com.tongxun.atongmu.parent.BasePresenter;
import com.tongxun.atongmu.parent.model.RecipesModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/11.
 */

public class RecipesPresenter extends BasePresenter<IRecipesContract.View> implements IRecipesContract.Presenter, IRecipesContract.Interactor.onFinishListener {

    RrecipesInteractor interactor = null;

    public RecipesPresenter() {
        interactor = new RrecipesInteractor();
    }

    @Override
    public void getRecipes(String date) {
        interactor.getRecipes(date,this);
    }

    @Override
    public void onSuccess(List<RecipesModel> list) {
        if(mView!=null){
            mView.refreshAdapter(list);
        }
    }

    @Override
    public void onError(String message) {
        if(mView!=null){
            mView.onError(message);
        }
    }
}
