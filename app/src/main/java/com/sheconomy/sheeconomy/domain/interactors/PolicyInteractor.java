package com.sheconomy.sheeconomy.domain.interactors;

import com.sheconomy.sheeconomy.Models.PolicyContent;

public interface PolicyInteractor {
    interface CallBack {

        void onPolicyLoaded(PolicyContent policyContent);

        void onPolicyLoadError();
    }
}
