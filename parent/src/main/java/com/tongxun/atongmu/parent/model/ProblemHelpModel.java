package com.tongxun.atongmu.parent.model;

import java.util.List;

/**
 * Created by Anro on 2017/7/26.
 */

public class ProblemHelpModel {
    /**
     * help : [{"name":"123","url":"https://www.atongmu.net:8443/backworkrest/common/showAppHelpContext?helpId=2f549c6ed75d41638b3dbf2e5bfa22d1"},{"name":"123321","url":"https://www.atongmu.net:8443/backworkrest/common/showAppHelpContext?helpId=36eafa98bad04325a68baf08e2c829fd"}]
     * title : 123
     */

    private String title;
    private List<ProblemHelpItemModel> help;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ProblemHelpItemModel> getHelp() {
        return help;
    }

    public void setHelp(List<ProblemHelpItemModel> help) {
        this.help = help;
    }


}
