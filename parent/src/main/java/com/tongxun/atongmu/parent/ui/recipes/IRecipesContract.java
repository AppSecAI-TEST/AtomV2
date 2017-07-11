package com.tongxun.atongmu.parent.ui.recipes;

import com.tongxun.atongmu.parent.BaseView;
import com.tongxun.atongmu.parent.model.RecipesModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/11.
 */

public interface IRecipesContract {
    interface View extends BaseView{

        void refreshAdapter(List<RecipesModel> list);

        void onError(String message);
    }

    interface Presenter{

        void getRecipes(String date);
    }

    interface Interactor{
        void getRecipes(String date,onFinishListener listener);

        interface onFinishListener{
            void onSuccess(List<RecipesModel> list);
            void onError(String message);
        }
    }
}
