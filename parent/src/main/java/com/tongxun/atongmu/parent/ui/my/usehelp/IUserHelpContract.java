package com.tongxun.atongmu.parent.ui.my.usehelp;

import com.tongxun.atongmu.parent.model.FunIntroModel;
import com.tongxun.atongmu.parent.model.ProblemHelpModel;

import java.util.List;

/**
 * Created by Anro on 2017/7/25.
 */

public interface IUserHelpContract {

    interface View{

        void onError(String message);

        void onFunIntroSuccess(List<FunIntroModel> data);

        void onProblemSuccess(List<ProblemHelpModel> datas);
    }

    interface Presenter{

        void getFunctionIntroduction();

        void getUseHelp();
    }

    interface Interactor{

        void getFunctionIntroduction(onFinishListener listener);

        void getUseHelp(onFinishListener listener);

        interface onFinishListener{

            void onError(String message);


            void onFunIntroSuccess(List<FunIntroModel> data);

            void onProblemSuccess(List<ProblemHelpModel> datas);
        }
    }
}
