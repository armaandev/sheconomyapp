package com.sheconomy.sheeconomy.Presentation.presenters;

import com.sheconomy.sheeconomy.Models.PolicyContent;
import com.sheconomy.sheeconomy.Presentation.ui.activities.PolicyView;
import com.sheconomy.sheeconomy.domain.executor.Executor;
import com.sheconomy.sheeconomy.domain.executor.MainThread;
import com.sheconomy.sheeconomy.domain.interactors.PolicyInteractor;
import com.sheconomy.sheeconomy.domain.interactors.impl.PolicyInteractorImpl;

public class PolicyPresenter extends AbstractPresenter implements PolicyInteractor.CallBack {
    private PolicyView policyView;
    private int type = 0;

    public PolicyPresenter(Executor executor, MainThread mainThread, PolicyView policyView) {
        super(executor, mainThread);
        this.policyView = policyView;
    }

    public void getPolicy(String url){
        new PolicyInteractorImpl(mExecutor, mMainThread, this, url).execute();
    }

    @Override
    public void onPolicyLoaded(PolicyContent policyContent) {
        if (policyView != null){
            policyView.onPolicyContentLoaded(policyContent);
        }
    }

    @Override
    public void onPolicyLoadError() {

    }
}
