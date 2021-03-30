package com.sheconomy.sheeconomy.domain.interactors.impl;

import com.sheconomy.sheeconomy.domain.executor.Executor;
import com.sheconomy.sheeconomy.domain.executor.MainThread;
import com.sheconomy.sheeconomy.domain.interactors.base.AbstractInteractor;

public class RazorPAyInteractorImpl extends AbstractInteractor {


    public RazorPAyInteractorImpl(Executor threadExecutor, MainThread mainThread) {
        super(threadExecutor, mainThread);
    }

    @Override
    public void run() {

    }
}
